package com.isoc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

/**
 * Created by z on 5/20/2015.
 */
public class EidPrayerInfoActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eid_prayer_info);

        TextView title          = (TextView)findViewById(R.id.title);
        TextView date           = (TextView)findViewById(R.id.date);
            date.setText("This year, Eid-ul-Fitr will be on \n");
        TextView location       = (TextView)findViewById(R.id.location);
            location.setText("Eid Prayer wll be held at \n");
        TextView time           = (TextView)findViewById(R.id.time);
        TextView instructions1  = (TextView)findViewById(R.id.instructions1);
        TextView instructions2  = (TextView)findViewById(R.id.instructions2);
        TextView instructions3  = (TextView)findViewById(R.id.instructions3);
        TextView instructions4  = (TextView)findViewById(R.id.instructions4);

        Querier q = new Querier(this);
        q.textQuery("eidTitle", title);
        q.textQuery("eidDate", date);
        q.textQuery("eidLoc", location);
        q.textQuery("eidIntruct1", instructions1);
        q.textQuery("eidIntruct2", instructions2);
        q.textQuery("eidIntruct3", instructions3);
        q.textQuery("eidIntruct4", instructions4);
    }

}
