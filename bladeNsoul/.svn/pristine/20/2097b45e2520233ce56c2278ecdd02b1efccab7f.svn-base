package com.effective.apackage.packageeffective;

import android.util.Log;

import com.effective.apackage.packageeffective.utilities.packageJsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void JSONARRAY_SORT() {

        String brownJson = "[{\"name\":\"드웬의 명예 패키지\",\"price\":36400,\"value\":1.0,\"etc\":\"주 1회 구매\",\"items\":[{\"name\":\"말발굽\",\"value\":0.0,\"count\":1000},{\"name\":\"명예점수\",\"value\":0.0,\"count\":2500}]},{\"name\":\"레벨업패키지(luxury)\",\"price\":55000,\"value\":1.0,\"etc\":\"계정당 1회\",\"items\":[{\"name\":\"다이아\",\"value\":0.0,\"count\":6350},{\"name\":\"5성스킬북\",\"value\":0.0,\"count\":1}]},{\"name\":\"레벨업패키지(standard)\",\"price\":33000,\"value\":1.0,\"etc\":\"계정당 1회\",\"items\":[{\"name\":\"다이아\",\"value\":0.0,\"count\":4290},{\"name\":\"4성스킬북\",\"value\":0.0,\"count\":1}]},{\"name\":\"브린이패쓰패키지(공격형)\",\"price\":130000,\"value\":1.0,\"etc\":\"계정당 3회\",\"items\":[{\"name\":\"레드슬라임\",\"value\":0.0,\"count\":5},{\"name\":\"공격형 계약서\",\"value\":0.0,\"count\":20},{\"name\":\"상급 공격각성 수정\",\"value\":0.0,\"count\":500},{\"name\":\"중급 방어각성 수정\",\"value\":0.0,\"count\":600},{\"name\":\"4성 승급의별\",\"value\":0.0,\"count\":5}]},{\"name\":\"브린이패쓰패키지(마법형)\",\"price\":130000,\"value\":1.0,\"etc\":\"계정당 3회\",\"items\":[{\"name\":\"레드슬라임\",\"value\":0.0,\"count\":5},{\"name\":\"4성 승급의별\",\"value\":0.0,\"count\":5},{\"name\":\"마법형 계약서\",\"value\":0.0,\"count\":20},{\"name\":\"상급 마법각성 수정\",\"value\":0.0,\"count\":500},{\"name\":\"중급 마법각성 수정\",\"value\":0.0,\"count\":600}]},{\"name\":\"브린이패쓰패키지(방어형)\",\"price\":130000,\"value\":1.0,\"etc\":\"계정당 3회\",\"items\":[{\"name\":\"레드슬라임\",\"value\":0.0,\"count\":5},{\"name\":\"중급 방어각성 수정\",\"value\":0.0,\"count\":600},{\"name\":\"4성 승급의별\",\"value\":0.0,\"count\":5},{\"name\":\"방어형 계약서\",\"value\":0.0,\"count\":20},{\"name\":\"상급 방어각성 수정\",\"value\":0.0,\"count\":500}]},{\"name\":\"브린이패쓰패키지(지원형)\",\"price\":130000,\"value\":1.0,\"etc\":\"계정당 3회\",\"items\":[{\"name\":\"레드슬라임\",\"value\":0.0,\"count\":5},{\"name\":\"4성 승급의별\",\"value\":0.0,\"count\":5},{\"name\":\"지원형 계약서\",\"value\":0.0,\"count\":20},{\"name\":\"상급 지원각성 수정\",\"value\":0.0,\"count\":500},{\"name\":\"중급 지원각성 수정\",\"value\":0.0,\"count\":600}]},{\"name\":\"월정액 패키지1\",\"price\":11000,\"value\":1.0,\"etc\":\"\",\"items\":[{\"name\":\"다이아\",\"value\":0.0,\"count\":1200}]},{\"name\":\"월정액 패키지2\",\"price\":33000,\"value\":1.0,\"etc\":\"\",\"items\":[{\"name\":\"다이아\",\"value\":0.0,\"count\":1800},{\"name\":\"고급계약서\",\"value\":0.0,\"count\":20}]},{\"name\":\"전설계약서 패키지\",\"price\":31200,\"value\":1.0,\"etc\":\"\",\"items\":[{\"name\":\"골드\",\"value\":0.0,\"count\":300000},{\"name\":\"전설계약서\",\"value\":0.0,\"count\":10}]},{\"name\":\"전설계약서패키지\",\"price\":22000,\"value\":1.0,\"etc\":\"\",\"items\":[{\"name\":\"골드\",\"value\":0.0,\"count\":1000000},{\"name\":\"말발굽\",\"value\":0.0,\"count\":1000},{\"name\":\"지휘검\",\"value\":0.0,\"count\":50},{\"name\":\"전설계약서\",\"value\":0.0,\"count\":20},{\"name\":\"레드슬라임\",\"value\":0.0,\"count\":2},{\"name\":\"신비의가루\",\"value\":0.0,\"count\":10000}]},{\"name\":\"초보탈출 축하 패키지\",\"price\":11000,\"value\":1.0,\"etc\":\"계정당 1회\",\"items\":[{\"name\":\"다이아\",\"value\":0.0,\"count\":600},{\"name\":\"고급계약서\",\"value\":0.0,\"count\":10},{\"name\":\"골드\",\"value\":0.0,\"count\":200000}]},{\"name\":\"해피할로윈패키지1\",\"price\":78000,\"value\":1.0,\"etc\":\"기간한정\",\"items\":[{\"name\":\"고급계약서\",\"value\":0.0,\"count\":10},{\"name\":\"5성스킬전수권\",\"value\":0.0,\"count\":1},{\"name\":\"강화의묘약\",\"value\":0.0,\"count\":30},{\"name\":\"골드\",\"value\":0.0,\"count\":600000},{\"name\":\"말발굽\",\"value\":0.0,\"count\":1500},{\"name\":\"지휘검\",\"value\":0.0,\"count\":50}]},{\"name\":\"해피할로윈패키지2\",\"price\":156000,\"value\":1.0,\"etc\":\"기간한정\",\"items\":[{\"name\":\"고급계약서\",\"value\":0.0,\"count\":15},{\"name\":\"강화의묘약\",\"value\":0.0,\"count\":30},{\"name\":\"골드\",\"value\":0.0,\"count\":1200000},{\"name\":\"말발굽\",\"value\":0.0,\"count\":3000},{\"name\":\"지휘검\",\"value\":0.0,\"count\":100},{\"name\":\"5성스킬북\",\"value\":0.0,\"count\":1}]}]";

        JSONArray gamePackageJson = null;


        try {
            gamePackageJson = new JSONArray(brownJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("boss3", gamePackageJson.toString());

        JSONArray gamePackageJsonSort = null;

        gamePackageJsonSort = packageJsonUtil.sort(gamePackageJson, new Comparator() {
            @Override
            public int compare(Object a, Object b) {
                JSONObject ja = (JSONObject) a;
                JSONObject jb = (JSONObject) b;
                return ja.optString("price").compareTo(jb.optString("price"));
            }
        });

        Log.d("boss3§", gamePackageJson.toString());
        assertNotEquals(gamePackageJson.toString(), gamePackageJsonSort.toString());

    }
}