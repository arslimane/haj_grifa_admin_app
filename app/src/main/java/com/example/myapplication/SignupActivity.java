package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    public EditText mail;
    public EditText pass, repeatPass, fullname;
    public FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        changeStatusBarColor();
        fullname=(EditText) findViewById(R.id.editTextName);
        pass=(EditText)findViewById(R.id.editTextPassword);
        repeatPass = (EditText)findViewById(R.id.editTextPassword2);
        mail = (EditText)findViewById(R.id.editTextEmail);
        firebaseAuth=FirebaseAuth.getInstance();
    }


    public void login(View view) {

        final String email =this.mail.getText().toString().trim();
        final String password =this.pass.getText().toString();
        final String name =this.fullname.getText().toString();
        final String passRepeat = this.repeatPass.getText().toString();


        if (TextUtils.isEmpty(email)|| TextUtils.isEmpty(password) || TextUtils.isEmpty(passRepeat) || TextUtils.isEmpty(name)){
            Toast.makeText(this,"يجب ملئ كل الخانات",Toast.LENGTH_LONG).show();
        }else if (! password.equals(passRepeat)){
            Toast.makeText(this,"يرجى التاكد من كلمة السر",Toast.LENGTH_LONG).show();
        } else{

            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("انشاء حساب ...");
            progressDialog.setMessage("من فضلك انتظر قليلا");
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar);
            progressDialog.setIndeterminate(true);
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"تم التسجيل بنجاح",Toast.LENGTH_LONG).show();
                        FirebaseUser user1=firebaseAuth.getCurrentUser();
                        user1.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>(){
                            public void onComplete( Task<Void> task){
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"يرجى التاكد من البريد الالكتروني",Toast.LENGTH_LONG).show();

                                }else{
                                    String error=task.getException().getMessage();
                                    Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                    }else{
                        String error=task.getException().getMessage();
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
                    }

                }
            });
        }

    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,Login.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }
}