package com.isoc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
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
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

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
    private Context c;

    public Querier(Context c) {
        rq = Volley.newRequestQueue(c);
        this.c = c;
    }

    public void makeLink(String id, final View v) {

        final String url = "http://www.isocmasjid.org/ramadanapp/v2/querydb.php?id=" + id;
        JSONObject json;

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public String onResponse(JSONArray response) {
                try {
                    final String gotoURL = response.getJSONObject(0).getString("text");
                    v.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(gotoURL));
                                    c.startActivity(browserIntent);
                                }
                            }
                    );
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
        String url = "http://www.isocmasjid.org/ramadanapp/v2/admin/form3.php?" +
                "committeeName=" + committee +
                "&name=" + name +
                "&address=" + address +
                "&city=" + city +
                "&state=" + state +
                "&zipcode=" + zip +
                "&mobile=" + mobile +
                "&email=" + email +
                "&age=" + age +
                "&gender=" + gender;

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
        });

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
