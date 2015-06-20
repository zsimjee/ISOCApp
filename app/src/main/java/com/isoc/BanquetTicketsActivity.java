package com.isoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by z on 5/20/2015.
 */
public class BanquetTicketsActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banquet_tickets);

        TextView title 	                = (TextView)findViewById(R.id.title);
        TextView desc                   = (TextView)findViewById(R.id.desc);
        TextView date                   = (TextView)findViewById(R.id.date);
        TextView loc                    = (TextView)findViewById(R.id.loc);
        TextView sched1                 = (TextView)findViewById(R.id.sched1);
        TextView sched2                 = (TextView)findViewById(R.id.sched2);
        TextView sched3                 = (TextView)findViewById(R.id.sched3);
        TextView sched4                 = (TextView)findViewById(R.id.sched4);
        final NumberPicker individualInput    = (NumberPicker)findViewById(R.id.individualInput);
        TextView individual             = (TextView)findViewById(R.id.individual);
        final NumberPicker tableInput         = (NumberPicker)findViewById(R.id.tableInput);
        TextView table                  = (TextView)findViewById(R.id.table);
        final NumberPicker babysittingInput   = (NumberPicker)findViewById(R.id.babysittingInput);
        TextView babysitting            = (TextView)findViewById(R.id.babysitting);
        final TextView total                  = (TextView)findViewById(R.id.total);
        Button confirm                  = (Button) findViewById(R.id.confirm);

        Querier q = new Querier(this);

        q.textQuery("banquetTitle", title);
        q.textQuery("banquetDesc", desc);
        q.textQuery("banquetDate", date);
        q.textQuery("banquetLoc", loc);
        q.textQuery("banquetSched1", sched1);
        q.textQuery("banquetSched2", sched2);
        q.textQuery("banquetSched3", sched3);
        q.textQuery("banquetSched4", sched4);

        individual.setText("$60 Individual Ticket");
        table.setText("$600 Table of 10");
        babysitting.setText("$30 Babysitting (3-12 years)");

        individualInput.setMinValue(0);
        individualInput.setMaxValue(20);
        tableInput.setMinValue(0);
        tableInput.setMaxValue(5);
        babysittingInput.setMinValue(0);
        babysittingInput.setMaxValue(20);

        confirm.setText("Confirm");

        total.setText("Total: $0");

        NumberPicker.OnValueChangeListener changed = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                total.setText("Total: $" +
                        (individualInput.getValue() * 60 + tableInput.getValue() * 600
                         + babysittingInput.getValue() * 30));
            }
        };

        individualInput.setOnValueChangedListener(changed);
        tableInput.setOnValueChangedListener(changed);
        babysittingInput.setOnValueChangedListener(changed);

        confirm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(total.getText().toString().trim().equals("Total: $0"))
                            Toast.makeText(getApplicationContext(),
                                    "Please select the amount of tickets you would like to purchase",
                                    Toast.LENGTH_SHORT).show();
                        else {
                            Intent intent = new Intent(BanquetTicketsActivity.this, PaymentInfoActivity.class);
                            intent.putExtra("from", "banquet");
                            intent.putExtra("notes",
                                    "individual:" + (individualInput.getValue() * 60) +
                                    ",table:" + (tableInput.getValue() * 600) +
                                    ",babysitting:" + (babysittingInput.getValue() * 30));
                            intent.putExtra("amount", individualInput.getValue() * 60 + tableInput.getValue() * 600 + babysittingInput.getValue() * 30);
                            startActivity(intent);
                        }
                    }
                }
        );

    }

}
