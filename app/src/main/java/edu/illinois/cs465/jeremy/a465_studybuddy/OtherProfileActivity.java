package edu.illinois.cs465.jeremy.a465_studybuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class OtherProfileActivity extends MainMapsActivity {

    private ImageButton back;
    private Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // classes[] is using dynamic view, so first get an inflater than set the view
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.other_profile, null);
        send = v.findViewById(R.id.message_button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Need to send messages
                startActivity(new Intent(OtherProfileActivity.this, message_screen.class));
            }
        });
        back = v.findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                finish();
            }
        });

        setContentView(R.layout.other_profile);
        TextView nameTV = v.findViewById(R.id.profile_name);
        nameTV.setText("Susan Ann");
        nameTV.setTextSize(30);

        TextView userString = v.findViewById(R.id.profile_user_string);
        userString.setText("Computer Science of 2020");
        userString.setTextSize(20);

        String[] classes = {"CS241", "CS233", "CS498"};
        LinearLayout ll = v.findViewById(R.id.profile_classes);

        for (String s : classes) {
            TextView tv = new TextView(this);
            tv.setText(s);
            tv.setTextSize(20);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams (
                    new TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                                 LinearLayout.LayoutParams.WRAP_CONTENT,
                                        1f)
            );
            ll.addView(tv);
        }

        TextView collab_pt = v.findViewById(R.id.profile_collab_point);
        Integer point = 649;
        collab_pt.setText(point.toString());

        setContentView(v);
    }
}
