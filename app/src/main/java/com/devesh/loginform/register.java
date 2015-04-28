package com.devesh.loginform;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by LENOVO on 4/28/2015.
 */
public class register extends Activity{
    EditText firstname,lastname,username,email,password;
    Button RegisterBtn;

    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        // get Instance  of Database Adapter
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        // Get Refferences of Views
        firstname=(EditText)findViewById(R.id.first_name);
        lastname=(EditText)findViewById(R.id.last_name);
        email=(EditText)findViewById(R.id.E_Mail);
        username=(EditText)findViewById(R.id.User_Name);
        password=(EditText)findViewById(R.id.Pass_Word);

        RegisterBtn=(Button)findViewById(R.id.register_button);
        RegisterBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                String fname = firstname.getText().toString();
                String lname = lastname.getText().toString();
                String uname = username.getText().toString();
                String pword = password.getText().toString();
                String mail = email.getText().toString();

                // check if any of the fields are vaccant
                if (fname.equals("") || pword.equals("") || lname.equals("")||uname.equals("")|| mail.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                }
                // check if both password matches
                else {
                    // Save the Data in Database
                    loginDataBaseAdapter.insertEntry(fname,lname,uname,mail,pword);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
}
