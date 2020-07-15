package com.example.cropmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Intent intent=new Intent(MainActivity.this,Login.class);
        startActivity(intent);


         */
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser!=null)
        {

            startActivity(new Intent(MainActivity.this,HomePage.class));
            finish();
        }
        else {
            startActivity(new Intent(MainActivity.this, Login.class));
        }
    }

}
