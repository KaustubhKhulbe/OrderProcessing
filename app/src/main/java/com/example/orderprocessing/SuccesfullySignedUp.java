package com.example.orderprocessing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SuccesfullySignedUp extends AppCompatActivity {

    Button goToMenu;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succesfully_signed_up);
        setUpButton();

        //writeToDataBase();
    }

    /*public void writeToDataBase() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        writeNewUserToDataBase(mAuth.getUid(), mAuth.getCurrentUser().getEmail());
    }*/

    private void setUpButton() {
        goToMenu = (Button) findViewById(R.id.gotToMenu);
        goToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccesfullySignedUp.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /*private void writeNewUserToDataBase(String userId, String name) {
        User user = new User(name);

        mDatabase.child("users").child(userId).setValue(user);
    }*/
}
