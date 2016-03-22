package com.example.mounirhedna.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button a1, a2, a3, b1, b2, b3, c1, c2, c3, newGame; //Creating button variables.
    Button[] buttons;
    boolean xTurn = true; //True = X's turn, False = O's turn.
    boolean winnerFound = false;
    boolean gameOver = false;
    int numTurns = 0;
    Bundle bundle;
    Player player1;
    Player player2;
    TextView playerTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bundle = this.getIntent().getExtras();
        player1 = bundle.getParcelable("player1");
        player2 = bundle.getParcelable("player2");
        playerTurn = (TextView) findViewById(R.id.playerTurn);
        playerTurn.setText(player1.getName() + "'s Turn");

        a1 = (Button) findViewById(R.id.A1); // Linking the button variables
        a2 = (Button) findViewById(R.id.A2); //by ID with buttons created created.
        a3 = (Button) findViewById(R.id.A3);
        b1 = (Button) findViewById(R.id.B1);
        b2 = (Button) findViewById(R.id.B2);
        b3 = (Button) findViewById(R.id.B3);
        c1 = (Button) findViewById(R.id.C1);
        c2 = (Button) findViewById(R.id.C2);
        c3 = (Button) findViewById(R.id.C3);
        newGame = (Button) findViewById(R.id.newGame);

        buttons = new Button[]{a1, a2, a3, b1, b2, b3, c1, c2, c3}; //Button array to hold all
                                                                    //buttons created.
        for (Button b : buttons) {
            b.setOnClickListener(new View.OnClickListener() { //Creates and on click listener for
                                                              //each button in the array.
                @Override
                public void onClick(View v) {//This method is called every time a button is clicked.
                    Button b = (Button) v; //Button is a subclass of view so can be type casted.
                    buttonClicked(b);
                }
            });
        }
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    public void resetGame() {
        for(Button b : buttons) {
            b.setText("");
            b.setClickable(true);
        }
        xTurn = true;
        winnerFound = false;
        gameOver = false;
        numTurns = 0;
        playerTurn.setText(player1.getName() + "'s Turn");
    }

    public void buttonClicked(Button b) {

        if(xTurn) { //Change text to X.
            if(b.getText() == "X" || b.getText() == "O") { //Checks if box is chosen
                message("Box already chosen!");
            }
            else {
                b.setText("X");
                numTurns++;
                nextTurn();
            }
        }
        else { //Change text to O.
            if(b.getText() == "X" || b.getText() == "O") {
                message("Box already chosen!");
            }
            else {
                b.setText("O");
                numTurns++;
                nextTurn();
            }
        }

        checkForWinner(); //Checks if player has won.
        checkForDraw();//Checks is the game is a draw.

        if(winnerFound) { //Congratulates the winner.
            if(xTurn) {
                message(player2.getName() + " Wins!");
                player1.win();
                endGame();
            }
            else {
                message(player1.getName() + " Wins!");
                player2.win();
                endGame();
            }
        }
    }

    private void message(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void nextTurn() {
        if(xTurn) {
            xTurn = !xTurn;
            playerTurn.setText(player2.getName() + "'s Turn");
        }
        else {
            xTurn = !xTurn;
            playerTurn.setText(player1.getName() + "'s Turn");
        }
    }

    private void checkForWinner() {

        if(a1.getText() == a2.getText() && a1.getText() == a3.getText() && a1.getText() != "") {
            winnerFound = true;
        }
        else if(b1.getText() == b2.getText() && b1.getText() == b3.getText() && b1.getText() != ""){
            winnerFound = true;
        }
        else if(c1.getText() == c2.getText() && c1.getText() == c3.getText() && c1.getText() != ""){
            winnerFound = true;
        }
        else if(a1.getText() == b1.getText() && a1.getText() == c1.getText() && a1.getText() != ""){
            winnerFound = true;
        }
        else if(a2.getText() == b2.getText() && a2.getText() == c2.getText() && a2.getText() != ""){
            winnerFound = true;
        }
        else if(a3.getText() == b3.getText() && a3.getText() == c3.getText() && a3.getText() != ""){
            winnerFound = true;
        }
        else if(a1.getText() == b2.getText() && a1.getText() == c3.getText() && a1.getText() != ""){
            winnerFound = true;
        }
        else if(a3.getText() == b2.getText() && a3.getText() == c1.getText() && a3.getText() != ""){
            winnerFound = true;
        }
    }

    private void endGame() {
        for(Button b : buttons) {
            b.setClickable(false);
        }
    }

    private void checkForDraw() {
        if(numTurns == 9 && !winnerFound) {
            message("Draw!");
            endGame();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
