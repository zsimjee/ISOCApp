package com.isoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by z on 6/17/2015.
 */
public class PersonConfirmationActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_confirmation);

        Intent source = getIntent();
        Querier q = new Querier(this);
        String from = getIntent().getStringExtra("from");

        TextView confirmation = (TextView)findViewById(R.id.confirmation);

        q.appendTextQuery(from + "PostPayInPerson", confirmation);

    }

    public void onBackPressed(){
        Intent intent = new Intent(PersonConfirmationActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
