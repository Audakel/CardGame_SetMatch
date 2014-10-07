package com.example.root.SetCardGamev2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by audakel on 10/5/14.
 */
public class SortGameResult extends Activity{




    //Variables
    private String[] gameResult = {"234",",234","079","4823","29384", "8492","1204", "394","932", "9032", "432"};
    private ArrayList<String> gameResultArrayList = new ArrayList<String>();
    private Set<String> setOfGameStats = new HashSet<String>(Arrays.asList("", "", ""));


    //Initializers

    //Helper functions

    //Methods

        //Get Array from userDefaults
        public void load(FragmentActivity fragmentActivity){
            SharedPreferences prefs = fragmentActivity.getSharedPreferences("GameData", Context.MODE_PRIVATE);
            Map<String, String> entries = (Map<String, String>) prefs.getAll();
            Set<String> keys = entries.keySet();

            for (String key : keys) {
                //String scoreDisplay =  ": " + entries.get(key);
//                gameResultArrayList.add(entries.get(key));

                setOfGameStats = prefs.getStringSet(key, setOfGameStats);
                String[] arrayOfGameStats = setOfGameStats.toArray(new String[setOfGameStats.size()]);
                String endGameDate = arrayOfGameStats[0];
                String typeOfGame = arrayOfGameStats[1];
                String gameScore = arrayOfGameStats[2];
                //String startGameDate = arrayOfGameStats[3];
                gameResultArrayList.add(typeOfGame + " Score: " + gameScore + "    " + endGameDate);



            }
            gameResult = (String[]) gameResultArrayList.toArray(new String[gameResultArrayList.size()]);
        }

            //Parse out and set Start, End and Score variables for each game

        //Organize Array

            //By date

            //By length

            //By score

        //Return organized array
        public String[] sortResults(FragmentActivity fragmentActivity){
            load(fragmentActivity);
            return gameResult;
        }



}
