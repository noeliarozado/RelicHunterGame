package com.example.relichunter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private TextView messageTextView;
    private Button endGameButton;

    private String[] relics = {"AXE", "COMPASS", "ABACUS", "OIL_LAMP", "NEEDLE", "PAPYRUS", "SANDAL"};
    private boolean[] tileRevealed = new boolean[16];
    private int relicsFound = 0;
    private final String[] messages = {
            "STONE AXE: Found by Zealsprince in Jokler’s garden. He’s not sure what to make of it...",
            "COMPASS: Used by tmtu during his last hike when he forgot his GPS at home.",
            "ABACUS: Used by Chanty to count the days until the next Advent calendar starts.",
            "OIL LAMP: Used by Kilmanio to get to the gym at night after forgetting his flashlight in a Hardangervidda cabin.",
            "BONE NEEDLE: Used by APH to knit a new Godot hat and a sweater for Amon.",
            "PAPYRUS: Used by Rapid to jot down new Spanish words when he couldn’t find his notebook.",
            "PREHISTORIC SANDAL: Found by Amon behind the sofa while Dolan was playing Lethal Company."
    };

    private final String[] relicImages = {
            "stone_axe", "compass", "abacus", "oil_lamp", "bone_needle", "papyrus", "sandal"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gridLayout = findViewById(R.id.gridLayout);
        messageTextView = findViewById(R.id.messageTextView);
        endGameButton = findViewById(R.id.endGameButton);

        // Set up the grid with clickable ImageViews
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            final ImageView tile = (ImageView) gridLayout.getChildAt(i);
            final int index = i;
            tile.setImageResource(R.drawable.stone);  // Default image (could be a stone or similar)

            // Set a random relic for the tile
            tile.setTag(relics[(int) (Math.random() * relics.length)]);

            tile.setOnClickListener(v -> onTileClicked(tile, index));
        }
    }

    private void onTileClicked(ImageView tile, int index) {
        // If the tile is already revealed, do nothing
        if (tileRevealed[index]) {
            return;
        }

        tileRevealed[index] = true;
        String relic = (String) tile.getTag();
        String message = getMessageForRelic(relic);
        tile.setImageResource(getImageForRelic(relic));  // Change the image based on the relic

        messageTextView.setText(message);

        // Check if the game is over
        relicsFound++;
        if (relicsFound == relics.length) {
            endGameButton.setVisibility(View.VISIBLE);
            endGameButton.setText("Congratulations! You found all the relics. Score: " + relicsFound + "/" + relics.length);
        }
    }

    private String getMessageForRelic(String relic) {
        switch (relic) {
            case "AXE":
                return messages[0];
            case "COMPASS":
                return messages[1];
            case "ABACUS":
                return messages[2];
            case "OIL_LAMP":
                return messages[3];
            case "NEEDLE":
                return messages[4];
            case "PAPYRUS":
                return messages[5];
            case "SANDAL":
                return messages[6];
            default:
                return "No message available.";
        }
    }

    private int getImageForRelic(String relic) {
        switch (relic) {
            case "AXE":
                return R.drawable.axe;  // Replace with your actual image resource
            case "COMPASS":
                return R.drawable.compass;
            case "ABACUS":
                return R.drawable.abacus;
            case "OIL_LAMP":
                return R.drawable.oil_lamp;
            case "NEEDLE":
                return R.drawable.needle;
            case "PAPYRUS":
                return R.drawable.papyrus;
            case "SANDAL":
                return R.drawable.sandal;
            default:
                return R.drawable.stone;  // Default image for nothing
        }
    }
}
