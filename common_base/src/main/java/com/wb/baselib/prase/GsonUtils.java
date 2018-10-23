package com.wb.baselib.prase;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 这个是GSON解析工具类
 */
public class GsonUtils {
    private static GsonUtils gsonUtils;
    public static GsonUtils newInstance(){
        if(gsonUtils==null){
            gsonUtils=new GsonUtils();
        }
        return gsonUtils;
    }
    /**
     * 将json转为制定类型
     * @param jsonData json
     * @param entityType 转换的类型
     * @param <T>
     * @return
     */
    public  <T> T parseJson(String jsonData,Class<T> entityType){
        T t = null;
        Gson gson = new Gson();
        try {
            t = gson.fromJson(jsonData, entityType);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }

        return t;
    }

    /**
     * 将json转为list类型
     * @param jsonArrayData
     * @param <T>
     * @return
     */
    public  <T> List<T> parseJsonArray(String jsonArrayData){
        Gson gson = new Gson();
        List<T> list=gson.fromJson(jsonArrayData,new TypeToken<List<T>>(){}.getType());
        return list;
    }

    /**
     * 将jsonarray数组转为List类型
     * @param array
     * @param entityType
     * @param <T>
     * @return
     */
    public  <T> List<T> readJsonArray(JSONArray array, Class<T> entityType) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                T t = gson.fromJson(array.getJSONObject(i).toString(), entityType);
                list.add(t);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     *  将array字符串转化为 list
     * @param array
     * @param entityType
     * @param <T>
     * @return
     * @throws JSONException
     */
    public  <T> List<T> readJsonArray(String array, Class<T> entityType) throws JSONException {
        Gson gson = new Gson();
        JSONArray jsonArray=new JSONArray(array);
        List<T> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                T t = gson.fromJson(jsonArray.getJSONObject(i).toString(), entityType);
                list.add(t);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    /**
     * 将Object转化为String
     * @param object
     * @return
     */
    public  String GsonToString(Object object) {
        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .create();
        String json=gson.toJson(object);
        return  json;

    }

    /**
     * 将map 转为json 字符串
     * @param map
     * @return
     */
    public  String simpleMapToJsonStr(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return "null";
        }
        String jsonStr = "{";
        Set<?> keySet = map.keySet();
        for (Object key : keySet) {
            jsonStr += "\"" + key + "\":" +"\""+map.get(key)+"\"" + ",";
        }
        jsonStr = "{" + jsonStr.substring(1, jsonStr.length() - 1);
        jsonStr += "}";
        return jsonStr;
    }
    /**
     * 将json转化为Object
     * @param json
     * @param cls
     * @return
     */

    public  <T> T getBean(String json, Class<T> cls) {
        T t = null;
        Gson gson = new Gson();
        try {
            t = gson.fromJson(json, cls);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }

        return t;
    }
    /**
     * 将list类型转化为String
     *
     * @param list
     * @return
     */
    public  <T> String listToJson(List<T> list) {
        Gson gson = new Gson();
        try {
            return gson.toJson(list);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

    }

    /**
     * 将文件中的json读取出来
     * @param context
     * @param fileName
     * @return
     */
    public String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}