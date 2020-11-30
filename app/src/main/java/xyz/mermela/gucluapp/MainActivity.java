package xyz.mermela.gucluapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    public EditText login_mail,login_password;
    private Button login_button;
    private TextView register_now;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        login_mail = findViewById(R.id.login_mail);
        login_password = findViewById(R.id.login_password);
        login_button = findViewById(R.id.login_button);
        register_now = findViewById(R.id.register_now);

        Intent intent = getIntent();
        String mail = intent.getStringExtra("mail");
        String password =intent.getStringExtra("password");

        login_mail.setText(mail);
        login_password.setText(password);

        mAuth = FirebaseAuth.getInstance();

        register_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {

         String mail = login_mail.getText().toString();
         String password = login_password.getText().toString();

        mAuth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Succesfully login",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this,DashboardActivity.class);
                            intent.putExtra("username",mail);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getApplicationContext(),"Hatalı giriş",Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
}