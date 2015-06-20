package com.isoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by z on 6/18/2015.
 */
public class PersonalInformationActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);

        TextView name               = (TextView)findViewById(R.id.name);
        final EditText nameInput    = (EditText)findViewById(R.id.nameInput);
        TextView address            = (TextView)findViewById(R.id.address);
        final EditText addressInput = (EditText)findViewById(R.id.addressInput);
        TextView city               = (TextView)findViewById(R.id.city);
        final EditText cityInput    = (EditText)findViewById(R.id.cityInput);
        TextView state              = (TextView)findViewById(R.id.state);
        final EditText stateInput   = (EditText)findViewById(R.id.stateInput);
        TextView zip                = (TextView)findViewById(R.id.zip);
        final EditText zipInput     = (EditText)findViewById(R.id.zipInput);
        TextView mobile             = (TextView)findViewById(R.id.mobile);
        final EditText mobileInput  = (EditText)findViewById(R.id.mobileInput);
        TextView email              = (TextView)findViewById(R.id.email);
        final EditText emailInput   = (EditText)findViewById(R.id.emailInput);
        TextView age                = (TextView)findViewById(R.id.age);
        final EditText ageInput     = (EditText)findViewById(R.id.ageInput);
        TextView gender             = (TextView)findViewById(R.id.gender);
        final EditText genderInput  = (EditText)findViewById(R.id.genderInput);
        Button submit               = (Button)findViewById(R.id.submit);

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

        final int[] committees    = src.getIntArrayExtra("committees");
        final boolean newsletter  = src.getBooleanExtra("newsletter", false);
        final boolean member      = src.getBooleanExtra("member", false);

        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
                    }
                }
        );
    }

    private void joinCommittees(int[] committees, boolean newsletter, boolean member,
                                String name, String address, String city, String state,
                                String zip, String mobile, String email, String age, String gender) {

        Querier q = new Querier(this);
        for(int c : committees)
            q.addToCommittee("Committee" + c, name, address, city, state, zip, mobile, email, age, gender);
        if(newsletter)
            q.addToCommittee("newsletter", name, address, city, state, zip, mobile, email, age, gender);
        if(member)
            q.addToCommittee("member", name, address, city, state, zip, mobile, email, age, gender);
    }
}
