package com.example.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    Button Register,Member;
    EditText Name,Email,Phone,Password;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressDialog=new ProgressDialog(this);
        Register =(Button) findViewById(R.id.btnRegister);
        Member =(Button) findViewById(R.id.btnLinkToLoginScreen);

        Name=findViewById(R.id.name);
        Email=findViewById(R.id.email);
        Phone=findViewById(R.id.phone);
        Password=findViewById(R.id.password);
        Register.setOnClickListener(this);
        Member.setOnClickListener(this);

        firebaseAuth=FirebaseAuth.getInstance();

    }

    private void registeruser(){
        String name=Name.getText().toString().trim();

        String phone=Phone.getText().toString().trim();
        String pass=Password.getText().toString().trim();
        String email=Email.getText().toString().trim();

        if (TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please enter FullName",Toast.LENGTH_SHORT).show();
            return;

        }

        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Please enter Phone",Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please enter Password",Toast.LENGTH_SHORT).show();

        }
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter Email",Toast.LENGTH_SHORT).show();
            return;

        }

        progressDialog.setMessage("Registering the user...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"Registered Succesfully",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                        else {

                            Toast.makeText(RegisterActivity.this,"Could not Register...",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                    }
                });



}

    @Override
    public void onClick(View v) {
        if (v==Register){
            registeruser();
        }
        if (v==Member){
            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(i);
        }

    }


}
