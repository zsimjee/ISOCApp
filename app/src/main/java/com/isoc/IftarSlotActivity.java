package com.isoc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by z on 6/21/2015.
 */
public class IftarSlotActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iftar_slot);

        Intent src = getIntent();
        int day = src.getIntExtra("day", 0);
        final boolean saturday = (day - 3) % 7 == 0;

        final TextView total = (TextView) findViewById(R.id.total);
        final TextView taken = (TextView) findViewById(R.id.taken);
        final LinearLayout confirm = (LinearLayout) findViewById(R.id.confirm);
        TextView menu1           = (TextView)findViewById(R.id.menu1);
        TextView menu2           = (TextView)findViewById(R.id.menu2);
        TextView menu3           = (TextView)findViewById(R.id.menu3);
        TextView menu4           = (TextView)findViewById(R.id.menu4);
        LinearLayout iftar = (LinearLayout) findViewById(R.id.iftar);
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
                        Intent intent = new Intent(IftarSlotActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );


        total.setText("$0");

        Querier q = new Querier(this);
        taken.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        int slotsTaken = Integer.parseInt(taken.getText().toString());
                        for(int i = 1; i <= slotsTaken; i++) {
                            CheckBox cb = (CheckBox)findViewById(i * 1);
                            cb.setTextColor(Color.parseColor("#929292"));
                            cb.setClickable(false);
                        }
                    }
                }
        );


        final CompoundButton.OnCheckedChangeListener listener600 = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    total.setText("$" + (Integer.parseInt(total.getText().toString().substring(total.getText().toString().indexOf("$") + 1)) + 600));
                else
                    total.setText("$" + (Integer.parseInt(total.getText().toString().substring(total.getText().toString().indexOf("$") + 1)) - 600));
            }
        };
        final CompoundButton.OnCheckedChangeListener listener300 = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    total.setText("$" + (Integer.parseInt(total.getText().toString().substring(total.getText().toString().indexOf("$") + 1)) + 300));
                else
                    total.setText("$" + (Integer.parseInt(total.getText().toString().substring(total.getText().toString().indexOf("$") + 1)) - 300));
            }
        };

        if (saturday) {
            CheckBox cb1 = new CheckBox(this);
            cb1.setId(1 * 1);
            cb1.setText("$300 - Iftar");
            cb1.setTextColor(Color.parseColor("#1F6234"));
            cb1.setOnCheckedChangeListener(listener300);
            CheckBox cb2 = new CheckBox(this);
            cb2.setId(2 * 1);
            cb2.setText("$600 - Supplies & Cleaning");
            cb2.setTextColor(Color.parseColor("#1F6234"));
            cb2.setOnCheckedChangeListener(listener600);
            iftar.addView(cb1);
            iftar.addView(cb2);
        } else {
            for (int i = 1; i <= 5; i++) {
                CheckBox cb = new CheckBox(this);
                cb.setId(i * 1);
                cb.setText("$600 - 100 People Dinner");
                cb.setTextColor(Color.parseColor("#1F6234"));
                cb.setOnCheckedChangeListener(listener600);
                iftar.addView(cb);
            }
            CheckBox cb1 = new CheckBox(this);
            cb1.setId(5 * 1);
            cb1.setText("$600 - Iftar & Children's Meals");
            cb1.setTextColor(Color.parseColor("#1F6234"));
            cb1.setOnCheckedChangeListener(listener600);
            CheckBox cb2 = new CheckBox(this);
            cb2.setId(6 * 1);
            cb2.setText("$600 - Supplies & Cleaning");
            cb2.setTextColor(Color.parseColor("#1F6234"));
            cb2.setOnCheckedChangeListener(listener600);
            iftar.addView(cb1);
            iftar.addView(cb2);
        }

        final Context c = this;

        confirm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!total.getText().toString().equals("$0")) {
                            Intent intent = new Intent(IftarSlotActivity.this, PaymentInfoActivity.class);
                            intent.putExtra("amount", total.getText().toString().substring(total.getText().toString().indexOf("$") + 1));
                            intent.putExtra("from", "sponsorAnIftar");
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(c,
                                    "Please select an option", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        q.resetTextQuery("iftarSponsors" + day, taken);
        q.resetTextQuery("menu" + day + "_line1", menu1);
        q.resetTextQuery("menu" + day + "_line2", menu2);
        q.resetTextQuery("menu" + day + "_line3", menu3);
        q.resetTextQuery("menu" + day + "_line4", menu4);

    }
}
