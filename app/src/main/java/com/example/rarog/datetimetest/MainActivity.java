package com.example.rarog.datetimetest;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.ClipboardManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String formattedDate = "";
        Date now = new Date();

        formattedDate += "Getting default locale datetime formatting\n"
                + "(DateFormat.getDateInstance(), DateFormat.getDateTimeInstance(), DateFormat.getTimeInstance())\n"
                + "----------\n\n";

        DateFormat[] formats = new DateFormat[] {
                DateFormat.getDateInstance(),
                DateFormat.getDateTimeInstance(),
                DateFormat.getTimeInstance(),
        };
        for (DateFormat df : formats) {
            SimpleDateFormat sdf = (SimpleDateFormat)df;
            formattedDate += sdf.toPattern() + ": " + df.format(now) + "\n";
        }

        TextView ptv = (TextView) findViewById(R.id.mainTextView);
        ptv.setText(formattedDate);
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
        if (id == R.id.action_copy) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            TextView ptv = (TextView) findViewById(R.id.mainTextView);
            ClipData clip = ClipData.newPlainText("DateTimeTest results", ptv.getText());
            clipboard.setPrimaryClip(clip);
            return true;
        }
        else if (id == R.id.action_exit) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
