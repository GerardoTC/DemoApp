package com.demo.gerardo_tarazona.demoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextEmail;
    EditText editTextPassword;
    TextView textViewRegister;
    Button buttonSignIn;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }

        editTextEmail = (EditText) findViewById(R.id.email_login);
        editTextPassword = (EditText) findViewById(R.id.password);
        textViewRegister = (TextView) findViewById(R.id.text_register);
        buttonSignIn = (Button) findViewById(R.id.signIn);

        buttonSignIn.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signIn:
                UserLogin();
                break;
            case R.id.text_register:
                finish();
                startActivity(new Intent(this,RegisterActivity.class));
                break;
        }
    }

    private void UserLogin() {
        String emailString = editTextEmail.getText().toString().trim();
        String passwordString = editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(emailString)){
            Toast.makeText(this, R.string.insert_email, Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(passwordString)){
            Toast.makeText(this, getString(R.string.insert_pass), Toast.LENGTH_SHORT).show();
        }
        if(!(TextUtils.isEmpty(emailString)&&TextUtils.isEmpty(passwordString))) {
            String login_process = getString(R.string.login_process);
            progressDialog.setMessage(login_process);
            progressDialog.show();
            progressDialog.setCancelable(false);


            firebaseAuth.signInWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(
                    this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isComplete()) {
                                finish();
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            }
                        }
                    }
            );
        }
    }
}
