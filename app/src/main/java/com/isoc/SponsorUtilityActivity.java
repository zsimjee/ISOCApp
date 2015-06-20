package com.isoc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by z on 6/18/2015.
 */
public class SponsorUtilityActivity extends Activity {

    private double gasUnit = 500;
    private double waterUnit = 500;
    private double trashUnit = 500;
    private double electricityUnit = 500;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor_utility);

        TextView title = (TextView) findViewById(R.id.title);
        TextView explanation = (TextView) findViewById(R.id.explanation);
        final TextView gas = (TextView) findViewById(R.id.gas);
        final EditText gasInput = (EditText) findViewById(R.id.gasInput);
        final TextView water = (TextView) findViewById(R.id.water);
        final EditText waterInput = (EditText) findViewById(R.id.waterInput);
        final TextView trash = (TextView) findViewById(R.id.trash);
        final EditText trashInput = (EditText) findViewById(R.id.trashInput);
        final TextView electricity = (TextView) findViewById(R.id.electricity);
        final EditText electricityInput = (EditText) findViewById(R.id.electricityInput);
        final TextView total = (TextView) findViewById(R.id.total);
        Button confirm = (Button) findViewById(R.id.confirm);


        title.setText("Sponsor A Utility Bill");
        explanation.setText("Support your institution!  There are various areas " +
                "where your help is needed. Enter the amount you wish to " +
                "donate to each.\n");
        gas.setText("Gas Bill Monthly: ");
        gasInput.setText("0");
        water.setText("Water Bill Monthly: ");
        waterInput.setText("0");
        trash.setText("Trash Bill Montly: ");
        trashInput.setText("0");
        electricity.setText("Electricity Bill Monthly: ");
        electricityInput.setText("0");
        total.setText("Total: $0");
        confirm.setText("Confirm");

        gas.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String gasText = gas.getText().toString();
                        gasUnit = Double.parseDouble(gasText.substring(gasText.indexOf("$") + 1));
                    }
                }
        );
        water.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String waterText = water.getText().toString();
                        waterUnit = Double.parseDouble(waterText.substring(waterText.indexOf("$") + 1));
                    }
                }
        );
        trash.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String trashText = trash.getText().toString();
                        trashUnit = Double.parseDouble(trashText.substring(trashText.indexOf("$") + 1));
                    }
                }
        );
        electricity.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String electricityText = electricity.getText().toString();
                        electricityUnit = Double.parseDouble(electricityText.substring(electricityText.indexOf("$") + 1));
                    }
                }
        );

        Querier q = new Querier(this);
        q.textQuery("gasBillAmt", gas);
        q.textQuery("waterBillAmt", water);
        q.textQuery("trashBillAmt", trash);
        q.textQuery("electricityBillAmt", electricity);

        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    double gasAmt = Integer.parseInt(gasInput.getText().toString()) * gasUnit;
                    double waterAmt = Integer.parseInt(waterInput.getText().toString()) * waterUnit;
                    double trashAmt = Integer.parseInt(trashInput.getText().toString()) * trashUnit;
                    double electricityAmt = Integer.parseInt(electricityInput.getText().toString()) * electricityUnit;

                    total.setText("Total: $" + (gasAmt + waterAmt + trashAmt + electricityAmt));
                } catch (Exception e) {
                }
            }
        };

        gasInput.addTextChangedListener(tw);
        waterInput.addTextChangedListener(tw);
        trashInput.addTextChangedListener(tw);
        electricityInput.addTextChangedListener(tw);

        final Context c = this;

        confirm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SponsorUtilityActivity.this, PaymentInfoActivity.class);
                        intent.putExtra("from", "general");
                        //TODO

                        double gasAmt = Integer.parseInt(gasInput.getText().toString()) * gasUnit;
                        double waterAmt = Integer.parseInt(waterInput.getText().toString()) * waterUnit;
                        double trashAmt = Integer.parseInt(trashInput.getText().toString()) * trashUnit;
                        double electricityAmt = Integer.parseInt(electricityInput.getText().toString()) * electricityUnit;
                        double amt = gasAmt + waterAmt + trashAmt + electricityAmt;
                        if (amt == 0) {
                            Toast toast = Toast.makeText(c, "Please enter an amount", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            intent.putExtra("amount", amt);
                            intent.putExtra("notes", "gas:" + gasAmt +
                                    ",water:" + waterAmt + ",trash:" + trashAmt +
                                    "electricity:" + electricityAmt);

                            startActivity(intent);
                        }
                    }
                }
        );
    }

    /*private void setGasUnit(int gasUnit) {
        this.gasUnit = gasUnit;
    }
    private void setWaterUnit(int waterUnit) {
        this.waterUnit = waterUnit;
    }
    private void setTrashUnit(int trashUnit) {
        this.trashUnit = trashUnit;
    }
    private void setElectricityUnit(int electricityUnit) {
        this.electricityUnit = electricityUnit;
    }*/

}
