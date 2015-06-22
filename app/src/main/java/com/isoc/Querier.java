package com.isoc;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by z on 6/16/2015.
 */
public class Querier {

    private RequestQueue rq;

    public Querier(Context c) {
        rq = Volley.newRequestQueue(c);
    }

    public void makeSimpleRequest(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public String onResponse(String response) {
                        return "";
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("VOLLEY ERROR:", error.getStackTrace().toString());
            }
        });
// Add the request to the RequestQueue.
        rq.add(stringRequest);
    }

    public void appendTextQuery(String id, final TextView tv) {
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

    public void addToCommittee(final String committee, final String name,
                               final String address, final String city,
                               final String state, final String zip,
                               final String mobile, final String email,
                               final String age, final String gender) {
        String url = "http://www.isocmasjid.org/ramadanapp/v2/admin/form3.php";

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public String onResponse(String response) {
                        return "";
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.v("VOLLEY ERROR:", error.getStackTrace().toString());
                }
        }) {
            protected Map<String, String> getPostParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("committeName", committee);
                params.put("name", name);
                params.put("address", address);
                params.put("city", city);
                params.put("state", state);
                params.put("zipcode", zip);
                params.put("mobile", mobile);
                params.put("email", email);
                params.put("age", age);
                params.put("gender", gender);
                return params;
            }
        };



        rq.add(request);
    }

    public void resetTextQuery(String id, final TextView tv) {
        String url = "http://www.isocmasjid.org/ramadanapp/v2/querydb.php?id=" + id;
        JSONObject json;

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public String onResponse(JSONArray response) {
                try {
                    tv.setText(response.getJSONObject(0).getString("text"));
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
