package com.isoc;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by z on 6/16/2015.
 */
public class Querier {

    private RequestQueue rq;

    public Querier(Context c) {
        rq = Volley.newRequestQueue(c);
    }

    public void textQuery(String id, final TextView tv) {
        String url = "http://www.isocmasjid.org/ramadanapp/v2/querydb.php?id=" + id;
        JSONObject json;

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public String onResponse(JSONArray response) {
                try {
                    tv.setText(tv.getText() + response.getJSONObject(0).getString("text"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return "";
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("VOLLEY ERROR:", error.getStackTrace().toString());
            }
        });

        rq.add(request);
    }
}
