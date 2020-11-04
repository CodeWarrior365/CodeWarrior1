package edu.ucu.cite.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editusername,editpassword;
    Button  buttonlogin ,buttonregister;
    Databasehalper databasehalper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databasehalper= new Databasehalper(this);

        editusername = (EditText) findViewById(R.id.username1);
        editpassword = (EditText) findViewById(R.id.pass1);
        buttonlogin = (Button) findViewById(R.id.btnlogin);
        buttonregister = (Button) findViewById(R.id.btnregister);



        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user =editusername.getText().toString();
                String pwd =editpassword.getText().toString();
                Boolean res = databasehalper.User(user,pwd);

                if(user.equals("")||pwd.equals("")){
                    Toast.makeText(getApplicationContext(),"Field are Empty",Toast.LENGTH_SHORT).show();
                }

                else if(res == true){
                    Intent intent=new Intent (MainActivity.this,user.class);
                    intent.putExtra("User",user.toString());
                    startActivity(intent);
                    Toast.makeText(MainActivity.this,"Login Successfull",Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(MainActivity.this,"Invalid Password or Username",Toast.LENGTH_LONG).show();
                }

            }
        });

        buttonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (MainActivity.this,register.class);
                startActivity(intent);
            }
        });

    }







}