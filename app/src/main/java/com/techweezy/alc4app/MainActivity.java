package com.techweezy.alc4app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    Button about, profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.home_activity);
        setContentView(R.layout.activity_main);
        about=(Button)findViewById(R.id.aboutlink);
        profile=(Button)findViewById(R.id.profileLink);
    }
    public void buttonClickEvent(View view){
        switch (view.getId()){
            case R.id.aboutlink:
                Intent aboutIntent=new
                        Intent(MainActivity.this,AboutAlc.class);
                startActivity(aboutIntent);
                finish();
                break;
            case R.id.profileLink:
                Intent profileIntent=new
                        Intent(MainActivity.this,ProfileWindow.class);
                startActivity(profileIntent);
                finish();
                break;
        }
    }
}