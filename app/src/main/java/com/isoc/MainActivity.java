package com.isoc;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private final String[] MENU_OPTIONS = {
            "Banquet Tickets",
            "Join a Committee",
            "Eid Prayer Info",
            "Sadaqatul Fitr",
            "General Donation",
            "Power of 1000",
            "Sponsor an Iftar",
            "Today at ISOC",
            "Zakat"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
//                Toast.makeText(MainActivity.this, MENU_OPTIONS[position],
//                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();

                switch(position) {
                    case 0:
                        intent = new Intent(MainActivity.this, BanquetTicketsActivity.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, JoinACommitteeActivity.class);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, EidPrayerInfoActivity.class);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, SadaqatulFitrActivity.class);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, GeneralDonationActivity.class);
                        break;
                    case 5:
                        intent = new Intent(MainActivity.this, PowerOf1000Activity.class);
                        break;
                    case 6:
                        intent = new Intent(MainActivity.this, SponsorAnIftarActivity.class);
                        break;
                    case 7:
                        intent = new Intent(MainActivity.this, TodayAtISOCActivity.class);
                        break;
                    case 8:
                        intent = new Intent(MainActivity.this, ZakatActivity.class);
                        break;
                }

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}