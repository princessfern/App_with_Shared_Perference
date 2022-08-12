package com.example.app_with_shared_perference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add handler to the splash screen.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*
                *  This code will run after the specified delay,
                *  which is, in this case 3000 mSec.
                * After, this delay, app wil decide to go to the login activity or
                * home activity, which will be decided by shared preference.
                * Initially the shared preference value will be false.
                * */
                //when this SF will be called first time, it will create a variable with name
                //login, preference will be created and then it will fetch it.
                //MODE_PRIVATE: No Other app can  get this preference,
            SharedPreferences pref = getSharedPreferences("login",MODE_PRIVATE);
            // By default the return type is false.
            Boolean check = pref.getBoolean("flag",false);
            Intent iNext;

            if(check){ // True if the user has already logged in.
                //If user already logged in, go to Home screen / activity.
                iNext =new Intent(MainActivity.this,HomeActivity.class);

            }else{
                //This code will be executed if for the first time app usage or
                // used is logged out.
                iNext = new Intent(MainActivity.this,LoginActivity.class);

            }
            startActivity(iNext);

            }
        },3000);

    }
}