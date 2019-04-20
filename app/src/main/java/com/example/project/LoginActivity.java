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
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button Login,Reg;
    EditText Email,Password;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog=new ProgressDialog(this);
        Login=findViewById(R.id.btnLogin);
        Reg=findViewById(R.id.btnLinkToRegisterScreen);
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
        Login.setOnClickListener(this);
        Reg.setOnClickListener(this);
        firebaseAuth=FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()!=null){

        }

    }

    private void userLogin() {
        String pass=Password.getText().toString().trim();
        String email=Email.getText().toString().trim();
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please enter Password",Toast.LENGTH_SHORT).show();

        }
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter Email",Toast.LENGTH_SHORT).show();
            return;

        }

        progressDialog.setMessage("Successfully Login :)");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            //Hello
                            finish();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                    }
                });

    }


    @Override
    public void onClick(View v) {
        if (v==Login){
            userLogin();

        }

        if (v==Reg){

            finish();
            Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
            startActivity(i);
        }

    }

}
