package com.bm.balanceme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    EditText signupName, signupEmail, signupPassword, signupAge, signupHeight, signupWeight;
    Button registerBtn;
    TextView loginBtn;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signupName = findViewById(R.id.name);
        signupEmail = findViewById(R.id.email);
        signupPassword = findViewById(R.id.password);
        signupAge = findViewById(R.id.age);
        signupHeight = findViewById(R.id.height);
        signupWeight = findViewById(R.id.weight);
        registerBtn = findViewById(R.id.registerBtn);
        loginBtn = findViewById(R.id.loginTextBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString().replace(".", "_");
                String password = signupPassword.getText().toString();
                String age = signupAge.getText().toString();
                String height = signupHeight.getText().toString();
                String weight = signupWeight.getText().toString();

                UserHelperClass userHelperClass = new UserHelperClass(name, email, password, age, height, weight);
                reference.child(email).setValue(userHelperClass);

                Toast.makeText(Register.this, "You have registered an account successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Register.this, MainActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("age", age);
                intent.putExtra("height", height + " cm");
                intent.putExtra("weight", weight + " kg");
                startActivity(intent);
            }
        });
        
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }
}