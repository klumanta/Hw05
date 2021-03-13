package com.example.kenanlumantas_simple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mSearchResultsDisplay;
    private EditText mSearchTermEditText;
    private Button mSearchButton;
    private Button mResetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connect with visual elements in activity_main.xml
        mSearchResultsDisplay = (TextView) findViewById(R.id.tv_display_text);
        mSearchTermEditText = (EditText) findViewById(R.id.et_search_box);
        mSearchButton = (Button) findViewById(R.id.search_button);
        mResetButton = (Button) findViewById(R.id.reset_button);

        // display to text view
        final String[] restaurantNames = {"Culver's", "Chick-Fil-A", "Five Guys", "Wendy's",
                "McDonald's", "Burger King", "Smashburger", "Popeyes", "Canes", "KFC", "Subway",
                "Chipotle", "Domino's", "Papa John's", "Taco Bell", "Sonic", "In-N-Out",
                "Sbarro", "White Castle", "Jersey Mike's", "Jimmy John's"};

        for(String name : restaurantNames){
            mSearchResultsDisplay.append("\n\n"+ name);
        }

        final String defaultDisplayText = mSearchResultsDisplay.getText().toString();

        // respond to search button
        mSearchButton.setOnClickListener(
            new View.OnClickListener(){
                public void onClick(View v){
                    //get search string from user
                    String searchText = mSearchTermEditText.getText().toString();
                    // check if search string matches
                    for(String name : restaurantNames){
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
                //inner method def
                public void onClick(View v){
                    // reset the text
                    mSearchResultsDisplay.setText(defaultDisplayText);
                }
            }
        );

    }

}