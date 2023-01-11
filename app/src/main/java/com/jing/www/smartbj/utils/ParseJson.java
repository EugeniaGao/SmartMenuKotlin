package com.jing.www.smartbj.utils;


import android.content.res.AssetManager;
import android.view.View;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 本地json数据的解析
 */
public class ParseJson {
    public void getJson(View view){
        InputStreamReader inputStreamReader=null;
        BufferedReader br=null;
        try{
            AssetManager assetManager =view.getContext().getAssets(); //获得assets资源管理器（assets中的文件无法直接访问，可以使用AssetManager访问）
            inputStreamReader = new InputStreamReader(assetManager.open("menuData.json"),"UTF-8"); //使用IO流读取json文件内容
            br = new BufferedReader(inputStreamReader);//使用字符高效流
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine())!=null){
                builder.append(line);
            }

            JSONObject testJson = new JSONObject(builder.toString()); // 从builder中读取了json中的数据。
            // 直接传入JSONObject来构造一个实例
            JSONArray array = testJson.getJSONArray("banks");

            System.out.println(">>>>>>banks object is->"+array.toString());
//            TextView jsonText= findViewById(R.id.jsonDisplayer);
            for (int i = 0;i<array.length();i++){
                JSONObject jsonObject = array.getJSONObject(i);
                String text = jsonObject.getString("text");
                String value = jsonObject.getString("value");
                String displayTxt=value+":"+text+"\n";
//                jsonText.append(displayTxt);
                System.out.println(">>>>>>JSON Data->\n"+text+value);
            }
        }catch(Exception e){
            System.err.println(">>>>>>read json error->"+e.getMessage());
            e.printStackTrace();
        }finally{
            try{
                br.close();
            }catch(Exception e){}
            try{
                inputStreamReader.close();
            }catch(Exception e){}
        }
    }

}
