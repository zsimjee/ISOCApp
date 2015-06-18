package com.isoc;

import android.app.Activity;
import android.os.Bundle;
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
import org.w3c.dom.Text;

/**
 * Created by z on 5/20/2015.
 */
public class TodayAtISOCActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_at_isoc);

        TextView fastBegins     = (TextView)findViewById(R.id.fast_begins);
                fastBegins.setText("Fast begins at ");
        TextView fastEnds       = (TextView)findViewById(R.id.fast_ends);
                fastEnds.setText("Fast ends at ");
        TextView menu1           = (TextView)findViewById(R.id.menu1);
                menu1.setText("Menu: ");
        TextView menu2           = (TextView)findViewById(R.id.menu2);
        TextView menu3           = (TextView)findViewById(R.id.menu3);
        TextView menu4           = (TextView)findViewById(R.id.menu4);
        TextView tarawih        = (TextView)findViewById(R.id.tarawih_starts);
                tarawih.setText("Tarawih starts from ");
        TextView khatira        = (TextView)findViewById(R.id.khatira);
            khatira.setText("Khatira after Isha: ");
        TextView tafseer        = (TextView)findViewById(R.id.tafseer);
            tafseer.setText("Tafseer after Witr: ");
        TextView khateeb        = (TextView)findViewById(R.id.khateeb);
            khateeb.setText("Today's Khutbah will be given by ");
        TextView specialEvents1  = (TextView)findViewById(R.id.special_events1);
            specialEvents1.setText("Special events: ");
        TextView specialEvents2  = (TextView)findViewById(R.id.special_events2);
        TextView timetable = (TextView)findViewById(R.id.timetable);
        TextView programs = (TextView)findViewById(R.id.programs);
        TextView menuLink = (TextView)findViewById(R.id.menuLink);

        Querier q = new Querier(this);

        RequestQueue rq = Volley.newRequestQueue(this);
        int day = 1;
        int week = 1;

        q.textQuery("fastBegins" + day, fastBegins);
        q.textQuery("fastEnds" + day, fastEnds);
        q.textQuery("menu" + day + "_line1", menu1);
        q.textQuery("menu" + day + "_line2", menu2);
        q.textQuery("menu" + day + "_line3", menu3);
        q.textQuery("menu" + day + "_line4", menu4);
        q.textQuery("tarawihStart" + day, tarawih);
        q.textQuery("khatiraGiver" + day, khatira);
        q.textQuery("tafseerGiver" + day, tafseer);
        q.textQuery("khutbahGiver" + week, khateeb);
        q.textQuery("specialEvents1_" + day, specialEvents1);
        q.textQuery("specialEvents2_" + day, specialEvents2);
        q.textQuery("timetableURL", timetable);
        q.textQuery("programsURL", programs);
        q.textQuery("menuURL", menuLink);
    }

}
