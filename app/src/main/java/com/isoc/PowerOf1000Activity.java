package com.isoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by z on 5/20/2015.
 */
public class PowerOf1000Activity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_of_1000);

        TextView sponsors       = (TextView)findViewById(R.id.sponsors);
        TextView desc           = (TextView)findViewById(R.id.desc);
        LinearLayout project    = (LinearLayout)findViewById(R.id.project);
        TextView projectText    = (TextView)findViewById(R.id.projectText);


        Querier q = new Querier(this);
        sponsors.setText("Current Number of Sponsors: ");
        q.textQuery("pow1kCurSponsors", sponsors);

        desc.setText("Help your Masjid reach it's goal of 1000 sponsors pledging one of the following");
        projectText.setText("Sponsor a Project or Operational Need");

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
