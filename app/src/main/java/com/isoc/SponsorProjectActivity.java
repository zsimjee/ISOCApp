package com.isoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by z on 6/18/2015.
 */
public class SponsorProjectActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor_project);

        TextView title      = (TextView)findViewById(R.id.title);
        TextView desc       = (TextView)findViewById(R.id.desc);
        final TextView amount     = (TextView)findViewById(R.id.amount);
        TextView project    = (TextView)findViewById(R.id.project);
        final RadioGroup amounts  = (RadioGroup)findViewById(R.id.amounts);
        final RadioGroup projects = (RadioGroup)findViewById(R.id.projects);
        Button confirm      = (Button)findViewById(R.id.confirm);

        title.setText("Sponsor a Project or Operational Need");
        desc.setText("Please indicate a total sponsorship amount.  " +
                "Each increment of $100 represents the number of " +
                "sponsors you are committing for.  You can sponsor " +
                "$100 or more per member in your family, or on behalf " +
                "of someone who has passed away.");
        amount.setText("Select one:");
        project.setText("Choose your area of sponsorship: ");
        confirm.setText("Confirm");

        Intent src          = getIntent();
        final String from   = src.getStringExtra("from");
        makeAmountRadios(from, amounts);
        makeProjectRadios(projects);

        confirm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SponsorProjectActivity.this, PaymentInfoActivity.class);
                        intent.putExtra("from", from);
                        intent.putExtra("amount", amounts.getCheckedRadioButtonId());
                        RadioButton proj = (RadioButton)findViewById(projects.getCheckedRadioButtonId());
                        intent.putExtra("notes", proj.getText().toString());
                        startActivity(intent);
                    }
                }
        );

    }

    private void makeAmountRadios(String from, RadioGroup amounts) {
        int increment = 0;

        if(from.equals("pow1kPost"))
            increment = 100;
        else if(from.equals("40x40Post"))
            increment = 2500;

        for(int i = 1; i <= 4; i++) {
            RadioButton rb = new RadioButton(this);
            rb.setText("$" + increment * i);
            rb.setId(increment * i);
            amounts.addView(rb);
        }

        amounts.check(increment * 1);
    }

    private void makeProjectRadios(RadioGroup projects) {
        Querier q = new Querier(this);

        for(int i = 1; i <= 8; i++) {
            RadioButton rb = new RadioButton(this);
            rb.setId(i * 1);
            q.textQuery("sponsorProject" + i, rb);
            projects.addView(rb);
        }

        projects.check(8 * 1);
    }
}
