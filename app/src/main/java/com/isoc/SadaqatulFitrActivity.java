package com.isoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Created by z on 5/20/2015.
 */
public class SadaqatulFitrActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sadaqatul_fitr);


        TextView explanation    = (TextView)findViewById(R.id.explanation);
        final NumberPicker people     = (NumberPicker)findViewById(R.id.people);
        TextView peopleText     = (TextView)findViewById(R.id.peopleText);
        final TextView total          = (TextView)findViewById(R.id.total);
        Button confirm          = (Button)findViewById(R.id.confirm);


        explanation.setText("Sadaqatul Fitr is $10 per person in the family.\n" +
                "It should be paid before Eid.");
        people.setMinValue(1);
        people.setMaxValue(100);
        peopleText.setText(" number of people in your family");
        total.setText("Total: $" + people.getValue() * 10);
        confirm.setText("Confirm");

        people.setOnValueChangedListener(
                new NumberPicker.OnValueChangeListener() {
                    @Override
                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                        total.setText("Total: $" + newVal * 10);
                    }
                }
        );

        confirm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SadaqatulFitrActivity.this, PaymentInfoActivity.class);
                        intent.putExtra("from", "fitrPost");
                        intent.putExtra("amount", people.getValue() * 10);
                        startActivity(intent);
                    }
                }
        );

    }
}
