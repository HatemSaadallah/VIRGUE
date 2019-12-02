package com.example.virgue;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;

public class ChallengeTwo extends AppCompatActivity {


    int levelIndex = 0;
    JSONObject currentObject = null;
    ImageView iv;
    ImageButton imageButton1, imageButton2, imageButton3, imageButton4 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_two);

        imageButton1 = (ImageButton) findViewById(R.id.imageButton4);
        imageButton2 = (ImageButton) findViewById(R.id.imageButton7);
        imageButton3 = (ImageButton) findViewById(R.id.imageButton6);
        imageButton4 = (ImageButton) findViewById(R.id.imageButton5);
        iv =  findViewById(R.id.sound);


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mp = MediaPlayer.create(getBaseContext(),R.raw.lionve);
                mp.start();

            }
        });
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (currentObject.getString("correctAnswer").matches(currentObject.getJSONArray("images").get(0).toString())) {
                        Toast.makeText(ChallengeTwo.this, "Correct Answer", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(ChallengeTwo.this, "Wrong Answer", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ChallengeTwo.this, LionWord.class);
                startActivity(in);
                try {
                    if (currentObject.getString("correctAnswer").matches(currentObject.getJSONArray("images").get(1).toString())) {
                        Toast.makeText(ChallengeTwo.this, "Correct Answer", Toast.LENGTH_LONG).show();


                    } else {
                        Toast.makeText(ChallengeTwo.this, "Wrong Answer", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (currentObject.getString("correctAnswer").matches(currentObject.getJSONArray("images").get(2).toString())) {
                        Toast.makeText(ChallengeTwo.this, "Correct Answer", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ChallengeTwo.this, "Wrong Answer", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (currentObject.getString("correctAnswer").matches(currentObject.getJSONArray("images").get(3).toString())) {
                        Toast.makeText(ChallengeTwo.this, "Correct Answer", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ChallengeTwo.this, "Wrong Answer", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        loadData(levelIndex);
    }

    public void loadData(int level) {
        try {
            JSONArray dataArray = new JSONArray(loadJSONFromAsset(ChallengeTwo.this));
            JSONObject dataLevel = dataArray.getJSONObject(level);
            currentObject = dataLevel;

            JSONArray images = dataLevel.getJSONArray("images");

            String image1 = images.get(0).toString();
            int resID = getResources().getIdentifier(image1, "drawable", getPackageName());
            imageButton1.setBackgroundResource(resID);

            String image2 = images.get(1).toString();
            int res1 = getResources().getIdentifier(image2, "drawable", getPackageName());
            imageButton2.setBackgroundResource(res1);

            String image3 = images.get(2).toString();
            int res2 = getResources().getIdentifier(image3, "drawable", getPackageName());
            imageButton3.setBackgroundResource(res2);

            String image4 = images.get(3).toString();
            int res3 = getResources().getIdentifier(image4, "drawable", getPackageName());
            imageButton4.setBackgroundResource(res3);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("levels.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}

