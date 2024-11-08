package com.example.relichunter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private TextView messageText, finalMessageText;
    private ArrayList<String> items; // List to store grid items (relics, curses, nothing)
    private int relicsFound = 0;
    private int totalRelics = 7; // Number of relics to be found

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tableLayout = findViewById(R.id.tableLayout);
        messageText = findViewById(R.id.messageText);
        finalMessageText = findViewById(R.id.finalMessageText);

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
                button.setText("Stone " + (index + 1));
                button.setTag(items.get(index));  // Assign item type to button tag

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
                message = "STONE AXE: Found by Zealsprince in Jokler’s garden. He’s not sure what to make of it...";
                imageResource = R.drawable.axe;  // Add the actual image in your drawable folder
                break;
            case "COMPASS":
                message = "COMPASS: Used by tmtu during his last hike when he forgot his GPS at home.";
                imageResource = R.drawable.compass;  // Add the actual image in your drawable folder
                break;
            case "ABACUS":
                message = "ABACUS: Used by Chanty to count the days until the next Advent calendar starts.";
                imageResource = R.drawable.abacus;  // Add the actual image in your drawable folder
                break;
            case "OIL_LAMP":
                message = "OIL LAMP: Used by Kilmanio to get to the gym at night after forgetting his flashlight in a Hardangervidda cabin.";
                imageResource = R.drawable.oil_lamp;  // Add the actual image in your drawable folder
                break;
            case "BONE_NEEDLE":
                message = "BONE NEEDLE: Used by APH to knit a new Godot hat and a sweater for Amon.";
                imageResource = R.drawable.needle;  // Add the actual image in your drawable folder
                break;
            case "PAPYRUS":
                message = "PAPYRUS: Used by Rapid to jot down new Spanish words when he couldn’t find his notebook.";
                imageResource = R.drawable.papyrus;  // Add the actual image in your drawable folder
                break;
            case "PREHISTORIC_SANDAL":
                message = "PREHISTORIC SANDAL: Found by Amon behind the sofa while Dolan was playing Lethal Company.";
                imageResource = R.drawable.sandal;  // Add the actual image in your drawable folder
                break;
            case "CURSE":
                message = "CURSE: Oops! Something bad happened!";
                imageResource = R.drawable.hand;  // Add the curse image in your drawable folder
                break;
            case "NOTHING":
                message = "NOTHING: You found nothing... Try again!";
                imageResource = R.drawable.nothing;  // Add the nothing image in your drawable folder
                break;
        }

        // Update the button to show the image
        button.setBackgroundResource(imageResource);  // Set the image as background

        // Display the message at the bottom
        messageText.setText(message);
        messageText.setVisibility(View.VISIBLE);

        // Count the relics found
        if (itemType.equals("STONE_AXE") || itemType.equals("COMPASS") || itemType.equals("ABACUS")
                || itemType.equals("OIL_LAMP") || itemType.equals("BONE_NEEDLE") || itemType.equals("PAPYRUS")
                || itemType.equals("PREHISTORIC_SANDAL")) {
            relicsFound++;
        }

        // Check if all relics have been found
        if (relicsFound == totalRelics) {
            finalMessageText.setText("Congratulations! You've found all relics! Your score: " + relicsFound + "/" + totalRelics);
            finalMessageText.setVisibility(View.VISIBLE);
        }
    }



}
