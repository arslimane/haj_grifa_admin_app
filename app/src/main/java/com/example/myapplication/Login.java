package com.example.myapplication;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;

import android.widget.EditText;
import android.widget.ProgressBar;

import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;





public class Login extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private ProgressBar loadingProgressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        usernameEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        loadingProgressBar = findViewById(R.id.loadingbar);
        firebaseAuth=FirebaseAuth.getInstance();

    }

    public void signin(View view) {
        String user=usernameEditText.getText().toString().trim();
        String pass=passwordEditText.getText().toString();
        if (TextUtils.isEmpty(user)|| TextUtils.isEmpty(pass)){
            Toast.makeText(this,"خطأ",Toast.LENGTH_LONG).show();
        }else{
            loadingProgressBar.setVisibility(View.VISIBLE);
            firebaseAuth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        //saveUserInfo(getBaseContext(), FirebaseAuth.getInstance().getCurrentUser() != null);
                        loadingProgressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"تم تسجيل الدخول بنجاح",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), product_list.class));
                    }else{
                        loadingProgressBar.setVisibility(View.GONE);
                        String error=task.getException().getMessage();
                        Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }

    public void signUp(View view) {
        Intent intent=new Intent(this, SignupActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }


    public void passwordOublie(View view) {
        final String user=usernameEditText.getText().toString().trim();
        if (TextUtils.isEmpty(user)){
            Toast.makeText(this,"يرجى ادخال اسم المستخدم",Toast.LENGTH_LONG).show();
        }else {
            AlertDialog message = new AlertDialog.Builder(this)
                    .setTitle("تغيير كلمة السر")
                    .setMessage("هل تريد فعلا تغيير كلمة السر")
                    .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            loadingProgressBar.setVisibility(View.VISIBLE);

                            firebaseAuth.sendPasswordResetEmail(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(getBaseContext(),"راجع بريدك الالكتروني",Toast.LENGTH_LONG).show();
                                        loadingProgressBar.setVisibility(View.GONE);

                                    }else {
                                        Toast.makeText(getBaseContext(),"خطا في اعادة تعيين كلمة السر",Toast.LENGTH_LONG).show();
                                        loadingProgressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                        }
                    })
                    .setNegativeButton("لا", null)
                    .setIcon(R.drawable.sign_echec)
                    .show();


        }
    }

}