package xyz.mermela.gucluapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;


public class RegisterActivity extends AppCompatActivity {
    private EditText register_mail,register_password;
    private Button register_button;
    private TextView already_have_an_account;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        register_mail = findViewById(R.id.register_mail);
        register_password = findViewById(R.id.register_password);
        register_button = findViewById(R.id.register_button);
        already_have_an_account = findViewById(R.id.already_have_an_account);

        already_have_an_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        String mail = register_mail.getText().toString();
        String password = register_password.getText().toString();


        mAuth.createUserWithEmailAndPassword(mail,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Hesabınız Oluşturuldu.",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                        intent.putExtra("mail",mail);
                        intent.putExtra("password",password);
                        startActivity(intent);
                        finish();

                        } else {
                            Toast.makeText(getApplicationContext(),"Hatalı giriş",Toast.LENGTH_LONG).show();

                        }


                    }
                });
    }
}