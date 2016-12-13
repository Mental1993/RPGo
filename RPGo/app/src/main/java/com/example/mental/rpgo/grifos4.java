package com.example.mental.rpgo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class grifos4 extends AppCompatActivity {
    Button btn_Answer;
    EditText E_Answer;
    TextView Vtext;
    DatabaseHelper mydb;
    Boolean upd;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddle4);


        btn_Answer = (Button) findViewById(R.id.btnAnswer4);
        E_Answer = (EditText) findViewById(R.id.answer4);
        Vtext = (TextView) findViewById(R.id.thetext4);

        btn_Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (E_Answer.getText().toString().equals("Nothing") || E_Answer.getText().toString().equals("nothing")) {
                    Toast.makeText(getApplicationContext(), "Good", Toast.LENGTH_SHORT).show();
                    part2();
                } else {
                    Toast.makeText(getApplicationContext(), "Nope!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    void part2() {

        Vtext.setText(getResources().getString(R.string.grifos4_p2));
        E_Answer.setText("");
        btn_Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (E_Answer.getText().toString().equals("2")) {
                    Toast.makeText(getApplicationContext(), "Keep going!!", Toast.LENGTH_SHORT).show();
                    part3();
                } else {
                    Toast.makeText(getApplicationContext(), "A number!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void part3() {
        Vtext.setText(getResources().getString(R.string.grifos4_p3));
        E_Answer.setText("");
        btn_Answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (E_Answer.getText().toString().equals("Tomorrow") || E_Answer.getText().toString().equals("tomorrow")) {
                    Toast.makeText(getApplicationContext(), "Once again, you are correct..!", Toast.LENGTH_SHORT).show();

                    mydb = new DatabaseHelper(grifos4.this);
                    int setter = Integer.parseInt(Global.getNogrif()) + 1;
                    upd = mydb.update_user(setter, Integer.parseInt(Global.getId()));
                    Global.setNogrif(String.valueOf(setter));
                    mydb.close();
                    startActivity(new Intent(grifos4.this, Buttons.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Think..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("grifos4 Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
