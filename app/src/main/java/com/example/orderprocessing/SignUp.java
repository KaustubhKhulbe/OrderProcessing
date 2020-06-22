package com.example.orderprocessing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    // Input fields
    private static TextView name, password;
    private static Button signUp;
    private static CheckBox isCustomer;

    //Database fields
    private FirebaseAuth mAuth;

    //Other fields
    private static final String TAG = "TAG";
    private DatabaseReference mDatabase;
    private static boolean checkBoxType = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initializeInputFields();
       // initalizeFirebaseFields();
    }

    //private void initalizeFirebaseFields() {
       // mAuth = FirebaseAuth.getInstance();
    //}

    private void initializeInputFields() {
        name = (TextView)findViewById(R.id.nameNew);
        password = (TextView)findViewById(R.id.passwordNew);
        signUp = (Button)findViewById(R.id.signUpNew);
        isCustomer = (CheckBox)findViewById(R.id.checkIfCustomer);

        setOnClickListeners();

    }

    private void setOnClickListeners() {
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createUser(name.getText().toString(), password.getText().toString());
                writeToDataBase();


            }
        });

        isCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxType = true;
            }
        });
    }
    public void writeToDataBase() {
        //TODO need to make it so that it checks
        // if checkmark is ticked at sign up time, not at
        // anytime or this will enable it as true if user
        // accidentally clicks it


        //TODO database updating previously signed in user not current user
        mDatabase = FirebaseDatabase.getInstance().getReference();
        if(checkBoxType) writeNewUserToDataBase(mAuth.getUid(), mAuth.getCurrentUser().getEmail(), true);
        else writeNewUserToDataBase(mAuth.getUid(), mAuth.getCurrentUser().getEmail(), false);

    }

    private void writeNewUserToDataBase(String userId, String name, boolean status) {
        User user = new User(name, status);
        mDatabase.child("users").child(userId).setValue(user);
    }

    private void createUser(String email, String password) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            //FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(SignUp.this, SuccesfullySignedUp.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        signUserIn(email, password);
    }

    private void signUserIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



}
