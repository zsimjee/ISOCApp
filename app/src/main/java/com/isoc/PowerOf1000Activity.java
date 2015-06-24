package com.isoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by z on 5/20/2015.
 */
public class PowerOf1000Activity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_of_1000);

        TextView sponsors       = (TextView)findViewById(R.id.sponsors);
        ImageButton project    = (ImageButton)findViewById(R.id.project);
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
                        Intent intent = new Intent(PowerOf1000Activity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );


        Querier q = new Querier(this);
        q.appendTextQuery("pow1kCurSponsors", sponsors);

        project.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PowerOf1000Activity.this, SponsorProjectActivity.class);
                        intent.putExtra("from", "pow1k");
                        startActivity(intent);
                    }
                }
        );
    }
}
