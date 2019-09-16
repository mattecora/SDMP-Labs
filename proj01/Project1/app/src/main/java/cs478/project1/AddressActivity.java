package cs478.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

public class AddressActivity extends AppCompatActivity {

    private EditText addressInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        addressInput = findViewById(R.id.addressInput);
        addressInput.setOnEditorActionListener((v, id, event) -> {
            // Return to MainActivity if the user has finished
            if (id == EditorInfo.IME_ACTION_DONE)
                returnToMain();
            return true;
        });
    }

    private void returnToMain() {
        if (addressInput.getText().toString().equals("")) {
            // User has inserted no text
            setResult(RESULT_CANCELED);
        } else {
            // User has inserted some text
            Intent intent = new Intent();
            intent.putExtra("address", addressInput.getText().toString());
            setResult(RESULT_OK, intent);
        }
        finish();
    }

}
