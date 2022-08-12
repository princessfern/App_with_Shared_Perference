package com.example.app_with_shared_perference;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.security.auth.login.LoginException;

public class LoginActivity extends AppCompatActivity {
    Button logInBtn, newUserBtn;
    EditText usernameValue, passwordValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);

        logInBtn = findViewById(R.id.buttonLoginActivity);
        newUserBtn = findViewById(R.id.signUpBtn);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Verification code

                usernameValue = findViewById(R.id.usernameInput);
                passwordValue = findViewById(R.id.passwordInput);
                String username = usernameValue.getText().toString();
                String password = passwordValue.getText().toString();

                boolean valid_name = false;

                if(username.matches("\\d.*") || username.length()>20){
                    Toast.makeText(LoginActivity.this, "Username must not start with a number and should be less than 20 characters", Toast.LENGTH_LONG).show();
                }else{
                    valid_name=true;
                }

                String searchPrefs = pref.getString(username, "default password");

                if (pref.contains(username) && searchPrefs.equals(password) && valid_name){
                    /*
                     * We can only insert shared preference only though the editor.*/
                    SharedPreferences.Editor editor = pref.edit();

                    //Set the flag value to be true and log the user in
                    editor.putBoolean("flag",true);
                    editor.apply();
                    Intent iHome = new Intent(LoginActivity.this, HomeActivity.class);
                    Toast.makeText(LoginActivity.this,"User logged in! ", Toast.LENGTH_SHORT).show();
                    startActivity(iHome);

                }
                else{
                    Toast.makeText(LoginActivity.this,
                            "Invalid login credential: No such user", Toast.LENGTH_SHORT).show();
                }
            }
        });

        newUserBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CommitPrefEdits")
            @Override
            public void onClick(View view) {

                usernameValue = findViewById(R.id.usernameInput);
                passwordValue = findViewById(R.id.passwordInput);
                String username = usernameValue.getText().toString();
                String password = passwordValue.getText().toString();

                boolean valid_name = false;
                if(username.matches("\\d.*") || username.length()>20){
                    Toast.makeText(LoginActivity.this, "Username must not start with a number and should be less than 20 characters", Toast.LENGTH_LONG).show();
                }else{
                    valid_name=true;
                }

                if (!pref.contains(username) && valid_name){
                    SharedPreferences.Editor editor;
                    editor = pref.edit();
                    editor.putString(username, password);
                    editor.apply();
                    Toast.makeText(LoginActivity.this,"User Created! Go to Login!", Toast.LENGTH_SHORT).show();
                }
                else if (pref.contains(username)){
                    Toast.makeText(LoginActivity.this, "Invalid Signup: User already exists.", Toast.LENGTH_LONG).show();
                }
                else if (!valid_name){
                    Toast.makeText(LoginActivity.this, "Invalid username: starts with a number/too many characters", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}