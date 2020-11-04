package edu.ucu.cite.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class user extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        TextView username=(TextView) findViewById(R.id.username);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String user=bundle.get("User").toString();
        username.setText(user);


    }
}