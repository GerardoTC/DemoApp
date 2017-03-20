package com.demo.gerardo_tarazona.demoapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class UserRecovery extends Activity {
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_recovery);
         firebaseAuth = FirebaseAuth.getInstance();

        Button recovery_pass = (Button) findViewById(R.id.recovery_password);

        recovery_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText email = (EditText) findViewById(R.id.email_recovery);
                firebaseAuth.sendPasswordResetEmail(email.getText().toString().trim());
                Toast.makeText(UserRecovery.this, R.string.email_recovery_pass_sent, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
