package com.example.mounirhedna.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstPage extends AppCompatActivity {

    Button play, highscores, quit;
    Player player1, player2;
    EditText player1Name, player2Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button play = (Button) findViewById(R.id.play);
        Button highscores = (Button) findViewById(R.id.highscores);
        Button quit = (Button) findViewById(R.id.quit);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                playGame();
            }
        });


        highscores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });



    }
    private void playGame(){

        player1Name = (EditText) findViewById(R.id.player1);
        player1 = new Player(player1Name.getText().toString());

        player2Name = (EditText) findViewById(R.id.player2);
        player2 = new Player(player2Name.getText().toString());

        Intent nextPage = new Intent(FirstPage.this, MainActivity.class);

        Bundle bundle = new Bundle();
        bundle.putParcelable("player1", player1);
        bundle.putParcelable("player2", player2);
        nextPage.putExtras(bundle);

        startActivity(nextPage);
    }

}
