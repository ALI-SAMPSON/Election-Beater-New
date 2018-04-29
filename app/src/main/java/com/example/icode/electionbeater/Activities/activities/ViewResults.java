package com.example.icode.electionbeater.Activities.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.icode.electionbeater.R;

public class ViewResults extends AppCompatActivity {

    //button to start the election
    Button startButton;

    //button to view election results
    Button viewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_results);


        startButton = (Button)findViewById(R.id.startBtn);
        viewButton = (Button)findViewById(R.id.viewBtn);
    }


}
