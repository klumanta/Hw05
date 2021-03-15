package com.example.kenanlumantas_simple;

import com.example.kenanlumantas_simple.utilities.NetworkUtils;

import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mSearchResultsDisplay;
    private EditText mSearchTermEditText;
    private Button mMeteorButton;
    private Button mResetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connect with visual elements in activity_main.xml
        mSearchResultsDisplay = (TextView) findViewById(R.id.tv_display_text);
        mSearchTermEditText = (EditText) findViewById(R.id.et_search_box);
        mMeteorButton = (Button) findViewById(R.id.meteors_button);
        mResetButton = (Button) findViewById(R.id.reset_button);

        makeMeteorNetworkSearchQuery();

        // Respond to search button
        mMeteorButton.setOnClickListener(
            new View.OnClickListener(){
                public void onClick(View v){
                    // Get search string from user
                    String searchText = mSearchTermEditText.getText().toString();

                    // Get text from mSearchResultsDisplayText
                    String meteors = mSearchResultsDisplay.getText().toString();
                    // Convert to a list
                    String[] meteorsList = meteors.split("\n");

                    // Search for match
                    for(String name : meteorsList){
                        if(name.toLowerCase().equals(searchText.toLowerCase())){
                            mSearchResultsDisplay.setText(name);
                            break;
                        }else{
                            mSearchResultsDisplay.setText("No results match.");
                        }
                    }
                }
            }
        );

        mResetButton.setOnClickListener(
            new View.OnClickListener(){
                public void onClick(View v){
                    // Reset the text
                    makeMeteorNetworkSearchQuery();
                }
            }
        );

    }

    public void makeMeteorNetworkSearchQuery(){
        new FetchMeteorNetworkData().execute();
    }

    public class FetchMeteorNetworkData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params){
            // Get the search term
            String urlString = "https://data.nasa.gov/resource/gh4g-9sfh.json";
            URL searchUrl = NetworkUtils.buildUrl(urlString);

            // Get the response from the URl
            String responseString = null;
            try{
                responseString = NetworkUtils.getResponseFromUrl(searchUrl);
            }catch(Exception e){
                e.printStackTrace();
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String responseData){
            ArrayList<String> titles = NetworkUtils.parseMeteorsJson(responseData);
            // Display entries in GUI
            mSearchResultsDisplay.setText("Meteor Results:");
            for(String title: titles){
                mSearchResultsDisplay.append("\n\n" + title);
            }
        }
    }

    // Connect with menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int menuItemSelected = item.getItemId();

        if(menuItemSelected == R.id.menu_contact){
            Class destinationActivity = ContactActivity.class;

            // Create intent to go to next page
            Intent startAboutActivityIntent = new Intent(MainActivity.this, destinationActivity);

            String msg = mSearchTermEditText.getText().toString();
            startAboutActivityIntent.putExtra(Intent.EXTRA_TEXT, msg);

            startActivity(startAboutActivityIntent);
            Log.d("info", "Contact Activity launched");
        }
        return true;
    }

}