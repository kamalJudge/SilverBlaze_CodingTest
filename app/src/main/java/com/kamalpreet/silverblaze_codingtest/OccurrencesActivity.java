package com.kamalpreet.silverblaze_codingtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class OccurrencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occurrences);

        ArrayList<String> wordList = getIntent().getStringArrayListExtra("listWord");
        ArrayList<Integer> occurrenceList = getIntent().getIntegerArrayListExtra("listOccurrence");

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        OccurrencesAdapter adapter = new OccurrencesAdapter(this, wordList, occurrenceList);
        recyclerView.setAdapter(adapter);
    }
}
