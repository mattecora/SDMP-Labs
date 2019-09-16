package cs478.project1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String address;

    private Button addressButton;
    private Button mapButton;
    private TextView addressText;

    private final static int REQ_ADDR_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        address = "";
        addressButton = findViewById(R.id.addressButton);
        mapButton = findViewById(R.id.mapButton);
        addressText = findViewById(R.id.addressText);

        addressButton.setOnClickListener(v -> startAddressActivity());
        mapButton.setOnClickListener(v -> startMapActivity());
    }

    private void startAddressActivity() {
        // Start AddressActivity with explicit intent
        Intent intent = new Intent(this, AddressActivity.class);
        startActivityForResult(intent, REQ_ADDR_CODE);
    }

    private void startMapActivity() {
        // Start map application with implicit intent
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + Uri.encode(address)));
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check result code
        if (requestCode == REQ_ADDR_CODE && resultCode == RESULT_OK && data != null) {
            // Update address text
            address = data.getStringExtra("address");
            addressText.setText("Inserted address: " + address);
        } else {
            // Show a toast message
            Toast.makeText(this, "You have inserted no text!", Toast.LENGTH_SHORT).show();
        }
    }

}
