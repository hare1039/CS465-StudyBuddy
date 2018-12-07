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

        setContentView(v);
        findViewById(R.id.people_list).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Pressed", Toast.LENGTH_SHORT).show();
    }
}
