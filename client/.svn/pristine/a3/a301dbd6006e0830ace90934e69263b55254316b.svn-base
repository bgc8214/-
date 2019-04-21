package com.effective.apackage.packageeffective.utilities;


import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {


    public static URL buildUrl(Context context, String gameName) throws IOException {


        String jsonDownloadUrl = ConfigUtils.getProperty("baseUrl", context) + gameName;

        Uri builtUri = Uri.parse(jsonDownloadUrl).buildUpon()
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    // 여기서 파일 다운 후, 값 읽어서 스트링 넘기면 됨
    public static String getResponseFromHttpUrl(URL url, String gameName) throws IOException {


        String savePath = Environment.getExternalStorageDirectory().toString() + File.separator + "Download" + File.separator;

        File dir = new File(savePath);

        //상위 디렉토리가 존재하지 않을 경우 생성

        if (!dir.exists()) {
            Log.d("boss", "만들기");//파일 읽어서 넘겨주기
            Log.d("boss", savePath);//파일 읽어서 넘겨주기

            dir.mkdirs();

        }

        String localPath = savePath + "/" + gameName + ".json";


        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            Log.d("boss", "다운로드1");//파일 읽어서 넘겨주기


            int len = urlConnection.getContentLength();

            byte[] tmpByte = new byte[len];

            Log.d("boss", "다운로드2");//파일 읽어서 넘겨주기


            File file = new File(localPath);

            inputStream = urlConnection.getInputStream();
            outputStream = new FileOutputStream(file);

            FileOutputStream fos = new FileOutputStream(file);
            Log.d("boss", "다운로드3");//파일 읽어서 넘겨주기

            int read;
            while (true) {
                Log.d("boss", "다운로드4");//파일 읽어서 넘겨주기

                read = inputStream.read(tmpByte);

                if (read <= 0) {

                    break;
                }
                fos.write(tmpByte, 0, read); //file 생성

            }

            Log.d("boss", "다운로드 와ㅓㄴ료");//파일 읽어서 넘겨주기
            return null;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            outputStream.close();
            inputStream.close();
            urlConnection.disconnect();

            return null;
        }
    }
}