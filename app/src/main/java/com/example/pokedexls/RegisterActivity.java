package com.example.pokedexls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private Button signup_button;

    private EditText editTextEmail;
    private EditText editTextPassword;

    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent register_intent = getIntent();

        signup_button = (Button) findViewById(R.id.signup_button);
        editTextEmail = (EditText) findViewById(R.id.EditEmail);
        editTextPassword = (EditText) findViewById(R.id.EditPassword);

        mAuth = FirebaseAuth.getInstance();




        signup_button.setOnClickListener(v -> {

            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Intent main_intent = new Intent(RegisterActivity.this, MainScreen.class);
                            RegisterActivity.this.startActivity(main_intent);
                        } else {
                            // error
                        }
                    });

        });


    }
}