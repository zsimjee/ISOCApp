package com.isoc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by z on 5/20/2015.
 */
public class GeneralDonationActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_donation);


        TextView explanation = (TextView) findViewById(R.id.explanation);
        TextView masjid = (TextView) findViewById(R.id.masjid);
        final EditText masjidInput = (EditText) findViewById(R.id.masjidInput);
        TextView ocs = (TextView) findViewById(R.id.ocs);
        final EditText ocsInput = (EditText) findViewById(R.id.ocsInput);
        TextView social = (TextView) findViewById(R.id.social);
        final EditText socialInput = (EditText) findViewById(R.id.socialInput);
        final TextView funeral = (TextView) findViewById(R.id.funeral);
        final EditText funeralInput = (EditText) findViewById(R.id.funeralInput);
        final TextView total = (TextView) findViewById(R.id.total);
        LinearLayout confirm = (LinearLayout) findViewById(R.id.confirm);
        ImageButton back = (ImageButton) findViewById(R.id.back);
        ImageButton home = (ImageButton) findViewById(R.id.home);

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
                        Intent intent = new Intent(GeneralDonationActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );


        explanation.setText("Support your institution!  There are various areas " +
                "where your help is needed. Enter the amount you wish to " +
                "donate to each.\n");


        masjid.setText("Masjid");
        ocs.setText("Orange Crescent School");
        social.setText("Social Services, Assistance Programs");
        funeral.setText("Funeral Fund");
        total.setText("$0");

        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int masjidAmt = 0;
                int ocsAmt = 0;
                int socialAmt = 0;
                int funeralAmt = 0;
                try{masjidAmt = Integer.parseInt(masjidInput.getText().toString());} catch(Exception e) {}
                try{ocsAmt = Integer.parseInt(ocsInput.getText().toString());} catch(Exception e) {}
                try{socialAmt = Integer.parseInt(socialInput.getText().toString());} catch(Exception e) {}
                try{funeralAmt = Integer.parseInt(funeralInput.getText().toString());} catch(Exception e) {}

                total.setText("$" + (masjidAmt + ocsAmt + socialAmt + funeralAmt));

            }
        };

        masjidInput.addTextChangedListener(tw);
        ocsInput.addTextChangedListener(tw);
        socialInput.addTextChangedListener(tw);
        funeralInput.addTextChangedListener(tw);

        final Context c = this;

        confirm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(GeneralDonationActivity.this, PaymentInfoActivity.class);
                        intent.putExtra("from", "general");

                        int masjidAmt = 0;
                        int ocsAmt = 0;
                        int socialAmt = 0;
                        int funeralAmt = 0;
                        try{masjidAmt = Integer.parseInt(masjidInput.getText().toString());} catch(Exception e) {}
                        try{ocsAmt = Integer.parseInt(ocsInput.getText().toString());} catch(Exception e) {}
                        try{socialAmt = Integer.parseInt(socialInput.getText().toString());} catch(Exception e) {}
                        try{funeralAmt = Integer.parseInt(funeralInput.getText().toString());} catch(Exception e) {}

                        int amt = masjidAmt + ocsAmt + socialAmt + funeralAmt;

                        if (amt == 0) {
                            Toast toast = Toast.makeText(c, "Please enter an amount", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            intent.putExtra("amount", amt);
                            intent.putExtra("notes", "masjid:" + masjidAmt +
                                    ",ocs:" + ocsAmt + ",social:" + socialAmt +
                                    "funeral:" + funeralAmt);

                            startActivity(intent);
                        }
                    }
                }
        );
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

}
