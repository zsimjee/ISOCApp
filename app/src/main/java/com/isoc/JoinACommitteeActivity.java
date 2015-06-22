package com.isoc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by z on 5/20/2015.
 */
public class JoinACommitteeActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_a_committee);

        TextView description        = (TextView)findViewById(R.id.description);
        LinearLayout scroll         = (LinearLayout)findViewById(R.id.committees);
        final CheckBox checkNewsletter    = (CheckBox)findViewById(R.id.checkNewsletter);
        TextView newsletter         = (TextView)findViewById(R.id.newsletter);
        final CheckBox checkMember        = (CheckBox)findViewById(R.id.checkMember);
        TextView member             = (TextView)findViewById(R.id.member);
        LinearLayout next                 = (LinearLayout)findViewById(R.id.next);

        newsletter.setText("Sign up for ISOC's email newsletter and community alerts");
        member.setText("Contact me with information on becoming an ISOC Member");

        Querier q = new Querier(this);
        final ArrayList<CheckBox> committees = new ArrayList<CheckBox>();

        for(int i = 1; i <= 10; i++) {
            LinearLayout parent = new LinearLayout(this);
            parent.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout textParent = new LinearLayout(this);
            textParent.setOrientation(LinearLayout.VERTICAL);

            CheckBox committee = new CheckBox(this);
            TextView name = new TextView(this);
            TextView desc = new TextView(this);

            q.appendTextQuery("Committee" + i, name);
            q.appendTextQuery("CommitteeDesc" + i, desc);

            committees.add(committee);

            textParent.addView(name);
            textParent.addView(desc);

            parent.addView(committee);
            parent.addView(textParent);
            scroll.addView(parent);
        }

        next.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(JoinACommitteeActivity.this, PersonalInformationActivity.class);

                        ArrayList<Integer> committeeNums = new ArrayList<Integer>();

                        for(int i = 1; i <= 10; i++)
                            if(committees.get(i-1).isChecked())
                                committeeNums.add(i);

                        intent.putExtra("committees", committeeNums.toArray());
                        intent.putExtra("newsletter", checkNewsletter.isChecked());
                        intent.putExtra("member", checkMember.isChecked());
                        startActivity(intent);
                    }
                }
        );

    }

}
