package com.isoc;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by z on 5/20/2015.
 */
public class BanquetTicketsActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banquet_tickets);

        /*ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("BANQUET TICKETS");
        actionBar.setDisplayShowCustomEnabled(true);
        View customView=getLayoutInflater().inflate(R.layout.activity_banquet_tickets, null);
        actionBar.setCustomView(customView);*/

        TextView title 	                = (TextView)findViewById(R.id.title);
        TextView desc                   = (TextView)findViewById(R.id.desc);
        TextView date                   = (TextView)findViewById(R.id.date);
        TextView loc                    = (TextView)findViewById(R.id.loc);
        TextView sched1                 = (TextView)findViewById(R.id.sched1);
        TextView sched2                 = (TextView)findViewById(R.id.sched2);
        TextView sched3                 = (TextView)findViewById(R.id.sched3);
        TextView sched4                 = (TextView)findViewById(R.id.sched4);
        final EditText individualInput    = (EditText)findViewById(R.id.individualInput);
        final TextView individual             = (TextView)findViewById(R.id.individual);
        final EditText tableInput         = (EditText)findViewById(R.id.tableInput);
        TextView table                  = (TextView)findViewById(R.id.table);
        final EditText babysittingInput   = (EditText)findViewById(R.id.babysittingInput);
        final TextView babysitting            = (TextView)findViewById(R.id.babysitting);
        LinearLayout totalContainer     = (LinearLayout)findViewById(R.id.totalContainer);
        final TextView total                  = (TextView)findViewById(R.id.total);
        LinearLayout confirm                  = (LinearLayout) findViewById(R.id.confirm);

        Querier q = new Querier(this);

        q.appendTextQuery("banquetTitle", title);
        q.appendTextQuery("banquetDesc", desc);
        q.appendTextQuery("banquetDate", date);
        q.appendTextQuery("banquetLoc", loc);
        q.appendTextQuery("banquetSched1", sched1);
        q.appendTextQuery("banquetSched2", sched2);
        q.appendTextQuery("banquetSched3", sched3);
        q.appendTextQuery("banquetSched4", sched4);

        individual.setText("$60 Individual Ticket");
        table.setText("$600 Table of 10");
        babysitting.setText("$30 Babysitting (3-12 years)");

        individualInput.setText("0");
        tableInput.setText("0");
        babysittingInput.setText("0");

        WindowManager wm = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x / 2;
        confirm.setMinimumWidth(width);
        totalContainer.setMinimumWidth(width);

        total.setText("$0");

        individualInput.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(individualInput.getText().toString().equals(""))
                            individualInput.setText("0");
                        try {
                            total.setText("$" +
                                    (Integer.parseInt(individualInput.getText().toString()) * 60
                                            + Integer.parseInt(tableInput.getText().toString()) * 600
                                            + Integer.parseInt(babysittingInput.getText().toString()) * 30));
                        }catch(Exception e){}
                    }
                }
        );
        tableInput.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(tableInput.getText().toString().equals(""))
                            tableInput.setText("0");
                        try {
                            total.setText("$" +
                                    (Integer.parseInt(individualInput.getText().toString()) * 60
                                            + Integer.parseInt(tableInput.getText().toString()) * 600
                                            + Integer.parseInt(babysittingInput.getText().toString()) * 30));
                        }catch(Exception e){}
                    }
                }
        );
        babysittingInput.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(babysittingInput.getText().toString().equals(""))
                            babysittingInput.setText("0");

                        try {
                            total.setText("$" +
                                    (Integer.parseInt(individualInput.getText().toString()) * 60
                                            + Integer.parseInt(tableInput.getText().toString()) * 600
                                            + Integer.parseInt(babysittingInput.getText().toString()) * 30));
                        }catch(Exception e){}
                    }
                }
        );

        confirm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (total.getText().toString().trim().equals("$0"))
                            Toast.makeText(getApplicationContext(),
                                    "Please select the amount of tickets you would like to purchase",
                                    Toast.LENGTH_SHORT).show();
                        else {
                            Intent intent = new Intent(BanquetTicketsActivity.this, PaymentInfoActivity.class);
                            intent.putExtra("from", "banquet");
                            try {
                                intent.putExtra("notes",
                                        "individual:" + (Integer.parseInt(individualInput.getText().toString()) * 60) +
                                                ",table:" + (Integer.parseInt(tableInput.getText().toString()) * 600) +
                                                ",babysitting:" + (Integer.parseInt(babysittingInput.getText().toString()) * 30));
                            } catch (Exception e) {
                            }
                            startActivity(intent);
                        }
                    }
                }
        );

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
