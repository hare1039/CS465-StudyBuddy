package edu.illinois.cs465.jeremy.a465_studybuddy;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableRow;
import android.widget.Toast;

public class MainPeopleFeedActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_main_people_feed, null);

        Account[] accounts = {
                new Account(
                        "NEED HELP ON CS465 UI HW3",
                        "@drawable/list",
                        "Jordan Clark"),
                new Account(
                        "NEED HELP ON CS465 UI HW4",
                        "@drawable/list",
                        "Jordan Dlark"),
                new Account(
                        "NEED HELP ON CS465 UI HW5",
                        "@drawable/list",
                        "Jordan Elark")
        };
        for (Account account : accounts) {
            /*
                <TableRow
                    android:layout_margin="4dp"
                    android:background="@drawable/border"
                    android:clickable="true"
                    android:spinnerMode="dialog" >
                    <LinearLayout
                        android:layout_width="match_parent"
                        android:layout_height="100dp"
                        android:gravity="center"
                        android:orientation="vertical">
                        <ImageView
                            android:layout_width="100dp"
                            android:layout_height="80dp"
                            android:gravity="center"
                            android:src="@drawable/list" />
                        <TextView
                            android:layout_width="100dp"
                            android:layout_height="20dp"
                            android:gravity="center"
                            android:text="Jordan Clark"/>
                    </LinearLayout>

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="match_parent"
                        android:gravity="center_vertical"
                        android:ellipsize="none"
                        android:maxLines="100"
                        android:singleLine="false"
                        android:scrollHorizontally="false"
                        android:text="NEED HELP ON CS465 UI HW3" />
                </TableRow>
             */

            TableRow.LayoutParams param = new TableRow.LayoutParams();
            param.setMargins(4, 4, 4, 4);
            TableRow tr = new TableRow(this);
            int id = View.generateViewId();
            tr.setId(id);
            tr.setBackground(getResources().getDrawable(R.drawable.border));

            // haven't fully setup using java, but xml is fine
        }

        setContentView(v);
        findViewById(R.id.people_list).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Pressed", Toast.LENGTH_SHORT).show();
    }
}
