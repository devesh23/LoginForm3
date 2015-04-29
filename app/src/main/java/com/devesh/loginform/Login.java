package com.devesh.loginform;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends Activity
{
    Button Login,Register;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create a instance of SQLite Database
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        // Get The Refference Of Buttons
        Login=(Button)findViewById(R.id.login);
        Register=(Button)findViewById(R.id.register);

        // Set OnClick Listener on SignUp button
        Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  abd Start The Activity
                Intent intentregister = new Intent(getApplicationContext(), register.class);

                startActivity(intentregister);

            }
        });

    // Methos to handleClick Event of Sign In Button
        // get the Refferences of views

        final  EditText email=(EditText)findViewById(R.id.email);
        final  EditText password=(EditText)findViewById(R.id.pword);

        Button login=(Button)findViewById(R.id.login);

        // Set On ClickListener
        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // get The User name and Password
                String mail=email.getText().toString();
                String p_word=password.getText().toString();

                // fetch the Password form database for respective user name
                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(mail);

                // check if the Stored password matches with  Password entered by user
                if(p_word.equals(storedPassword))
                {
                    Toast.makeText(Login.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(Login.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }

}
