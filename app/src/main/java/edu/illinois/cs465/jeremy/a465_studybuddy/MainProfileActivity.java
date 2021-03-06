package edu.illinois.cs465.jeremy.a465_studybuddy;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainProfileActivity extends MainMapsActivity {

    private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // classes[] is using dynamic view, so first get an inflater than set the view
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_main_profile, null);

        back = v.findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                finish();
            }
        });

        setContentView(R.layout.activity_main_profile);
        TextView nameTV = v.findViewById(R.id.profile_name);
        nameTV.setText("Jordan Clarkson");
        nameTV.setTextSize(30);

        TextView userString = v.findViewById(R.id.profile_user_string);
        userString.setText("AEROSPACE ENGINEERING CLASS OF 2020");
        userString.setTextSize(20);

        String[] classes = {"CS465", "CS425", "ECE400"};
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
