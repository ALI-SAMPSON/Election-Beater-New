package com.example.icode.electionbeater.Activities.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.icode.electionbeater.Activities.Models.BallotBox;
import com.example.icode.electionbeater.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewResults extends AppCompatActivity {

    //reference to the listview Object
    ListView listView;

    //reference to the FirebaseDatabase object
    FirebaseDatabase database;

    //reference to the DatabaseReference object
    DatabaseReference ballotboxRef;


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

        database = FirebaseDatabase.getInstance();
        ballotboxRef = database.getReference().child("BallotBox");

        listView = (ListView)findViewById(R.id.viewResults_listView);

        viewResults();
    }

    public void viewResults(){

        FirebaseListAdapter<BallotBox> adapter = new FirebaseListAdapter<BallotBox>(
                ViewResults.this,
                BallotBox.class,
                R.layout.view_results_list_item,
                ballotboxRef)
        {
            @Override
            protected void populateView(View v, BallotBox model, int position) {
                //get reference to the IDs of the textViews and the imageView defined in the .xml file
                ImageView imageView = (ImageView)v.findViewById(R.id.candidate_imageView);
                TextView candidate_id = (TextView)v.findViewById(R.id.candidate_id);
                TextView full_name = (TextView)v.findViewById(R.id.full_name);
                TextView programme = (TextView)v.findViewById(R.id.programme);
                TextView portfolio = (TextView)v.findViewById(R.id.portfolio);
                TextView numberOfVotes = (TextView)v.findViewById(R.id.numberOfVotes);

                  /*sets the text of the textview and the image of the ImageView
                to all the details retrieved from the database
                 */
                BallotBox ballotBox = (BallotBox) model;
                Glide.with(ViewResults.this).load(ballotBox.getImageAddress()).into(imageView);
                candidate_id.setText("Candidate ID : " + ballotBox.getCandidate_id());
                full_name.setText("Fullname :  " + ballotBox.getFull_name());
                programme.setText("Programme : " + ballotBox.getProgramme());
                portfolio.setText("Portfolio : " + ballotBox.getPortfolio());
                numberOfVotes.setText("Number of Votes : " + ballotBox.getNumberOfvotes());
            }
        };
        listView.setAdapter(adapter);

    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onStop(){
        super.onStop();
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
                msg = "Hello, enjoy viewing the results of this election!";
                Toast.makeText(ViewResults.this, msg, Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
