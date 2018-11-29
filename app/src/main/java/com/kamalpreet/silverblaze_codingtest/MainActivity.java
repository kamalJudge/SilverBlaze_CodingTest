package com.kamalpreet.silverblaze_codingtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private StringBuilder text = new StringBuilder();
    ArrayList<String> listWord = new ArrayList<>();
    ArrayList<Integer> listWordOccurrence = new ArrayList<>();

    private String[] word;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //reads text file
        readFromText();

        // find occurrences and sort
        findOccurrences(word);

        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext() , OccurrencesActivity.class);
                i.putExtra("listOccurrence", listWordOccurrence);
                i.putExtra("listWord", listWord);
                startActivity(i);
            }
        });



    }

    private void readFromText()
    {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("codingChallenge.txt")));

            // do reading, usually loop until end of file reading
            String mLine;

            while ((mLine = reader.readLine()) != null)
            {
                word = mLine.split(" ");
                text.append(mLine);
                text.append('\n');
            }
        } catch (IOException e)
        {
            Toast.makeText(getApplicationContext(),"Error reading file!",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally
        {
            if (reader != null) {
                try
                {
                    reader.close();
                } catch (IOException e)
                {
                    //log the exception
                }
            }

            TextView output= (TextView) findViewById(R.id.textView);
            output.setText((CharSequence) text);

        }
    }

    private void findOccurrences(String[] word)
    {
        int occurrence;
        for (int i = 0; i < word.length; i++) {
            occurrence = 0;

            //confirm if already checked occurence
            for (int k = 0; k <= i; k++)
            {
                if (word[i].equalsIgnoreCase(word[k])) {
                    occurrence += 1;
                }
            }
            if (occurrence > 1)
            {
                // already checked
                continue;
            }

            //finds how many times word repeats
            occurrence = 0;
            for (int j = 0; j < word.length; j++) {
                if (word[i].equalsIgnoreCase(word[j])) {
                    occurrence += 1;
                }
            }

            //sort list
            //listWord.add("Word : " + word[i] + "  Occurrence : " + occurrence);
            if(listWordOccurrence.isEmpty())
            {
                listWordOccurrence.add(occurrence);
                listWord.add(word[i]);
            }
            else if(occurrence>listWordOccurrence.get(0))
            {
                listWordOccurrence.add(0, occurrence);
                listWord.add(0, word[i]);
            }
            else if(occurrence<listWordOccurrence.get(0) && occurrence>listWordOccurrence.get(1))
            {
                listWordOccurrence.add(1, occurrence);
                listWord.add(1, word[i]);
            }
            else{
                listWordOccurrence.add(occurrence);
                listWord.add(word[i]);
            }
        }


    }


}
