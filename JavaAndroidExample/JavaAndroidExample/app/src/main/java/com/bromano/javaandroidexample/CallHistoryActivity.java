package com.bromano.javaandroidexample;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;


public class CallHistoryActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_history);

        ArrayList<String> callHistory;

        Intent intent = getIntent();

        //Use empty array if data passed is null
        if(intent.getStringArrayListExtra("call_history") != null){
            callHistory = intent.getStringArrayListExtra("call_history");
        }else{
            callHistory = new ArrayList<String>();
        }

        //Set ListView
        ListAdapter phoneListAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,callHistory);
        setListAdapter(phoneListAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_call_history, menu);
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
