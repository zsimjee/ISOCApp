package com.isoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by z on 6/17/2015.
 */
public class CardConfirmationActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_confirmation);

        Intent source = getIntent();
        Querier q = new Querier(this);
        String from = getIntent().getStringExtra("from");

        TextView confirmation = (TextView)findViewById(R.id.confirmation);

        q.appendTextQuery(from + "PostCCPayment", confirmation);

    }

    public void onBackPressed(){
        Intent intent = new Intent(CardConfirmationActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
