package com.isoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Created by z on 5/20/2015.
 */
public class ZakatActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakat);


        TextView explanation        = (TextView)findViewById(R.id.explanation);
        TextView amount             = (TextView)findViewById(R.id.amount);
        final EditText amountInput  = (EditText)findViewById(R.id.amountInput);
        Button confirm          = (Button)findViewById(R.id.confirm);


        Querier q = new Querier(this);
        q.textQuery("zakatDesc", explanation);

        amount.setText("Enter Amount: ");
        confirm.setText("Confirm");


        confirm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ZakatActivity.this, PaymentInfoActivity.class);
                        intent.putExtra("from", "zakatPost");
                        intent.putExtra("amount", amountInput.getText().toString());
                        startActivity(intent);
                    }
                }
        );

    }


}
