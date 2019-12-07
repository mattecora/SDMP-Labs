package cs478.project5.audioclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cs478.project5.aidl.ClipServiceInterface;

public class MainActivity extends AppCompatActivity {

    private final static String SERVICE_PKG = "cs478.project5.clipserver";
    private final static String SERVICE_CLS = "cs478.project5.clipserver.ClipService";
    private final static String ACTION_SONG_END = "cs478.project5.ACTION_SONG_END";

    private EditText songNumInput;
    private Button startServiceButton, stopServiceButton,
            playButton, pauseButton, resumeButton, stopButton;

    private ClipServiceInterface clipService;
    private boolean isStarted, isBound;

    private SongEndReceiver receiver;

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // Set variables
            clipService = ClipServiceInterface.Stub.asInterface(service);
            isBound = true;

            // Play the selected song
            onPlayButtonClick(playButton);

            // Enable/disable buttons
            updateUi();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            handleKilledService();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start the song end receiver
        receiver = new SongEndReceiver(this);
        registerReceiver(receiver, new IntentFilter(ACTION_SONG_END));

        // Get references to views
        songNumInput = findViewById(R.id.songNumInput);
        startServiceButton = findViewById(R.id.startServiceButton);
        stopServiceButton = findViewById(R.id.stopServiceButton);
        playButton = findViewById(R.id.playButton);
        pauseButton = findViewById(R.id.pauseButton);
        resumeButton = findViewById(R.id.resumeButton);
        stopButton = findViewById(R.id.stopButton);

        // Enable/disable buttons
        updateUi();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public void onStartServiceButtonClick(View view) {
        // Start the service
        startForegroundService(getClipServiceIntent());
        isStarted = true;

        // Enable/disable buttons
        updateUi();
    }

    public void onStopServiceButtonClick(View view) {
        // Stop the service
        stopService(getClipServiceIntent());
        isStarted = false;

        // Enable/disable buttons
        updateUi();
    }

    public void onPlayButtonClick(View view) {
        // Bind the service if not already bound
        if (!isBound) {
            if (!bindService(getClipServiceIntent(), connection, 0))
                Toast.makeText(MainActivity.this, getString(R.string.service_cannot_bind_msg), Toast.LENGTH_SHORT).show();
        } else {
            // Play the selected song
            try {
                // Read the selected song from EditText
                int songNum = Integer.parseInt(songNumInput.getText().toString());

                // Call the remote service
                int duration = clipService.play(songNum);

                if (duration > 0)
                    Toast.makeText(MainActivity.this,
                            getString(R.string.song_started_msg, formatDuration(duration)), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,
                            getString(R.string.track_not_avail_msg), Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, getString(R.string.invalid_num_msg), Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                handleKilledService();
            }
        }

        // Enable/disable buttons
        updateUi();
    }

    public void onPauseButtonClick(View view) {
        // Check that the service is bound
        if (!isBound) return;

        // Pause the playback
        try {
            clipService.pause();
        } catch (RemoteException e) {
            handleKilledService();
        }

        updateUi();
    }

    public void onResumeButtonClick(View view) {
        // Check that the service is bound
        if (!isBound) return;

        // Resume the playback
        try {
            clipService.resume();
        } catch (RemoteException e) {
            handleKilledService();
        }

        // Enable/disable buttons
        updateUi();
    }

    public void onStopButtonClick(View view) {
        // Check that the service is bound
        if (!isBound) return;

        // Stop the playback
        try {
            clipService.stop();
        } catch (RemoteException e) {
            handleKilledService();
        }

        // Unbind the service
        unbindService(connection);
        isBound = false;

        // Enable/disable buttons
        updateUi();
    }

    public Intent getClipServiceIntent() {
        // Create explicit intent
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLS));

        return intent;
    }

    public void updateUi() {
        // Enable/disable startServiceButton
        startServiceButton.setEnabled(!isStarted);

        // Enable/disable stopServiceButton
        stopServiceButton.setEnabled(isStarted);

        // Enable/disable playButton
        playButton.setEnabled(isStarted);

        // Enable/disable pauseButton
        pauseButton.setEnabled(isStarted && isBound);

        // Enable/disable resumeButton
        resumeButton.setEnabled(isStarted && isBound);

        // Enable/disable stopButton
        stopButton.setEnabled(isStarted && isBound);
    }

    public void handleKilledService() {
        // Show toast message
        Toast.makeText(MainActivity.this, getString(R.string.service_killed_msg), Toast.LENGTH_SHORT).show();

        // Reset variables
        isStarted = false;
        isBound = false;

        // Enable/disable buttons
        updateUi();
    }

    public void handleSongEnd() {
        // Show toast message
        Toast.makeText(this, getString(R.string.song_ended_msg), Toast.LENGTH_SHORT).show();

        // Unbind the service
        unbindService(connection);

        // Reset variables
        isBound = false;

        // Enable/disable buttons
        updateUi();
    }

    public String formatDuration(int duration) {
        int msec = duration % 1000;
        int sec = duration / 1000;
        int min = sec / 60;

        return min + ":" + (sec - 60*min) + ":" + msec;
    }

}
