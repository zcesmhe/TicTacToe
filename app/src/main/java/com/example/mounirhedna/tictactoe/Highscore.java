package com.example.mounirhedna.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Highscore extends AppCompatActivity {

    TextView names;
    String scores;
    BufferedReader inputReader;
    StringBuffer stringBuffer;
    ArrayList<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        names = (TextView) findViewById(R.id.names);
        players = new ArrayList<Player>();
        stringBuffer = new StringBuffer();

        try {
            inputReader = new BufferedReader(new InputStreamReader(openFileInput("scores.txt")));
            try {
                while((scores = inputReader.readLine()) != null) {
                    players.add(createPlayer(scores));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Player> sortedList = sort(players);

        for(int i = 0; i < sortedList.size(); i++) {
            stringBuffer.append(sortedList.get(i).getName()
                     + " - " + sortedList.get(i).getScore() + "\n" );
        }

        names.setMovementMethod(new ScrollingMovementMethod());
        names.setText(stringBuffer);
    }

    private Player createPlayer(String s) {
        String[] nameAndScore = s.split(" ");
        String name = "";
        String score;
        if(nameAndScore.length > 2) {
            score = nameAndScore[nameAndScore.length-1];
            for(int i = 0; i < nameAndScore.length-1; i++) {
                name += nameAndScore[i] + " ";
            }
        }
        else {
            name = nameAndScore[0];
            score = nameAndScore[1];
        }
        Player p = new Player(name);
        p.setScore(Integer.parseInt(score));
        return p;
    }

    private ArrayList<Player> sort(ArrayList<Player> p) {
        int numSwaps = 0;
        for(int i = 0; i < players.size()-1; i++) {
            if(p.get(i).getScore() < p.get(i+1).getScore() ) {
                Player temp = p.get(i);
                p.set(i, p.get(i+1));
                p.set(i+1, temp);
                numSwaps++;
            }
        }
        if(numSwaps == 0) {
            return p;
        }
        else {
            sort(p);
        }
        return p;
    }

}
