package com.example.docentapplication;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ResisterRe extends StringRequest {

    //서버 url 설정
    final static  private  String URL = "http://wjdthf413.dothome.co.kr/Resister.php";
    private Map<String, String> map;

    public ResisterRe(String userID, String userPassword, String userName, int userAge, Response.Listener<String> responseListener) {
        super(Method.POST, URL, responseListener, null);
        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPassword", userPassword);
        map.put("userName", userName);
        map.put("userAge", userAge + "");
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
