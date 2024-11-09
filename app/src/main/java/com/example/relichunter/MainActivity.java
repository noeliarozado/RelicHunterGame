package com.example.relichunter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button startGameButton;
    private Button creditsButton;
    private Button instructionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGameButton = findViewById(R.id.startGameButton);
        creditsButton = findViewById(R.id.creditsButton);
        instructionsButton = findViewById(R.id.instructionsButton);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gameIntent);
            }
        });

        creditsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent creditsIntent = new Intent(MainActivity.this, CreditsActivity.class);
                startActivity(creditsIntent);
            }
        });

        instructionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent instructionsIntent = new Intent(MainActivity.this, InstructionsActivity.class);
                startActivity(instructionsIntent);
            }
        });
    }
}
