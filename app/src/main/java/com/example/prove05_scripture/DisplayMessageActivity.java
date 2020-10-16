package com.example.prove05_scripture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayMessageActivity extends AppCompatActivity {
    private static final String TAG = "DisplayActivity";
    public static final String SAVE_MESSAGE = "com.example.prove05_scripture.SAVE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Bundle bundle = getIntent().getExtras();
        String _scripture = "";
        if (bundle != null){
            String book = bundle.getString("book");
            String chapter = bundle.getString("chapter");
            String verse = bundle.getString("verse");
            _scripture = book + " " + chapter + ":" + verse;
        }
        Log.d(TAG, "Received intent with " + _scripture);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.scripture);
        textView.setText(_scripture);
    }

    public void saveScripture(View view) {
        // Storing data into SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("SavedScriptures", MODE_PRIVATE);

        // Creating an Editor object
        // to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        //loading items from main activity
        Bundle bundle = getIntent().getExtras();
        String book = "";
        String chapter = "";
        String verse = "";
        if (bundle != null){
            book = bundle.getString("book");
            chapter = bundle.getString("chapter");
            verse = bundle.getString("verse");

        }

        // Storing the key and its value
        myEdit.putString("book", book);
        myEdit.putString("chapter", chapter);
        myEdit.putString("verse", verse);


        // Once the changes have been made,
        // we need to commit to apply those changes made,
        // otherwise, it will throw an error
        Log.d(TAG, "saving " + book + " " + chapter + ":" + verse);
        myEdit.apply();

        Context context = getApplicationContext();
        CharSequence text = "Saved Scripture";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

}