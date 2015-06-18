package com.isoc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by z on 5/20/2015.
 */
public class GeneralDonationActivity extends Activity {

    private final String[] fundsDestinations = {
            "Masjid",
            "Sadaqa",
            "Orange Crescent School",
            "Social Service, Assistance Programs",
            "Funeral Fund"
    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_donation);

        ListView list = (ListView)findViewById(R.id.fund_destinations);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fundsDestinations);
        list.setAdapter(adapter);

    }

    //total
    //confirm

}
