package com.isoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by z on 6/17/2015.
 */
public class PaymentInfoActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_info);

        TextView name           = (TextView)findViewById(R.id.name);
        final EditText nameInput      = (EditText)findViewById(R.id.nameInput);
        TextView address        = (TextView)findViewById(R.id.address);
        final EditText addressInput   = (EditText)findViewById(R.id.addressInput);
        TextView city           = (TextView)findViewById(R.id.city);
        final EditText cityInput      = (EditText)findViewById(R.id.cityInput);
        TextView state          = (TextView)findViewById(R.id.state);
        final EditText stateInput     = (EditText)findViewById(R.id.stateInput);
        TextView zip            = (TextView)findViewById(R.id.zip);
        final EditText zipInput       = (EditText)findViewById(R.id.zipInput);
        TextView mobile         = (TextView)findViewById(R.id.mobile);
        final EditText mobileInput    = (EditText)findViewById(R.id.mobileInput);
        TextView email          = (TextView)findViewById(R.id.email);
        final EditText emailInput     = (EditText)findViewById(R.id.emailInput);
        Button card             = (Button)findViewById(R.id.card);
        Button person           = (Button)findViewById(R.id.person);


        name.setText("Name");
        address.setText("Address");
        city.setText("City");
        state.setText("State");
        zip.setText("Zip");
        mobile.setText("Mobile");
        email.setText("Email");
        card.setText("PAY BY CREDIT CARD");
        person.setText("PAY IN PERSON");

        final Intent source = getIntent();
        final String from = source.getStringExtra("from");
        final int amount = source.getIntExtra("amount", 0);

        card.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        savePledgeToDB(nameInput.getText().toString(), addressInput.getText().toString(),
                                cityInput.getText().toString(), stateInput.getText().toString(),
                                zipInput.getText().toString(), mobileInput.getText().toString(),
                                emailInput.getText().toString(), amount);
                        //TODO: figure out card payments

                        Intent intent = new Intent(PaymentInfoActivity.this, CardConfirmationActivity.class);
                        intent.putExtra("from", from + "CCPayment");
                        intent.putExtra("amount", amount);
                        startActivity(intent);
                    }
                }
        );

        person.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        savePledgeToDB(nameInput.getText().toString(), addressInput.getText().toString(),
                                cityInput.getText().toString(), stateInput.getText().toString(),
                                zipInput.getText().toString(), mobileInput.getText().toString(),
                                emailInput.getText().toString(), amount);

                        Intent intent = new Intent(PaymentInfoActivity.this, PersonConfirmationActivity.class);
                        intent.putExtra("from", from + "PayInPerson");
                        intent.putExtra("amount", amount);
                        startActivity(intent);
                    }
                }
        );

    }

    private void savePledgeToDB(String name, String address, String city, String state, String zip, String mobile, String email, int amount) {
        //TODO
    }
}
