package com.isoc;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by z on 5/20/2015.
 */
public class SponsorAnIftarActivity extends Activity {

    private Date d;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor_iftar);

        LinearLayout parent = (LinearLayout) findViewById(R.id.parent);
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
                        Intent intent = new Intent(SponsorAnIftarActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );


        Querier q = new Querier(this);

        d = new Date();
        d.setDate(14);
        d.setYear(2015);
        d.setMonth(5);

        final float scale = this.getResources().getDisplayMetrics().density;

        int fiveDp = (int) (5 * scale + 0.5f);
        int fiftyDp = (int) (50 * scale + 0.5f);
        int fifteenDp = (int) (15 * scale + 0.5f);

        int ramadanIndex = 0;

        for (int i = 0; i < 5; i++) {
            //make outer LinearLayouts
            LinearLayout l = new LinearLayout(this);
            l.setPadding(0, fiveDp, 0, 0);
            for (int j = 0; j < 7; j++) {
                //make outer Relative layouts
                RelativeLayout r = new RelativeLayout(this);
                r.setGravity(RelativeLayout.CENTER_HORIZONTAL);
                LinearLayout.LayoutParams outerParams = new LinearLayout.LayoutParams(0, fiftyDp, 1);

                //make inner RelativeLayout
                RelativeLayout ir = new RelativeLayout(this);
                RelativeLayout.LayoutParams innerParams = new RelativeLayout.LayoutParams(fiftyDp, fiftyDp);
                ir.setGravity(RelativeLayout.CENTER_HORIZONTAL);


                TextView date = new TextView(this);
                date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
                date.setText(parseMonth(d.getMonth()) + " " + d.getDate());
                RelativeLayout.LayoutParams dateParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dateParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                date.setTextColor(Color.parseColor("#FFFFFF"));

                if (inRamadan(d)) {
                    ir.setBackgroundColor(Color.parseColor("#1F6234"));
                    ir.setClickable(true);
                    final int dayNum = getDayOfRamadan(d);
                    final int dayOfTheWeek = d.getDay();
                    ir.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(SponsorAnIftarActivity.this, IftarSlotActivity.class);
                                    intent.putExtra("day", dayNum);
                                    startActivity(intent);
                                }
                            }
                    );
                    final Context c = this;
                    ir.setId(ramadanIndex * 1);
                    ramadanIndex++;

                    TextView day = new TextView(this);
                    day.setText("" + dayNum);
                    day.setGravity(Gravity.CENTER);
                    RelativeLayout.LayoutParams dayParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    day.setTextColor(Color.parseColor("#FFFFFF"));

                    ImageView utensils = new ImageView(this);
                    utensils.setImageResource(R.drawable.ico_food_blue);
                    RelativeLayout.LayoutParams utensilsParams = new RelativeLayout.LayoutParams(fifteenDp, fifteenDp);
                    utensilsParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

                    TextView sponsors = new TextView(this);
                    sponsors.addTextChangedListener(makeTextWatcher(sponsors, ir, dayOfTheWeek));
                    q.resetTextQuery("iftarSponsors" + getDayOfRamadan(d), sponsors);
                    sponsors.setPadding(0, 0, fifteenDp, 0);
                    RelativeLayout.LayoutParams sponsorsParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    sponsors.setTextColor(Color.parseColor("#FFFFFF"));
                    sponsorsParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    sponsors.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);

                    ir.addView(sponsors, sponsorsParams);
                    ir.addView(day, dayParams);
                    ir.addView(utensils, utensilsParams);
                }

                else {
                    date.setTextColor(Color.parseColor("#000000"));
                }


                ir.addView(date, dateParams);
                r.addView(ir, innerParams);
                l.addView(r, outerParams);

                d = incrementDate(d);
            }

            //add whole thing to parent
            parent.addView(l);

        }
    }

    private Date incrementDate(Date d) {
        Date retDate = d;
        retDate.setDate(d.getDate() + 1);
        return retDate;
    }

    private String parseMonth(int mon) {
        String[] months = {
                "January", "February", "march", "April", "May", "June", "July", "August", "Septembet", "October", "November", "December"
        };

        return months[mon];
    }

    private int getDayOfRamadan(Date d) {
        if(d.getMonth() == 5) {
            return d.getDate() - 17;
        }
        else
            return d.getDate() + 13;
    }

    private boolean inRamadan(Date d) {
        return (d.getMonth() == 5 && d.getDate() > 17) || (d.getMonth() == 6 && d.getDate() < 17);
    }

    private TextWatcher makeTextWatcher(final TextView sponsors, final RelativeLayout ir, final int dayNum) {
        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!sponsors.getText().toString().contains("/"))
                    if(dayNum == 0)
                        sponsors.setText(sponsors.getText().toString() + "/2");
                    else
                        sponsors.setText(sponsors.getText().toString() + "/7");

                if(sponsors.getText().toString().equals("7/7") || sponsors.getText().toString().equals("2/2")) {
                    ir.setBackgroundColor(Color.parseColor("#929292"));
                    ir.setClickable(false);
                }
            }
        };

        return tw;
    }

}
