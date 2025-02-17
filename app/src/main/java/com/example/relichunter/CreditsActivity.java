package com.example.relichunter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CreditsActivity extends AppCompatActivity {

    private TextView creditsTitle, thanksAPH, thanksTeam;
    private Button backToMenuButton;
    private ImageView gameJamImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        creditsTitle = findViewById(R.id.creditsTitle);
        thanksAPH = findViewById(R.id.thanksAPH);
        thanksTeam = findViewById(R.id.thanksTeam);
        backToMenuButton = findViewById(R.id.backToMenuButton);
        gameJamImage = findViewById(R.id.gameJamImage);

        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainMenuIntent = new Intent(CreditsActivity.this, MainActivity.class);
                startActivity(mainMenuIntent);
                finish();
            }
        });
    }
}
