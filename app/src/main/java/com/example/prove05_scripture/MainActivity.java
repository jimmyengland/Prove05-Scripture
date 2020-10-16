package com.example.prove05_scripture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.prove05_scripture.MESSAGE";
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the save button */
    public void displayScripture(View view) {
        EditText editText = (EditText) findViewById(R.id.editBook);
        EditText chapter = (EditText) findViewById(R.id.editChapter);
        EditText verse = (EditText) findViewById(R.id.editVerse);
        Bundle bundle = new Bundle();
        bundle.putString("book", editText.getText().toString());
        bundle.putString("chapter", chapter.getText().toString());
        bundle.putString("verse", (verse.getText().toString()));
        String scripture = editText.getText().toString() + " " + chapter.getText().toString() + ":" + verse.getText().toString();
        Log.d(TAG, "About to create intent with " + scripture);
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void loadScripture(View view){
        SharedPreferences sharedPref = getSharedPreferences("SavedScriptures", Context.MODE_PRIVATE);
        String book = sharedPref.getString("book", "");
        String chapter = sharedPref.getString("chapter", "");
        String verse = sharedPref.getString("verse", "");

        TextView textView = (TextView) findViewById(R.id.editBook);
        textView.setText(book);
        TextView chap = (TextView) findViewById(R.id.editChapter);
        chap.setText(chapter);
        TextView ver = (TextView) findViewById(R.id.editVerse);
        ver.setText(verse);
    }
}