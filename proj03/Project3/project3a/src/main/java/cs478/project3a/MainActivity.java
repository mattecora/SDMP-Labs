package cs478.project3a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static String SHOW_WEBSITE_ACTION = "edu.uic.cs478.f19.showPhoneWebsite";
    private final static String START_3B_ACTION = "edu.uic.cs478.f19.start3B";

    private final static String KABOOM_PERMISSION = "edu.uic.cs478.f19.kaboom";
    private final static int KABOOM_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set button listener
        Button startReceiverButton = findViewById(R.id.startReceiverButton);
        startReceiverButton.setOnClickListener(v -> checkPermissionAndStartReceiver());
    }

    public void checkPermissionAndStartReceiver() {
        // Check for permission
        if (checkSelfPermission(KABOOM_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, start the receiver
            FirstReceiver receiver = new FirstReceiver();
            registerReceiver(receiver, new IntentFilter(SHOW_WEBSITE_ACTION));

            // Update the status TextView
            TextView receiverStatusText = findViewById(R.id.receiverStatusText);
            receiverStatusText.setText(R.string.receiver_started_text);

            // Show toast
            Toast.makeText(this, R.string.receiver_started_text, Toast.LENGTH_SHORT).show();

            // Start app 3B
            Intent intent = new Intent();
            intent.setAction(START_3B_ACTION);
            startActivity(intent);
        } else {
            // Permission not granted, ask for it
            requestPermissions(new String[]{KABOOM_PERMISSION}, KABOOM_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Check whether permission has been granted
        if (requestCode == KABOOM_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            checkPermissionAndStartReceiver();
        } else {
            Toast.makeText(this, R.string.error_permission_text, Toast.LENGTH_SHORT).show();
        }
    }

}
