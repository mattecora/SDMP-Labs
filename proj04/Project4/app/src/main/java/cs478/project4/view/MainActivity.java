package cs478.project4.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import cs478.project4.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set button handlers
        Button guessByGuessButton = findViewById(R.id.guessByGuessButton);
        guessByGuessButton.setOnClickListener((view) -> {
            Intent i = new Intent(getApplicationContext(), GameActivity.class);
            i.putExtra("mode", GameActivity.MODE_GUESS_BY_GUESS);
            startActivity(i);
        });

        Button continuousButton = findViewById(R.id.continuousButton);
        continuousButton.setOnClickListener((view) -> {
            Intent i = new Intent(getApplicationContext(), GameActivity.class);
            i.putExtra("mode", GameActivity.MODE_CONTINUOUS);
            startActivity(i);
        });
    }

}
