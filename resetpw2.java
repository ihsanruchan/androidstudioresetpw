package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class resetpw2 extends AppCompatActivity {
    private EditText editTextmail;
    private Button home, resetpw;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpw2);

        editTextmail = (EditText) findViewById(R.id.resetpwmail);
        resetpw = (Button) findViewById(R.id.resetpwreset);

        mAuth = FirebaseAuth.getInstance();

        resetpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpassword();
            }
        });
    }

    private void resetpassword() {
        String mail = editTextmail.getText().toString().trim();
        if (mail.isEmpty()) {
            editTextmail.setError("Lütfen e-posta adresinizi girin.");
            editTextmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            editTextmail.setError("Lütfen geçerli bir e-posta adresi girin.");
            editTextmail.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(resetpw2.this, "Şifrenizi sıfırlamak için e-postanızı kontrol ediniz",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(resetpw2.this,"Bir şeyler ters gitti. Lütfen tekrar deneyin",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}