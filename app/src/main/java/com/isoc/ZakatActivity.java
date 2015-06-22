package com.isoc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
        final TextView total              = (TextView)findViewById(R.id.total);
        LinearLayout confirm          = (LinearLayout)findViewById(R.id.confirm);

        total.setText("$0");
        amountInput.setText("0");

        Querier q = new Querier(this);
        q.appendTextQuery("zakatDesc", explanation);

        amount.setText("Enter Amount: ");

        amountInput.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(amountInput.getText().toString().equals(""))
                            amountInput.setText("0");
                        try {
                            total.setText("$" + amountInput.getText());
                        } catch(Exception e) {}
                    }
                }
        );

        confirm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ZakatActivity.this, PaymentInfoActivity.class);
                        intent.putExtra("from", "zakat");
                        intent.putExtra("amount", amountInput.getText().toString());
                        startActivity(intent);
                    }
                }
        );

    }


}
