package com.bromano.javaandroidexample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpInetConnection;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get views
        final EditText phoneNumberText = (EditText)findViewById(R.id.phoneNumberText);
        Button callButton = (Button) findViewById(R.id.callButton);
        Button callHistoryButton = (Button) findViewById(R.id.callHistoryButton);

        final ArrayList<String> callHistory = new ArrayList<String>();

        //Set up click event for call button
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder callDialog = new AlertDialog.Builder(MainActivity.this);
                final String phoneNumber = phoneNumberText.getText().toString();

                callDialog.setMessage("Call " + phoneNumber + "?");

                //Start Call Activity
                callDialog.setNeutralButton("Call", new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int id){
                        //Add phone number to call history
                        callHistory.add(phoneNumber);

                        //Start intent
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + phoneNumber));
                        startActivity(callIntent);
                    }
                });

                //Do Nothing on cancel
                callDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

                callDialog.create().show();
            }
        });

        //Set up click event for call history button
        callHistoryButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //Start CallHistoryActivity and pass data through intent.extras
                Intent intent = new Intent(MainActivity.this,CallHistoryActivity.class);
                intent.putExtra("call_history", callHistory);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
