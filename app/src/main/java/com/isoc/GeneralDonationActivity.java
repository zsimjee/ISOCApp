package com.isoc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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


        explanation.setText("Support your institution!  There are various areas " +
                "where your help is needed. Enter the amount you wish to " +
                "donate to each.\n");

        masjid.setText("Masjid");
        masjidInput.setText("0");
        ocs.setText("Orange Crescent School");
        ocsInput.setText("0");
        social.setText("Social Services, Assistance Programs");
        socialInput.setText("0");
        funeral.setText("Funeral Fund");
        funeralInput.setText("0");
        total.setText("$0");


        masjidInput.addTextChangedListener(
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
                            if (masjidInput.getText().toString().equals(""))
                                masjidInput.setText("0");
                            int masjidAmt = Integer.parseInt(masjidInput.getText().toString());
                            int ocsAmt = Integer.parseInt(ocsInput.getText().toString());
                            int socialAmt = Integer.parseInt(socialInput.getText().toString());
                            int funeralAmt = Integer.parseInt(funeralInput.getText().toString());

                            total.setText("$" + (masjidAmt + ocsAmt + socialAmt + funeralAmt));
                        } catch (Exception e) {
                        }
                    }
                }
        );
        ocsInput.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (ocsInput.getText().toString().equals(""))
                            ocsInput.setText("0");

                        try {
                            int masjidAmt = Integer.parseInt(masjidInput.getText().toString());
                            int ocsAmt = Integer.parseInt(ocsInput.getText().toString());
                            int socialAmt = Integer.parseInt(socialInput.getText().toString());
                            int funeralAmt = Integer.parseInt(funeralInput.getText().toString());

                            total.setText("$" + (masjidAmt + ocsAmt + socialAmt + funeralAmt));
                        } catch (Exception e) {
                        }
                    }
                }
        );
        socialInput.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (socialInput.getText().toString().equals(""))
                            socialInput.setText("0");

                        try {
                            int masjidAmt = Integer.parseInt(masjidInput.getText().toString());
                            int ocsAmt = Integer.parseInt(ocsInput.getText().toString());
                            int socialAmt = Integer.parseInt(socialInput.getText().toString());
                            int funeralAmt = Integer.parseInt(funeralInput.getText().toString());

                            total.setText("$" + (masjidAmt + ocsAmt + socialAmt + funeralAmt));
                        } catch (Exception e) {
                        }
                    }
                }
        );
        funeralInput.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (funeralInput.getText().toString().equals(""))
                            funeralInput.setText("0");

                        try {
                            int masjidAmt = Integer.parseInt(masjidInput.getText().toString());
                            int ocsAmt = Integer.parseInt(ocsInput.getText().toString());
                            int socialAmt = Integer.parseInt(socialInput.getText().toString());
                            int funeralAmt = Integer.parseInt(funeralInput.getText().toString());

                            total.setText("$" + (masjidAmt + ocsAmt + socialAmt + funeralAmt));
                        } catch (Exception e) {
                        }
                    }
                }
        );

        final Context c = this;

        confirm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(GeneralDonationActivity.this, PaymentInfoActivity.class);
                        intent.putExtra("from", "general");

                        int masjidAmt = Integer.parseInt(masjidInput.getText().toString());
                        int ocsAmt = Integer.parseInt(ocsInput.getText().toString());
                        int socialAmt = Integer.parseInt(socialInput.getText().toString());
                        int funeralAmt = Integer.parseInt(funeralInput.getText().toString());
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


}
