package com.isoc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by z on 6/17/2015.
 */
public class PaymentInfoActivity extends Activity {

    private EditText nameInput, addressInput, cityInput, stateInput, zipInput, mobileInput, emailInput;
    private TextView name, address, city, state, zip, mobile, email;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_info);

        name = (TextView) findViewById(R.id.name);
        nameInput = (EditText) findViewById(R.id.nameInput);
        address = (TextView) findViewById(R.id.address);
        addressInput = (EditText) findViewById(R.id.addressInput);
        city = (TextView) findViewById(R.id.city);
        cityInput = (EditText) findViewById(R.id.cityInput);
        state = (TextView) findViewById(R.id.state);
        stateInput = (EditText) findViewById(R.id.stateInput);
        zip = (TextView) findViewById(R.id.zip);
        zipInput = (EditText) findViewById(R.id.zipInput);
        mobile = (TextView) findViewById(R.id.mobile);
        mobileInput = (EditText) findViewById(R.id.mobileInput);
        email = (TextView) findViewById(R.id.email);
        emailInput = (EditText) findViewById(R.id.emailInput);
        RelativeLayout card = (RelativeLayout) findViewById(R.id.card);
        RelativeLayout person = (RelativeLayout) findViewById(R.id.person);
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
                        Intent intent = new Intent(PaymentInfoActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );


        name.setText("Name");
        address.setText("Address");
        city.setText("City");
        state.setText("State");
        zip.setText("Zip");
        mobile.setText("Mobile");
        email.setText("Email");
        final Intent source = getIntent();
        final String from = source.getStringExtra("from");
        final String notes = source.getStringExtra("notes");
        final int amount = source.getIntExtra("amount", 0);

        card.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //savePledgeToDB(nameInput.getText().toString(), addressInput.getText().toString(),
                        //        cityInput.getText().toString(), stateInput.getText().toString(),
                        //zipInput.getText().toString(), mobileInput.getText().toString(),
                        //        emailInput.getText().toString(), amount);
                        //TODO: figure out card payments

                        if (validate()) {
                            Intent intent = new Intent(PaymentInfoActivity.this, CardConfirmationActivity.class);
                            intent.putExtra("from", from);
                            intent.putExtra("amount", amount);
                            //startActivity(intent);

                            String url = "http://www.isocmasjid.org/ramadanapp/isocmobile/donate.php?"
                                    + "amt=" + amount
                                    + "&dt=" + from
                                    + "&not=" + notes;

                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(browserIntent);
                            //startActivity(intent);
                        }
                    }
                }
        );

        person.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (validate()) {
                            savePledgeToDB(nameInput.getText().toString(), addressInput.getText().toString(),
                                    cityInput.getText().toString(), stateInput.getText().toString(),
                                    zipInput.getText().toString(), mobileInput.getText().toString(),
                                    emailInput.getText().toString(), amount, from, notes);

                            Intent intent = new Intent(PaymentInfoActivity.this, PersonConfirmationActivity.class);
                            intent.putExtra("from", from);
                            intent.putExtra("amount", amount);
                            intent.putExtra("notes", notes);
                            startActivity(intent);
                        }
                    }
                }
        );

    }

    private void savePledgeToDB(String name, String address, String city,
                                String state, String zip, String mobile,
                                String email, int amount, String from, String notes) {
        String url = "http://www.isocmasjid.org/ramadanapp/v2/admin/payInPerson_get.php?" +
                "na=" + name +
                "&addr=" + address +
                "&ci=" + city +
                "&st=" + state +
                "&zc=" + zip +
                "&amt=" + amount +
                "&dt=" + from +
                "&not=" + notes +
                "&mo=" + mobile +
                "&em=" + email;

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    private boolean validate() {


        boolean valid = true;

        if (nameInput.getText().toString().equals("")) {
            valid = false;
            name.setTextColor(Color.parseColor("#FF0000"));
        }
        else
            name.setTextColor(Color.parseColor("#000000"));
        if (addressInput.getText().toString().equals("")) {
            valid = false;
            address.setTextColor(Color.parseColor("#FF0000"));
        }
        else
            address.setTextColor(Color.parseColor("#000000"));
        if (cityInput.getText().toString().equals("")) {
            valid = false;
            city.setTextColor(Color.parseColor("#FF0000"));
        }
        else
            city.setTextColor(Color.parseColor("#000000"));
        if (stateInput.getText().toString().equals("")) {
            valid = false;
            state.setTextColor(Color.parseColor("#FF0000"));
        }
        else
            state.setTextColor(Color.parseColor("#000000"));
        if (zipInput.getText().toString().equals("")) {
            valid = false;
            zip.setTextColor(Color.parseColor("#FF0000"));
        }
        else
            zip.setTextColor(Color.parseColor("#000000"));
        if (mobileInput.getText().toString().equals("")) {
            valid = false;
            mobile.setTextColor(Color.parseColor("#FF0000"));
        }
        else
            mobile.setTextColor(Color.parseColor("#000000"));
        if (emailInput.getText().toString().equals("")) {
            valid = false;
            email.setTextColor(Color.parseColor("#FF0000"));
        }
        else
            email.setTextColor(Color.parseColor("#000000"));

        if(!valid) {
            final Context c = this;
            Toast.makeText(this,
                    "Please enter all fields",
                    Toast.LENGTH_SHORT).show();
        }

        return valid;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }
}
