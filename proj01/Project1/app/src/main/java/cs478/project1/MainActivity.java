package cs478.project1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String address;
    private TextView addressText;

    private final static int REQ_ADDR_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Try to read the address from the saved Bundle
        address = savedInstanceState == null ? null : savedInstanceState.getString("address");
        addressText = findViewById(R.id.addressText);

        // Update the view if address was saved
        if (address != null)
            addressText.setText(getString(R.string.inserted_address_text, address));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Necessary to keep the address when the screen is rotated
        super.onSaveInstanceState(outState);
        outState.putString("address", address);
    }

    public void startAddressActivity(View view) {
        // Start AddressActivity with explicit intent
        Intent intent = new Intent(getApplicationContext(), AddressActivity.class);
        startActivityForResult(intent, REQ_ADDR_CODE);
    }

    public void startMapActivity(View view) {
        if (address != null) {
            // Start map application with implicit intent
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + Uri.encode(address)));
            startActivity(intent);
        } else {
            // Show a toast message
            Toast.makeText(this, getString(R.string.no_inserted_address_toast), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check result code
        if (requestCode == REQ_ADDR_CODE && resultCode == RESULT_OK && data != null) {
            // Update address text
            address = data.getStringExtra("address");
            addressText.setText(getString(R.string.inserted_address_text, address));
        }
    }

}
