package com.example.icode.electionbeater.Activities.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.icode.electionbeater.Activities.Adapters.ListViewAdapter;
import com.example.icode.electionbeater.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PortfolioCategories extends AppCompatActivity {

    ProgressDialog progressDialog;

    //ArrayAdapter<String> p_adapter;

    ListAdapter p_adapter;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_categories);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("CANDIDATE PORTFOLIO");
        getSupportActionBar().setLogo(R.mipmap.first_icon);

        listView = (ListView)findViewById(R.id.candidate_category);

        insertString();

    }

    //insert string values into the listview
    public void insertString(){

        String[] p_categories = {
                "President",
                "Vice President",
                "Secretary",
                "Financial Secretary",
                "Organiser Secretary",
                "General Secretary",
                "PRO"
        };

        //List<String> p_list =
             //  new ArrayList<String>(Arrays.asList(p_categories));

        p_adapter = new ListViewAdapter(
                //the current context
                 this,
                //Portfolio data
                p_categories);

        /*p_adapter = new ArrayAdapter<String>(
                //the current context
                this,
                //ID of the listView layout
                R.layout.activity_portfolio_categories,
                R.id.textView,
                //Portfolio data
                (List<String>) p_adapter);*/

        listView.setAdapter(p_adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String portfolio = String.valueOf(parent.getItemAtPosition(position));
                //open the castVoteActivity in order for the voter to cast vote
                Intent intentVote = new Intent(PortfolioCategories.this,CastVoteActivity.class);
                startActivity(intentVote);

                Toast.makeText(PortfolioCategories.this,"Welcome to the "+ portfolio +" Category", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = "";
        switch (item.getItemId()){
            case R.id.good_day:
                msg = "Welcome and enjoy voting!";
                Toast.makeText(PortfolioCategories.this,msg, Toast.LENGTH_LONG).show();
                break;
            case R.id.info:
                //open info page or activity
                Intent intent_info = new Intent(PortfolioCategories.this, App_Info.class);
                startActivity(intent_info);
                break;
            //logs the voter out of the system
            case R.id.logout:
                progressDialog = ProgressDialog.show(PortfolioCategories.this, "Logging Out...", null, true, true);
                progressDialog.show(); //displays the progress dialog
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                        progressDialog.dismiss();    //dismisses the alertDialog
                        timer.cancel();     //this will cancel the timer of the system
                    }
                }, 8000);   // the timer will count 4 seconds....
                Intent intent_logout = new Intent(PortfolioCategories.this, LoginActivity.class);
                startActivity(intent_logout);
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}
