package com.isoc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by z on 5/20/2015.
 */
public class SadaqatulFitrActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sadaqatul_fitr);


        TextView explanation = (TextView) findViewById(R.id.explanation);
        final EditText people = (EditText) findViewById(R.id.people);
        TextView peopleText = (TextView) findViewById(R.id.peopleText);
        final TextView total = (TextView) findViewById(R.id.total);
        LinearLayout confirm = (LinearLayout) findViewById(R.id.confirm);
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
                        Intent intent = new Intent(SadaqatulFitrActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );


        explanation.setText("Sadaqatul Fitr is $10 per person in the family.\n" +
                "It should be paid before Eid.");
        peopleText.setText("Number of people in your family: ");
        total.setText("$0");

        people.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        try {
                            total.setText("$" + Integer.parseInt(people.getText().toString()) * 10);
                        } catch (Exception e) {
                            total.setText("$0");
                        }
                    }
                }
        );

        final Context c = this;

        confirm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (total.getText().toString().equals("$0"))
                            Toast.makeText(c,
                                    "Please enter an amount",
                                    Toast.LENGTH_SHORT).show();
                        else {
                            Intent intent = new Intent(SadaqatulFitrActivity.this, PaymentInfoActivity.class);
                            intent.putExtra("from", "fitr");
                            intent.putExtra("amount", Integer.parseInt(people.getText().toString()) * 10);
                            startActivity(intent);
                        }
                    }
                }
        );

    }
}
