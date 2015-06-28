package com.isoc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by z on 5/20/2015.
 */
public class ZakatActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakat);

        TextView link               =(TextView)findViewById(R.id.link);
        TextView amount             = (TextView)findViewById(R.id.amount);
        final EditText amountInput  = (EditText)findViewById(R.id.amountInput);
        final TextView total              = (TextView)findViewById(R.id.total);
        LinearLayout confirm          = (LinearLayout)findViewById(R.id.confirm);
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
                        Intent intent = new Intent(ZakatActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

        total.setText("$0");

        link.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "http://www.zakat.org/donate/zakat-calculator/";

                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(browserIntent);
                    }
                }
        );

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
                        try {
                            total.setText("$" + Integer.parseInt(amountInput.getText().toString()));
                        } catch (Exception e) {
                            total.setText("$0");
                        }
                    }
                }
        );

        final Context c = this;

        confirm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (total.getText().toString().equals("$0"))
                            Toast.makeText(c,
                                    "Please enter an amount",
                                    Toast.LENGTH_SHORT).show();
                        else {
                            Intent intent = new Intent(ZakatActivity.this, PaymentInfoActivity.class);
                            intent.putExtra("from", "zakat");
                            intent.putExtra("amount", Integer.parseInt(amountInput.getText().toString()));
                            startActivity(intent);
                        }
                    }
                }
        );

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

}
