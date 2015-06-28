package com.isoc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by z on 5/20/2015.
 */
public class JoinACommitteeActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_a_committee);

        LinearLayout scroll = (LinearLayout) findViewById(R.id.committees);
        final CheckBox checkNewsletter = (CheckBox) findViewById(R.id.checkNewsletter);
        TextView newsletter = (TextView) findViewById(R.id.newsletter);
        final CheckBox checkMember = (CheckBox) findViewById(R.id.checkMember);
        TextView member = (TextView) findViewById(R.id.member);
        LinearLayout next = (LinearLayout) findViewById(R.id.next);
        ImageButton back = (ImageButton) findViewById(R.id.back);
        ImageButton home = (ImageButton) findViewById(R.id.home);

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
                        Intent intent = new Intent(JoinACommitteeActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

        checkNewsletter.setText("Sign up for ISOC's email newsletter and community alerts");
        checkMember.setText("Contact me with information on becoming an ISOC Member");

        Querier q = new Querier(this);
        final ArrayList<CheckBox> committees = new ArrayList<CheckBox>();

        for (int i = 1; i <= 8; i++) {
            LinearLayout parent = new LinearLayout(this);
            parent.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout textParent = new LinearLayout(this);
            textParent.setOrientation(LinearLayout.VERTICAL);

            CheckBox committee = new CheckBox(this);
            TextView name = new TextView(this);
            name.setTextColor(Color.parseColor("#3A82B0"));
            TextView desc = new TextView(this);
            desc.setTextColor(Color.parseColor("#000000"));

            q.appendTextQuery("Committee" + i, name);
            q.appendTextQuery("CommitteeDesc" + i, desc);

            committees.add(committee);

            textParent.addView(name);
            textParent.addView(desc);

            parent.addView(committee);
            parent.addView(textParent);
            scroll.addView(parent);
        }

        final Context c = this;

        next.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String committeeNums = "";

                        for (int i = 1; i <= 8; i++)
                            if (committees.get(i - 1).isChecked())
                                committeeNums += i + ",";


                        if (committeeNums.length() == 0 && !checkNewsletter.isChecked() && !checkMember.isChecked())
                            Toast.makeText(c, "Please select at least one option", Toast.LENGTH_SHORT).show();

                        else {
                            Intent intent = new Intent(JoinACommitteeActivity.this, PersonalInformationActivity.class);
                            intent.putExtra("committees", committeeNums.substring(0, committeeNums.length()-1));
                            intent.putExtra("newsletter", checkNewsletter.isChecked());
                            intent.putExtra("member", checkMember.isChecked());
                            startActivity(intent);
                        }
                    }
                });
    }

}
