package com.isoc;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by z on 5/20/2015.
 */
public class TodayAtISOCActivity extends Activity {

    private Querier q;
    private ImageButton backDay, nextDay;
    private TextView dayView, fastBegins, fastEnds, menu1, menu2, menu3, menu4, tarawih, khatira, khateebStatic, khateeb, specialEvents1, specialEvents2;
    private int day;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_at_isoc);

        backDay = (ImageButton)findViewById(R.id.backDay);
        nextDay = (ImageButton)findViewById(R.id.nextDay);
        dayView        = (TextView)findViewById(R.id.day);
        fastBegins     = (TextView)findViewById(R.id.fast_begins);
        fastEnds       = (TextView)findViewById(R.id.fast_ends);
        menu1           = (TextView)findViewById(R.id.menu1);
        menu2           = (TextView)findViewById(R.id.menu2);
        menu3           = (TextView)findViewById(R.id.menu3);
        menu4           = (TextView)findViewById(R.id.menu4);
        tarawih        = (TextView)findViewById(R.id.tarawih_starts);
        khatira        = (TextView)findViewById(R.id.khatira);
        khateeb        = (TextView)findViewById(R.id.khateeb);
        khateebStatic   = (TextView)findViewById(R.id.khateebStatic);
        specialEvents1  = (TextView)findViewById(R.id.special_events1);
        specialEvents2  = (TextView)findViewById(R.id.special_events2);
        final TextView timetable = (TextView)findViewById(R.id.timetable);
        final TextView programs = (TextView)findViewById(R.id.programs);
        final TextView menuLink = (TextView)findViewById(R.id.menuLink);
        ImageButton back = (ImageButton)findViewById(R.id.back);
        ImageButton home = (ImageButton)findViewById(R.id.home);

        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
        home.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TodayAtISOCActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

        q = new Querier(this);

        q.makeLink("timetableURL", timetable);
        q.makeLink("programsURL", programs);
        q.makeLink("menuURL", menuLink);

        RequestQueue rq = Volley.newRequestQueue(this);
        day = getCurrDay();
        final Date date = new Date();

        query(q, day, date);

        if(day == 1)
            backDay.setVisibility(View.INVISIBLE);
        if(day == 29)
            nextDay.setVisibility(View.INVISIBLE);


        backDay.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        day--;
                        date.setDate(date.getDate() - 1);
                        if (day == 1)
                            backDay.setVisibility(View.INVISIBLE);
                        nextDay.setVisibility(View.VISIBLE);
                        query(q, day, date);
                    }
                }
        );

        nextDay.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        day++;
                        date.setDate(date.getDate() + 1);
                        if (day == 29)
                            nextDay.setVisibility(View.INVISIBLE);
                        backDay.setVisibility(View.VISIBLE);
                        query(q, day, date);
                    }
                }
        );
    }



    private void query(Querier q, int day, Date date) {

        dayView.setText(parseDay(date.getDay()) + ", " + parseMonth(date.getMonth()) + " " + date.getDate() + " (Day " + day + ")");
        q.resetTextQuery("fastBegins" + day, fastBegins);
        q.resetTextQuery("fastEnds" + day, fastEnds);
        q.resetTextQuery("menu" + day + "_line1", menu1);
        q.resetTextQuery("menu" + day + "_line2", menu2);
        q.resetTextQuery("menu" + day + "_line3", menu3);
        q.resetTextQuery("menu" + day + "_line4", menu4);
        q.resetTextQuery("tarawihStart" + day, tarawih);
        q.resetTextQuery("khatiraGiver" + day, khatira);
        if(isFriday(day)) {
            q.resetTextQuery("khutbahGiver" + getWeek(day), khateeb);
            khateebStatic.setVisibility(View.VISIBLE);
            khateeb.setVisibility(View.VISIBLE);
            khateebStatic.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            khateeb.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        else {
            khateebStatic.setHeight(0);
            khateeb.setHeight(0);
            khateebStatic.setVisibility(View.INVISIBLE);
            khateeb.setVisibility(View.INVISIBLE);
        }
        q.resetTextQuery("specialEvents1_" + day, specialEvents1);
        q.resetTextQuery("specialEvents2_" + day, specialEvents2);
    }

    private int getCurrDay() {
        Date today = new Date();
        Date start = new Date();
        start.setMonth(5);
        start.setDate(5);
        return (int)((today.getTime() - start.getTime()) / (1000 * 60 * 60 * 24));
    }
    private int getWeek(int day) {
        if(day < 6)
            return 1;
        else if(day < 13)
            return 2;
        else if(day < 20)
            return 3;
        else if(day < 27)
            return 4;
        else
            return 5;
    }
    private boolean isFriday(int day) {
        return day == 5 || day == 12 || day == 19 || day == 26;
    }
    private String parseDay(int day) {
        String[] days = {
                "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
        };

        return days[day];
    }
    private String parseMonth(int month) {
        String[] months = {
                "January", "February", "March","April", "May", "June", "July", "August", "September", "October", "November", "December"
        };

        return months[month];
    }

}
