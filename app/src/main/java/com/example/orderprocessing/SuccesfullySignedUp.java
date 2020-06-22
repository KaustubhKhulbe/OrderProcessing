package com.example.orderprocessing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccesfullySignedUp extends AppCompatActivity {

    Button goToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succesfully_signed_up);
        setUpButton();
    }

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
}
