package com.example.relichunter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class InstructionsActivity extends AppCompatActivity {

    private TextView titleText, instructionsText;
    private Button backToMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        titleText = findViewById(R.id.titleText);
        instructionsText = findViewById(R.id.instructionsText);
        backToMenuButton = findViewById(R.id.backToMenuButton);

        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainMenuIntent = new Intent(InstructionsActivity.this, MainActivity.class);
                startActivity(mainMenuIntent);
                finish();
            }
        });
    }
}
