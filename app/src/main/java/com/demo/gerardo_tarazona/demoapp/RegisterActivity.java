package com.demo.gerardo_tarazona.demoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextAge;
    private EditText editTextEmail;
    private EditText editTextPass;
    private Button buttonRegister;
    private TextView textViewLogin;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;

    private UserObject user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        if(firebaseAuth.getCurrentUser() !=null){
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }

        editTextName = (EditText) findViewById(R.id.name);
        editTextAge = (EditText) findViewById(R.id.age);
        editTextEmail = (EditText) findViewById(R.id.email_register);
        editTextPass = (EditText) findViewById(R.id.password_register);
        buttonRegister = (Button) findViewById(R.id.register);
        textViewLogin = (TextView)findViewById(R.id.login_already_account);

        buttonRegister.setOnClickListener(this);
        textViewLogin.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                    UserRegister();
                break;
            case R.id.login_already_account:
                    finish();
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                break;

        }
    }

    private void UserRegister() {
        String name = editTextName.getText().toString().trim();
        String age = editTextAge.getText().toString();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPass.getText().toString().trim();




        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, R.string.insert_email, Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, getString(R.string.insert_pass), Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, getString(R.string.insert_name), Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(age)){
            Toast.makeText(this, getString(R.string.insert_age), Toast.LENGTH_SHORT).show();
        }
        if(!(TextUtils.isEmpty(email)&&TextUtils.isEmpty(password)&&TextUtils.isEmpty(name)&&TextUtils.isEmpty(age))) {


            String id = databaseReference.push().getKey();
            user = new UserObject(age,email,name);

            databaseReference.child(id).setValue(user);

            progressDialog.setMessage(getString(R.string.register_process));
            progressDialog.show();
            progressDialog.setCancelable(false);

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        finish();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        Toast.makeText(getBaseContext(), getString(R.string.registered_successfully), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getBaseContext(), getString(R.string.could_not_register), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }




}
