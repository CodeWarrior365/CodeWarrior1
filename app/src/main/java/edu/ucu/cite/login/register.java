package edu.ucu.cite.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class register extends AppCompatActivity {
    Databasehalper db;
    EditText eusername,epassword,econpassword,ename,eaddress,estatus;
    RadioGroup egender;
    Button bregister ,blogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db= new Databasehalper(this);
        eusername=findViewById(R.id.regusername);
        epassword=findViewById(R.id.regpass);
        econpassword=findViewById(R.id.regconpass);
        ename=findViewById(R.id.regname);
        eaddress=findViewById(R.id.regaddress);
        estatus=findViewById(R.id.regstatus);
        egender=findViewById(R.id.radiogroup1);

        bregister=findViewById(R.id.btnregister1);
        blogin=findViewById(R.id.btnlogin1);

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this,MainActivity.class);
                startActivity(intent);
            }
        });

        bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username= eusername.getText().toString();
                String password= epassword.getText().toString();
                String conpassword = econpassword.getText().toString();
                String name= ename.getText().toString();
                RadioButton checkedBtn = findViewById(egender.getCheckedRadioButtonId());
                String gender =checkedBtn.getText().toString();
                String address= eaddress.getText().toString();
                String status = estatus.getText().toString();


                if (username.equals("")||password.equals("")||conpassword.equals("")||name.equals("")||gender.equals("")||address.equals("")||status.equals("")){
                    Toast.makeText(getApplicationContext(),"Field are Empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    //validate the password
                    if (password.equals(conpassword)){
                        //validate the username
                        Boolean checkUser =db.checkUser(username);
                        if (checkUser==true){
                            //fill up all fields
                            Boolean val =db.addUser(username,password,name,address,gender,status);
                            if(val==true){
                                Toast.makeText(getApplicationContext(),"Your Registered",Toast.LENGTH_SHORT).show();
                                //make the fields empty
                                eusername.setText("");
                                epassword.setText("");
                                econpassword.setText("");
                                ename.setText("");
                                eaddress.setText("");
                                estatus.setText("");
                            }

                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Username Already Exist",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Password does'nt match",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });





    }


}