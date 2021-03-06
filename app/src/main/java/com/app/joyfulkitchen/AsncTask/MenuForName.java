package com.app.joyfulkitchen.AsncTask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.joyfulkitchen.activity.R;
import com.app.joyfulkitchen.activity.menuchild.Newest;
import com.app.joyfulkitchen.model.Message;
import com.app.joyfulkitchen.util.MenuAPI;
import com.app.joyfulkitchen.util.MenuForImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;


/**
 * 根据菜名搜索菜谱
 */
public class MenuForName extends AsyncTask{

    private String result;
    private String menuName;
    private JSONObject menuInfo;
    private BaseAdapter adapter;
    private String title;
    private String image;
    private List<Message> menuList;
    private Bitmap menuForBit;

    @Override
    protected Object doInBackground(Object[] params) {

        menuName = (String) params[0];
        adapter = (BaseAdapter) params[1];
        menuList = (List<Message>) params[2];

        result = MenuAPI.getRequest1(menuName);

        try {
            JSONObject jsonObject = new JSONObject(result);
            menuInfo = jsonObject.getJSONObject("result");
            JSONArray datas = menuInfo.getJSONArray("data");

            for(int i=0;i<datas.length();i++){
                JSONObject  data= datas.optJSONObject(i);
                title = data.getString("title");
                JSONArray album = data.optJSONArray("albums");
                image = album.getString(0);
                /*String类型的网络地址图片转成bitmap*/

                try {
                     menuForBit = MenuForImage.getBitmap(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Message m = new Message();
                m.setMenuName(title);
                m.setImg(menuForBit);
                menuList.add(m);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return title;
    }

    //做完后执行
    @Override
    protected void onPostExecute(Object result) {

        adapter.notifyDataSetChanged();
    }

}
