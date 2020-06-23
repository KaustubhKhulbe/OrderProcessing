package com.example.orderprocessing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SuccessfulyLogedIn extends AppCompatActivity {

    private static final String TAG = "TAG";
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    private ListView mListView;

    private TextView email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successfuly_loged_in);


        email = (TextView) findViewById(R.id.nameFromDatabase);
        password = (TextView) findViewById(R.id.statusFromDatabase);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null){
                    Log.d(TAG, "onAuthStateListener: Signed in" + user.getUid());
                    toastMessage("Succesfully signed in with: " + user.getEmail());
                }
                else{
                    Log.d(TAG, "onAuthStateListerer: Signed out");
                    toastMessage("Successfuly signed out");
                }
            }
        };

    myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            showData(dataSnapshot);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });


    }

    private void showData(DataSnapshot dataSnapshot){
        for(DataSnapshot ds: dataSnapshot.getChildren()){
            User user = new User();

            user.setEmail(ds.child(userID).getValue(User.class).getEmail());
            user.setIsCustomer(ds.child(userID).getValue(User.class).getIsCustomer());

            Log.d(TAG, "showData: name " + user.getEmail());
            System.out.println("showData: name " + user.getEmail());

            Log.d(TAG, "showData: status " + user.getEmail());
            System.out.println("showData: status " + user.getIsCustomer());

            email.setText(user.getEmail());
            password.setText(Boolean.toString(user.getIsCustomer()));
        }
    }



    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }

    @Override
    public void onStop(){
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
