package com.example.relichunter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {
    private TableLayout tableLayout;
    private TextView messageText, finalMessageText, titleText;
    private Button backToMenuButton;
    private ArrayList<String> items;
    private int relicsFound = 0;
    private int cursesFound = 0;
    private double score = 100.0;
    private int totalRelics = 7;
    private int totalCurses = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tableLayout = findViewById(R.id.tableLayout);
        messageText = findViewById(R.id.messageText);
        finalMessageText = findViewById(R.id.finalMessageText);
        titleText = findViewById(R.id.titleText);
        backToMenuButton = findViewById(R.id.backToMenuButton);

        backToMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        items = new ArrayList<>();
        items.add("STONE_AXE");
        items.add("COMPASS");
        items.add("ABACUS");
        items.add("OIL_LAMP");
        items.add("BONE_NEEDLE");
        items.add("PAPYRUS");
        items.add("PREHISTORIC_SANDAL");

        // Add "nothing" and "curse" to the grid
        for (int i = 0; i < 9; i++) {
            if (i % 2 == 0) {
                items.add("CURSE");
            } else {
                items.add("NOTHING");
            }
        }

        // Shuffle items so they appear randomly on the grid
        Collections.shuffle(items);

        // Create rows dynamically for the table
        for (int i = 0; i < 4; i++) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT
            ));

            // Add 4 buttons per row
            for (int j = 0; j < 4; j++) {
                int index = i * 4 + j;
                if (index >= items.size()) break;  // Prevent overflow

                Button button = new Button(this);
                button.setText("");
                button.setTag(items.get(index));

                // Make each button the same width
                TableRow.LayoutParams params = new TableRow.LayoutParams(0,
                        TableRow.LayoutParams.WRAP_CONTENT, 1);
                button.setLayoutParams(params);

                button.setOnClickListener(v -> handleItemClick((Button) v));

                row.addView(button);
            }
            tableLayout.addView(row);
        }
    }

    private void handleItemClick(Button button) {
        String itemType = (String) button.getTag();
        button.setEnabled(false);

        String message = "";
        int imageResource = 0;

        switch (itemType) {
            case "STONE_AXE":
                message = "STONE AXE: Found by Zealsprince in Jokler’s garden. He doesn’t know what " +
                        "to think about it...";
                imageResource = R.drawable.axe;
                relicsFound++;
                score += 10;
                break;
            case "COMPASS":
                message = "COMPASS: Used by Tmtu in his last hike when he forgot his GPS at home.";
                imageResource = R.drawable.compass;
                relicsFound++;
                score += 10;
                break;
            case "ABACUS":
                message = "ABACUS: Used by Chanty to count the days to start the next Advent’s calendar.";
                imageResource = R.drawable.abacus;
                relicsFound++;
                score += 10;
                break;
            case "OIL_LAMP":
                message = "OIL LAMP: Used by Kilmanio to go to the gym at night after he forgot his " +
                        "flashlight in a Hardangervidda cabin.";
                imageResource = R.drawable.oil_lamp;
                relicsFound++;
                score += 10;
                break;
            case "BONE_NEEDLE":
                message = "BONE NEEDLE: Used by APH to knit a new Godot hat and a sweater for Amon.";
                imageResource = R.drawable.needle;
                relicsFound++;
                score += 10;
                break;
            case "PAPYRUS":
                message = "PAPYRUS: Used by Rapid to take notes of new Spanish words because he couldn’t find his notebook.";
                imageResource = R.drawable.papyrus;
                relicsFound++;
                score += 10;
                break;
            case "PREHISTORIC_SANDAL":
                message = "PREHISTORIC SANDAL: Found by Amon behind the sofa while Dolan was playing Lethal Company.";
                imageResource = R.drawable.sandal;
                relicsFound++;
                score += 10;
                break;
            case "CURSE":
                message = "CURSE: Oops! Something bad happened!";
                imageResource = R.drawable.hand;
                cursesFound++;
                score -= 5;
                break;
            case "NOTHING":
                message = "NOTHING: You found nothing... Try again!";
                imageResource = R.drawable.nothing;
                break;
        }
        button.setBackgroundResource(imageResource);

        messageText.setText(message);
        messageText.setVisibility(View.VISIBLE);

        if (relicsFound == totalRelics) {
            finalMessageText.setText("Congratulations! You've found all relics! Your score: " + calculateScore() + "%");
            finalMessageText.setVisibility(View.VISIBLE);
        }
    }

    private double calculateScore() {
        double totalItems = relicsFound + cursesFound;
        double scorePercentage = (relicsFound * 10) - (cursesFound * 5);
        return Math.max(scorePercentage, 0);
    }
}
