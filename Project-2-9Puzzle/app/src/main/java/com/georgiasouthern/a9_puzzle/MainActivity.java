package com.georgiasouthern.a9_puzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    int clickedId;
    int emptyId = R.id.butNine;
    Map<Integer, int[]> gameMap = new HashMap<>(); //ID, coordinates in theoretical 2d array


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Mapping button ID's
        gameMap.put(R.id.butOne, new int[]{0, 0});
        gameMap.put(R.id.butTwo, new int[]{0, 1});
        gameMap.put(R.id.butThree, new int[]{0, 2});
        gameMap.put(R.id.butFour, new int[]{1, 0});
        gameMap.put(R.id.butFive, new int[]{1, 1});
        gameMap.put(R.id.butSix, new int[]{1, 2});
        gameMap.put(R.id.butSeven, new int[]{2, 0});
        gameMap.put(R.id.butEight, new int[]{2, 1});
        gameMap.put(R.id.butNine, new int[]{2, 2});
    }

    public void buttonClick(View view) {
        clickedId = view.getId();
        Button buttonClicked = findViewById(clickedId);
        Button emptyButton = findViewById(emptyId);
        if(clickedId == emptyId) {
            Toast.makeText(this, "Click a square with a number on it.", Toast.LENGTH_SHORT).show();
        } else {
            if(validMove(clickedId, emptyId)) {
                emptyButton.setText(buttonClicked.getText());
                buttonClicked.setText("");
                emptyId = clickedId;
            } else {
                Toast.makeText(this, "Invalid move", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Checks for valid move
    private boolean validMove(int clickedId, int emptyId) {
        int[] clickValues = gameMap.get(clickedId);
        int[] emptyValues = gameMap.get(emptyId);
        if ((clickValues[0] == emptyValues[0] && Math.abs(clickValues[1] - emptyValues[1]) < 2) || //if same row and adjacent
                (clickValues[1] == emptyValues[1] && Math.abs(clickValues[0] - emptyValues[0]) < 2)) { //if same column and adjacent
            return true;
        }
        return false;
    }
}