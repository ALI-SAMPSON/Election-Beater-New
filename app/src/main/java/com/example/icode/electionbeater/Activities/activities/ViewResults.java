package com.example.icode.electionbeater.Activities.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.icode.electionbeater.R;

public class ViewResults extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_results);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("VIEW RESULTS");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_black);


        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_admin,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        String msg;
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intentBack = new Intent(ViewResults.this,ManageElection.class);
                intentBack.addFlags(intentBack.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentBack);
                break;
            case R.id.about:
                Intent intentAbout = new Intent(ViewResults.this, App_Info_Admin.class);
                intentAbout.addFlags(intentAbout.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intentAbout);
                break;
            case R.id.good_day:
                msg = "Hello, Welcome Back!";
                Toast.makeText(ViewResults.this, msg, Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
