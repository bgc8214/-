package com.effective.apackage.bspackageeffective;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.effective.apackage.bspackageeffective.utilities.NetworkUtil;
import com.effective.apackage.packageeffective.R;
import com.google.android.gms.ads.MobileAds;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private int asyncCount = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // MainActivity.class 자리에 다음에 넘어갈 액티비티를 넣어주기

        MobileAds.initialize(this, "ca-app-pub-1562059604219100~4868740928ㄴ");
        //Log.i(TAG, "start checksum main_json");
        new FileChecksum().execute(ApplicationContext.getAppContext().getString(R.string.app_game) + ".json");
    }

    public void checkFinish() {
        asyncCount = asyncCount-1;
        if(asyncCount == 0) {
            Intent intent = new Intent(this, GameMainActivity.class);
            intent.putExtra("state", "launch");
            startActivity(intent);
            finish();
        }
    }

    public class FileChecksum extends AsyncTask<String, String, Boolean> {
        private String fileChecksumUrl;
        private String fileName;
        private String folder;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            asyncCount = asyncCount+1;
            fileChecksumUrl = NetworkUtil.getChecksumUrl();
            folder = ApplicationContext.getAppContext().getFilesDir() + File.separator;
            File directory = new File(folder);

            if (!directory.exists()) {
                directory.mkdirs();
            }
        }

        @Override
        protected void onPostExecute(Boolean isCorrect) {
            super.onPostExecute(isCorrect);
            //Log.i(TAG, fileName + " checksum " + isCorrect);
            if(!isCorrect) {
                //Log.i(TAG, fileName + " start download");
                new DownloadFile().execute(fileName);
            } else {
                /*if(fileName.equals(ApplicationContext.getAppContext().getString(R.string.main_json))) {
                    List<Game> gameList = JsonUtil.getGameList();

                    if(gameList != null) {
                        List<FileChecksum> tasks = new ArrayList<>();
                        for(Game game : gameList) {
                            //Log.i(TAG, game.getName());
                            FileChecksum task = new FileChecksum();
                            tasks.add(task);
                            task.execute(game.getName() + ".json");
                        }
                    }
                }*/
            }
            //Log.i(TAG, fileName + " checksum task finised");

            checkFinish();
        }

        @Override
        protected Boolean doInBackground(String... f_url) {
            fileName = f_url[0];
            File jsonFile = new File(folder + fileName);

            if(jsonFile == null || !jsonFile.exists() || !jsonFile.isFile()) {
                return false;
            }

            String md5hash = checksum(jsonFile);
            if(md5hash == null) return false;

            //Log.i(TAG, fileName + " : " + md5hash);
            String inputLine;
            String result;
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(fileChecksumUrl).append(fileName).append("?md5hash=").append(md5hash);
                URL myUrl = new URL(sb.toString());
                //Create a connection
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod("GET");
                connection.setReadTimeout(500);
                connection.setConnectTimeout(500);

                connection.connect();

                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();

                connection.disconnect();
                //Log.i(TAG, fileName + " result : " + result);
                if(result.equals("true")) return true;
                else return false;

            } catch (Exception e) {
                //Log.e("Error: ", e.getMessage());
                return true;
            }
        }

        private String checksum(File file) {
            try {
                InputStream fin = new FileInputStream(file);
                java.security.MessageDigest md5er = MessageDigest.getInstance("MD5");
                byte[] buffer = new byte[1024];

                int read;
                do {
                    read = fin.read(buffer);
                    if (read > 0)
                        md5er.update(buffer, 0, read);
                } while (read != -1);
                fin.close();

                byte[] digest = md5er.digest();
                if (digest == null)
                    return null;
                String strDigest = "0x";
                for (int i = 0; i < digest.length; i++) {
                    strDigest += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1).toUpperCase();
                }
                return strDigest;
            } catch (Exception e) {
                //Log.e("Error: ", e.getMessage());
                return null;
            }
        }

    }

    /**
     * Async Task to download file from URL
     */
    public class DownloadFile extends AsyncTask<String, String, Boolean> {

        private String fileDownloadUrl;
        private String fileName;
        private String folder;

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            asyncCount = asyncCount+1;
            fileDownloadUrl = NetworkUtil.getDownloadUrl();
            folder = ApplicationContext.getAppContext().getFilesDir() + File.separator;
            File directory = new File(folder);

            if (!directory.exists()) {
                directory.mkdirs();
            }
        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected Boolean doInBackground(String... f_url) {
            int count;

            InputStream input = null;
            FileOutputStream output = null;
            try {
                URL url = new URL(fileDownloadUrl + f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();
                //Log.i(TAG, "file length : " + lengthOfFile);

                // input stream to read file - with 8k buffer
                input = new BufferedInputStream(url.openStream(), 8192);

                //Extract file name from URL
                fileName = f_url[0];

                // Output stream to write file
                output = new FileOutputStream(folder + fileName);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));
                    //Log.d(TAG, "Progress: " + (int) ((total * 100) / lengthOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();
                return true;

            } catch (Exception e) {
                //Log.e("Error: ", e.getMessage());
            }

            return false;
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
        }


        @Override
        protected void onPostExecute(Boolean isSuccess) {
            // dismiss the dialog after the file was downloaded
            super.onPostExecute(isSuccess);
            if(!isSuccess) {
                //Log.i(TAG, fileName + "download task fail");
                File file = new File(folder + fileName);
                if(file.exists()) {
                    //TODO
                }
                else {
                    //TODO
                }
            }else {
                /*if(fileName.equals(ApplicationContext.getAppContext().getString(R.string.main_json))) {
                    if(isSuccess) new FileChecksum().execute(fileName);
                }*/
            }

            //Log.i(TAG, fileName + "download task finised");
            checkFinish();
        }
    }
}
