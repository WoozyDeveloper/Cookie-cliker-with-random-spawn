package com.adiio.stressclicker333;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int cont=0;
    ImageButton pressM;
    TextView txt;
    private String text="0";
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String TEXT="text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pressM=(ImageButton) findViewById(R.id.imageButton2);
        txt=(TextView)findViewById(R.id.textView);

        pressM.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                smecherie();
            }
        });
        loadData();
        updateData();

    }

    public void smecherie()
    {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        Random rand = new Random();
        int  n = rand.nextInt(height-300) + 1;
        int m=rand.nextInt(width-300) + 1;
        pressM.setTranslationX(m);
        pressM.setTranslationY(-n);
        ++cont;
        txt.setText(cont+"");
        saveData();
    }

    public void saveData()
    {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString(TEXT, txt.getText().toString());
        editor.apply();
    }

    public void loadData()
    {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        text=sharedPreferences.getString(TEXT,"");
    }

    public void updateData()
    {
        txt.setText(text);
        cont=strToInt(text);
    }

    public static int strToInt( String str ){
        int i = 0;
        int num = 0;

        while( i < str.length()) {
            num *= 10;
            num += str.charAt(i++) - '0';
        }
        return num;
    }
}