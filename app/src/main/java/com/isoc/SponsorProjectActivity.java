package com.isoc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by z on 6/18/2015.
 */
public class SponsorProjectActivity extends Activity {
    private EditText otherAmount;
    private int increment;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor_project);

        //getActionBar().setTitle("Ramadan At ISOC");

        Intent src = getIntent();
        final String from = src.getStringExtra("from");
        TextView desc = (TextView) findViewById(R.id.desc);
        final TextView amount = (TextView) findViewById(R.id.amount);
        TextView project = (TextView) findViewById(R.id.project);
        final RadioGroup amounts = (RadioGroup) findViewById(R.id.amounts);
        final RadioGroup projects = (RadioGroup) findViewById(R.id.projects);
        otherAmount = (EditText)findViewById(R.id.otherAmount);
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
                        Intent intent = new Intent(SponsorProjectActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );


        otherAmount.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        amounts.clearCheck();
                    }
                }
        );

        if (from.equals("pow1k"))
            increment = 100;
        else if (from.equals("40x40"))
            increment = 2500;

        desc.setText("Please indicate a total sponsorship amount.  " +
                "Each increment of $" + String.format("%,d", increment) + " represents the number of " +
                "sponsors you are committing for.  You can sponsor " +
                "$" + String.format("%,d", increment) + " or more per member in your family, or on behalf " +
                "of someone who has passed away.");
        amount.setText("Select one:");
        project.setText("Choose your area of sponsorship: ");

        makeAmountRadios(amounts);
        makeProjectRadios(projects);


        confirm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (amounts.getCheckedRadioButtonId() != -1) {
                            Intent intent = new Intent(SponsorProjectActivity.this, PaymentInfoActivity.class);
                            intent.putExtra("from", from);
                            intent.putExtra("amount", amounts.getCheckedRadioButtonId());
                            RadioButton proj = (RadioButton) findViewById(projects.getCheckedRadioButtonId());
                            intent.putExtra("notes", "project:" + proj.getText().toString());
                            startActivity(intent);
                        }
                        else if(validate()) {
                            Intent intent = new Intent(SponsorProjectActivity.this, PaymentInfoActivity.class);
                            intent.putExtra("from", from);
                            intent.putExtra("amount", otherAmount.getText().toString());
                            RadioButton proj = (RadioButton) findViewById(projects.getCheckedRadioButtonId());
                            intent.putExtra("notes", "project:" + proj.getText().toString());
                            startActivity(intent);
                        }
                    }
                }
        );
    }

    private void makeAmountRadios(RadioGroup amounts) {


        for (int i = 1; i <= 4; i++) {
            final RadioButton rb = new RadioButton(this);
            rb.setText("$" + String.format("%,d", increment * i));
            rb.setId(increment * i);
            rb.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            otherAmount.setText("");
                            rb.setChecked(true);
                        }
                    }
            );
            //rb.setButtonDrawable(R.drawable.button_selector);
            amounts.addView(rb);
        }
        //RadioButton rb = new RadioButton(this);
        //rb.setText("Other: ");
        //rb.setId(5 * 1);



        amounts.check(increment * 1);
    }

    private void makeProjectRadios(RadioGroup projects) {
        Querier q = new Querier(this);

        for (int i = 1; i <= 8; i++) {
            RadioButton rb = new RadioButton(this);
            rb.setId(i * 1);
            q.appendTextQuery("sponsorProject" + i, rb);
            projects.addView(rb);
        }
        RadioButton rb = new RadioButton(this);
        rb.setId(1 * 9);
        rb.setText("General Operations");
        projects.addView(rb);


        projects.check(1 * 9);
    }

    private boolean validate() {
        if(otherAmount.getText().toString().equals("")
                || Integer.parseInt(otherAmount.getText().toString()) < increment)
        {
            Toast.makeText(this,
                    "Please enter an amount over $" + increment,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
