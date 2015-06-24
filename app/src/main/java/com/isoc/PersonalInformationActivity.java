package com.isoc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by z on 6/18/2015.
 */
public class PersonalInformationActivity extends Activity{

    private EditText nameInput, addressInput, cityInput, stateInput, zipInput, mobileInput, emailInput;
    private TextView name, address, city, state, zip, mobile, email;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        name               = (TextView)findViewById(R.id.name);
        nameInput    = (EditText)findViewById(R.id.nameInput);
        address            = (TextView)findViewById(R.id.address);
        addressInput = (EditText)findViewById(R.id.addressInput);
        city               = (TextView)findViewById(R.id.city);
        cityInput    = (EditText)findViewById(R.id.cityInput);
        state              = (TextView)findViewById(R.id.state);
        stateInput   = (EditText)findViewById(R.id.stateInput);
        zip                = (TextView)findViewById(R.id.zip);
        zipInput     = (EditText)findViewById(R.id.zipInput);
        mobile             = (TextView)findViewById(R.id.mobile);
        mobileInput  = (EditText)findViewById(R.id.mobileInput);
        email              = (TextView)findViewById(R.id.email);
        emailInput   = (EditText)findViewById(R.id.emailInput);
        TextView age                = (TextView)findViewById(R.id.age);
        final EditText ageInput     = (EditText)findViewById(R.id.ageInput);
        TextView gender             = (TextView)findViewById(R.id.gender);
        final EditText genderInput  = (EditText)findViewById(R.id.genderInput);
        LinearLayout submit               = (LinearLayout)findViewById(R.id.submit);
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
                        Intent intent = new Intent(PersonalInformationActivity.this, MainActivity.class);
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
        age.setText("Age");
        gender.setText("Gender");

        Intent src = getIntent();

        final String committees    = src.getStringExtra("committees");
        final boolean newsletter  = src.getBooleanExtra("newsletter", false);
        final boolean member      = src.getBooleanExtra("member", false);

        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(validate()) {
                            joinCommittees(committees, newsletter, member, nameInput.getText().toString(),
                                    addressInput.getText().toString(), cityInput.getText().toString(),
                                    stateInput.getText().toString(), zipInput.getText().toString(),
                                    mobileInput.getText().toString(), emailInput.getText().toString(),
                                    ageInput.getText().toString(), genderInput.getText().toString());

                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Thank you for joining out team!\nISOC will be cntacting you soon",
                                    Toast.LENGTH_SHORT);
                            toast.show();

                            Intent intent = new Intent(PersonalInformationActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
        );
    }

    private void joinCommittees(String committees, boolean newsletter, boolean member,
                                String name, String address, String city, String state,
                                String zip, String mobile, String email, String age, String gender) {

        Querier q = new Querier(this);
        if(!committees.equals(""))
            q.addToCommittee("Committees:" + committees, name, address, city, state, zip, mobile, email, age, gender);
        if(newsletter)
            q.addToCommittee("newsletter", name, address, city, state, zip, mobile, email, age, gender);
        if(member)
            q.addToCommittee("member", name, address, city, state, zip, mobile, email, age, gender);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
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
}
