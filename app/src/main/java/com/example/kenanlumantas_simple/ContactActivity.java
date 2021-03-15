package com.example.kenanlumantas_simple;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    private TextView mDisplayAboutTextView;
    private Button mOpenWebpageButton;
    private Button mOpenMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Connect UI
        mDisplayAboutTextView = (TextView) findViewById(R.id.tv_about_text);
        mOpenWebpageButton = (Button) findViewById(R.id.button_open_webpage);
        mOpenMapButton = (Button) findViewById(R.id.button_open_map);

        // Grab data
        Intent intentThatStartedThisActivity = getIntent();
        String message = "news";
        if(intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
            message = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            mDisplayAboutTextView.append("\n\n\n" + message);
        }

        final String urlString = "https://cse.nd.edu/";

        // Web page button
        mOpenWebpageButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        openWebPage(urlString);
                    }
                }
        );

        // Map button
        mOpenMapButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        openMap();
                    }
                }
        );
    }

    public void openMap(){
        String addressString = "University of Notre Dame, IN";
        Uri addressUri = Uri.parse("geo:0,0").buildUpon().appendQueryParameter("q", addressString).build();
        Intent openMapIntent = new Intent(Intent.ACTION_VIEW);
        openMapIntent.setData(addressUri);

        if(openMapIntent.resolveActivity(getPackageManager()) != null){
            startActivity(openMapIntent);
        }
    }

    public void openWebPage(String urlString){
        Uri webpage = Uri.parse(urlString);
        Intent openWebPageIntent = new Intent(Intent.ACTION_VIEW, webpage);

        if(openWebPageIntent.resolveActivity(getPackageManager()) != null){
            startActivity(openWebPageIntent);
        }
    }

}