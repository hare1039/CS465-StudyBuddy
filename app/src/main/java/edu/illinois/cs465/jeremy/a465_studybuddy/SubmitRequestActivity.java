package edu.illinois.cs465.jeremy.a465_studybuddy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubmitRequestActivity extends Activity {

    public static boolean isSubmit;
    public static String my_request;

    private EditText edit;
    private Button cancel, submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_request);

        edit = (EditText) findViewById(R.id.edit);
        cancel = (Button) findViewById(R.id.cancel_but);
        submit = (Button) findViewById(R.id.submit_but);


        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                my_request = edit.getText().toString();


                isSubmit = true;
                startActivity(new Intent(SubmitRequestActivity.this, PeoReqTabs.class));
                finish();

            }
        });
    }
}
