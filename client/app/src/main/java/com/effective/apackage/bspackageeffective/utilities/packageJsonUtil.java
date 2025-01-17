package com.effective.apackage.bspackageeffective.utilities;

import android.content.Context;

import com.effective.apackage.bspackageeffective.ApplicationContext;
import com.effective.apackage.bspackageeffective.domain.ItemValue;
import com.effective.apackage.bspackageeffective.domain.PackageValue;
import com.effective.apackage.bspackageeffective.domain.SortType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class packageJsonUtil {

    private static final String TAG = packageJsonUtil.class.getSimpleName();

    static JSONArray gamePackageJson = null;
    static String gamePackageFile = null;
    static final int TOP_RATE1 = 10;
    static final int TOP_RATE2 = 30;
    static final int TOP_RATE3 = 50;
    static ArrayList<PackageValue> packageValueList;
    private static double topRateValue1;
    private static double topRateValue2;
    private static double topRateValue3;


    public static ArrayList<PackageValue> getPackageStringsFromJson(Context context, String gameName)
            throws JSONException {

        try {
            String folder = ApplicationContext.getAppContext().getFilesDir() + File.separator + "json" + File.separator;

            FileInputStream fis = context.openFileInput(gameName + ".json");//파일명
            //Log.d("storage", gameName);
            BufferedReader buffer = new BufferedReader
                    (new InputStreamReader(fis));
            String str = buffer.readLine(); // 파일에서 한줄을 읽어옴

            // 파일에서 읽은 데이터를 저장하기 위해서 만든 변수
            StringBuffer data = new StringBuffer();
            while (str != null) {
                data.append(str + "\n");
                str = buffer.readLine();
            }
            buffer.close();
            gamePackageFile = data.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Log.d("storage", gamePackageFile.toString());


        return initPackageList(new JSONArray(gamePackageFile));
    }

    public static ArrayList<PackageValue> initPackageList(JSONArray gamePackageJsonArray) throws JSONException {
        int valueSum = 0;
        gamePackageJson = gamePackageJsonArray;
        packageValueList = new ArrayList<>();

        //Log.d("sort", gamePackageJson.toString());
        for (int i = 0; i < gamePackageJson.length(); i++) {

            PackageValue packageValue = new PackageValue();
            String name;
            String value;
            String price;
            ArrayList<ItemValue> itemValues = new ArrayList<>();

            JSONObject packageObject = gamePackageJson.getJSONObject(i);


            JSONArray itemsJson = packageObject.getJSONArray("items");
            //Log.d("boss", itemsJson.toString());

            for (int j = 0; j < itemsJson.length(); j++) {

                ItemValue itemValue = new ItemValue();

                itemValue.setItemCount(itemsJson.getJSONObject(j).getDouble("count"));
                itemValue.setItemName(itemsJson.getJSONObject(j).getString("name"));
                itemValue.setItemValue(itemsJson.getJSONObject(j).getString("value"));

                itemValues.add(itemValue);
            }


            packageValue.setPackageName(packageObject.getString("name"));
            packageValue.setPackageValue(Double.parseDouble(packageObject.getString("value")));
            packageValue.setPackagePrice(Integer.parseInt(packageObject.getString("price")));
            packageValue.setEtc(packageObject.getString("etc"));
            //TODO 실제 데이터가 들어가면 try / catch 빼도록
            try{
                packageValue.setState(packageObject.getString("state"));
            }catch (Exception e){
                //Log.d(TAG,"state 파싱 에러");
            }

            packageValue.setItemValues(itemValues);

            valueSum += Double.parseDouble(packageObject.getString("value"));

            //Log.d("boss", "리스트 " + packageValueList.toString());
            packageValueList.add(packageValue);
        }

        return packageValueList;
    }

    public static ArrayList<PackageValue> sort(SortType sortType) throws JSONException {
        gamePackageJson = packageJsonUtil.jsonSort(gamePackageJson, new Comparator() {
            @Override
            public int compare(Object a, Object b) {
                JSONObject ja = (JSONObject) a;
                JSONObject jb = (JSONObject) b;
                switch (sortType) {
                    case PRICE_DOWN:
                        return Integer.compare(Integer.parseInt(jb.optString("price"))
                                , Integer.parseInt(ja.optString("price")));
                    case PRICE_UP:
                        return Integer.compare(Integer.parseInt(ja.optString("price"))
                                , Integer.parseInt(jb.optString("price")));
                    case VALUE_DOWN:
                        return Double.compare(Double.parseDouble(jb.optString("value"))
                                , Double.parseDouble(ja.optString("value")));
                    case VALUE_UP:
                        return Double.compare(Double.parseDouble(ja.optString("value"))
                                , Double.parseDouble(jb.optString("value")));
                }
                return -1;
            }
        });
        //Log.d("sort", gamePackageJson.toString());
        return initPackageList(gamePackageJson);
    }

    public static void initTopRateValues() {
        double topRateValueOrigin1 = packageValueList.get((int) ((double) packageValueList.size() * TOP_RATE1 / 100)).getPackageValue();
        topRateValue1 = Math.round(topRateValueOrigin1 * 100);
        double topRateValueOrigin2 = packageValueList.get((int) ((double) packageValueList.size() * TOP_RATE2 / 100)).getPackageValue();
        topRateValue2 = Math.round(topRateValueOrigin2 * 100);
        double topRateValueOrigin3 = packageValueList.get((int) ((double) packageValueList.size() * TOP_RATE3 / 100)).getPackageValue();
        topRateValue3 = Math.round(topRateValueOrigin3 * 100);

        //Log.d("packageJsonUtil",((double) packageValueList.size() * TOP_RATE1 / 100)+" -"+topRateValue1);
        //Log.d("packageJsonUtil",((double) packageValueList.size() * TOP_RATE2 / 100)+" -"+topRateValue2);

        //Log.d("packageJsonUtil",((double) packageValueList.size() * TOP_RATE3 / 100)+" -"+topRateValue3);
    }

    public static double getTopRateValue1(){
        return topRateValue1;
    }
    public static double getTopRateValue2(){
        return topRateValue2;
    }
    public static double getTopRateValue3(){
        return topRateValue3;
    }


    public static JSONArray jsonSort(JSONArray array, Comparator c) {
        List asList = new ArrayList(array.length());
        for (int i = 0; i < array.length(); i++) {
            asList.add(array.opt(i));
        }
        Collections.sort(asList, c);
        JSONArray res = new JSONArray();
        for (Object o : asList) {
            res.put(o);
        }
        return res;
    }
}
