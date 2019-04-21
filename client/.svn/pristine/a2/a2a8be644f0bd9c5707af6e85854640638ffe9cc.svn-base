package com.effective.apackage.packageeffective.utilities;

import android.util.Log;

import com.effective.apackage.packageeffective.ApplicationContext;
import com.effective.apackage.packageeffective.R;
import com.effective.apackage.packageeffective.bean.Game;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JsonUtil {
    private static final String TAG = JsonUtil.class.getSimpleName();

    public static String getJsonFolder() {
        return "";
        //return ApplicationContext.getAppContext().getFilesDir() + File.separator + ApplicationContext.getAppContext().getString(R.string.json_path) + File.separator;
    }

    public static String readJsonFile(String fileName) {
        String result = null;
        try {
            FileInputStream fis = ApplicationContext.getAppContext().openFileInput(getJsonFolder() + fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    public static List<Game> getGameList() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Log.d(TAG, getJsonFolder() + ApplicationContext.getAppContext().getString(R.string.main_json));
            List<Game> gameList = objectMapper.readValue(readJsonFile(ApplicationContext.getAppContext().getString(R.string.main_json)), new TypeReference<List<Game>>() {
            });
            return gameList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}