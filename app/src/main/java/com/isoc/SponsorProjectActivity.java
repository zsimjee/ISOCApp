package com.isoc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by z on 6/18/2015.
 */
public class SponsorProjectActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor_project);

        //getActionBar().setTitle("Ramadan At ISOC");

        Intent src          = getIntent();
        final String from   = src.getStringExtra("from");
        TextView desc       = (TextView)findViewById(R.id.desc);
        final TextView amount     = (TextView)findViewById(R.id.amount);
        TextView project    = (TextView)findViewById(R.id.project);
        final RadioGroup amounts  = (RadioGroup)findViewById(R.id.amounts);
        final RadioGroup projects = (RadioGroup)findViewById(R.id.projects);
        LinearLayout confirm      = (LinearLayout)findViewById(R.id.confirm);
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
                        Intent intent = new Intent(SponsorProjectActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );


        int increment = 0;

        if(from.equals("pow1k"))
            increment = 100;
        else if(from.equals("40x40"))
            increment = 2500;

        desc.setText("Please indicate a total sponsorship amount.  " +
                "Each increment of $" + String.format("%,d", increment) + " represents the number of " +
                "sponsors you are committing for.  You can sponsor " +
                "$" + String.format("%,d", increment) + " or more per member in your family, or on behalf " +
                "of someone who has passed away.");
        amount.setText("Select one:");
        project.setText("Choose your area of sponsorship: ");

        makeAmountRadios(increment, amounts);
        makeProjectRadios(projects);


        confirm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SponsorProjectActivity.this, PaymentInfoActivity.class);
                        intent.putExtra("from", from);
                        intent.putExtra("amount", amounts.getCheckedRadioButtonId());
                        RadioButton proj = (RadioButton)findViewById(projects.getCheckedRadioButtonId());
                        intent.putExtra("notes", "project:" + proj.getText().toString());
                        startActivity(intent);
                    }
                }
        );

    }

    private void makeAmountRadios(int increment, RadioGroup amounts) {


        for(int i = 1; i <= 4; i++) {
            RadioButton rb = new RadioButton(this);
            rb.setText("$" + String.format("%,d", increment * i));
            rb.setId(increment * i);
            //rb.setButtonDrawable(R.drawable.button_selector);
            amounts.addView(rb);
        }

        amounts.check(increment * 1);
    }

    private void makeProjectRadios(RadioGroup projects) {
        Querier q = new Querier(this);

        for(int i = 1; i <= 8; i++) {
            RadioButton rb = new RadioButton(this);
            rb.setId(i * 1);
            q.appendTextQuery("sponsorProject" + i, rb);
            projects.addView(rb);
        }

        projects.check(8 * 1);
    }
}
