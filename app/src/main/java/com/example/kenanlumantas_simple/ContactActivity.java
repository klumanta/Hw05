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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Connect UI
        mDisplayAboutTextView = (TextView) findViewById(R.id.tv_about_text);
        mOpenWebpageButton = (Button) findViewById(R.id.button_open_webpage);

        // Grab data
        Intent intentThatStartedThisActivity = getIntent();
        String message = "news";
        if(intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){
            message = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            mDisplayAboutTextView.append("\n\n\n" + message);
        }

        final String urlString = "https://www.linkedin.com/in/kenan-lumantas-0665b8193?trk=people-guest_people_search-card";

        // Web page button
        mOpenWebpageButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        openWebPage(urlString);
                    }
                }
        );

    }

    public void openWebPage(String urlString){
        Uri webpage = Uri.parse(urlString);
        Intent openWebPageIntent = new Intent(Intent.ACTION_VIEW, webpage);

        if(openWebPageIntent.resolveActivity(getPackageManager()) != null){
            startActivity(openWebPageIntent);
        }
    }

}