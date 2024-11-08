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
    private double score = 100.0; // Start score at 100%
    private int totalRelics = 7; // Number of relics to be found
    private int totalCurses = 3; // Number of curses in the game

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Initialize views
        tableLayout = findViewById(R.id.tableLayout);
        messageText = findViewById(R.id.messageText);
        finalMessageText = findViewById(R.id.finalMessageText);
        titleText = findViewById(R.id.titleText);
        backToMenuButton = findViewById(R.id.backToMenuButton);

        // Set up the back to menu button click listener
        backToMenuButton.setOnClickListener(v -> {
            // Intent to go back to MainActivity (or whatever your main menu activity is)
            Intent intent = new Intent(GameActivity.this, MainActivity.class);
            startActivity(intent);
            finish();  // Close this activity
        });

        // Initialize the list of items (relics, curses, nothing)
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
        for (int i = 0; i < 4; i++) {  // We will have 4 rows
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
                button.setText("");  // Don't show text, only images
                button.setTag(items.get(index));  // Assign item type to button tag



                // Make each button the same width
                TableRow.LayoutParams params = new TableRow.LayoutParams(0,
                        TableRow.LayoutParams.WRAP_CONTENT, 1);  // Set the weight to 1 to make all buttons equal width
                button.setLayoutParams(params);






                // Set up click listener for each button
                button.setOnClickListener(v -> handleItemClick((Button) v));

                row.addView(button);
            }

            // Add the row to the TableLayout
            tableLayout.addView(row);
        }
    }

    private void handleItemClick(Button button) {
        String itemType = (String) button.getTag();  // Get the item type (relic, curse, nothing)
        button.setEnabled(false); // Disable button after it's clicked

        String message = "";
        int imageResource = 0;  // Default to 0 (no image)

        // Display different messages and set images depending on item type
        switch (itemType) {
            case "STONE_AXE":
                message = "STONE AXE: Found by Zealsprince in Jokler’s garden. He doesn’t know what to think about it...";
                imageResource = R.drawable.axe;
                relicsFound++;
                score += 10;  // Increase score for relics
                break;
            case "COMPASS":
                message = "COMPASS: Used by tmtu during his last hike.";
                imageResource = R.drawable.compass;
                relicsFound++;
                score += 10;
                break;
            case "ABACUS":
                message = "ABACUS: Used by Chanty to count the days.";
                imageResource = R.drawable.abacus;
                relicsFound++;
                score += 10;
                break;
            case "OIL_LAMP":
                message = "OIL LAMP: Used by Kilmanio to get to the gym.";
                imageResource = R.drawable.oil_lamp;
                relicsFound++;
                score += 10;
                break;
            case "BONE_NEEDLE":
                message = "BONE NEEDLE: Used by APH to knit a sweater.";
                imageResource = R.drawable.needle;
                relicsFound++;
                score += 10;
                break;
            case "PAPYRUS":
                message = "PAPYRUS: Used by Rapid to jot down new Spanish words.";
                imageResource = R.drawable.papyrus;
                relicsFound++;
                score += 10;
                break;
            case "PREHISTORIC_SANDAL":
                message = "PREHISTORIC SANDAL: Found by Amon behind the sofa.";
                imageResource = R.drawable.sandal;
                relicsFound++;
                score += 10;
                break;
            case "CURSE":
                message = "CURSE: Oops! Something bad happened!";
                imageResource = R.drawable.hand;
                cursesFound++;
                score -= 5;  // Decrease score for curses
                break;
            case "NOTHING":
                message = "NOTHING: You found nothing... Try again!";
                imageResource = R.drawable.nothing;
                break;
        }

        // Update the button to show the image
        button.setBackgroundResource(imageResource);  // Set the image as background

        // Display the message at the bottom
        messageText.setText(message);
        messageText.setVisibility(View.VISIBLE);

        // Check if all relics have been found
        if (relicsFound == totalRelics) {
            finalMessageText.setText("Congratulations! You've found all relics! Your score: " + calculateScore() + "%");
            finalMessageText.setVisibility(View.VISIBLE);
        }
    }

    // Method to calculate the score as a percentage
    private double calculateScore() {
        double totalItems = relicsFound + cursesFound;
        double scorePercentage = (relicsFound * 10) - (cursesFound * 5);
        return Math.max(scorePercentage, 0); // Ensure score doesn't go negative
    }
}
