package cs478.project2.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import cs478.project2.R;
import cs478.project2.model.Phone;
import cs478.project2.model.PhoneDatabase;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    public static final int RC_PICTURE_ACTIVITY = 1;

    ListView phonesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the adapter for the phones list
        phonesList = findViewById(R.id.phonesList);
        phonesList.setAdapter(new PhoneListAdapter(this, PhoneDatabase.ALL_PHONES));

        // Set the listeners for the phones list
        phonesList.setOnItemClickListener(this);
        phonesList.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Create a dialog window
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the title with the model of the phone
        builder.setTitle(PhoneDatabase.ALL_PHONES.get(position).getModel());

        // Set the dialog items from string array and click listeners
        builder.setItems(getResources().getStringArray(R.array.click_menu_items), (dialog, which) -> {
            switch (which) {
                case 0:
                    // First option selected, show phone specs
                    startSpecActivity(PhoneDatabase.ALL_PHONES.get(position));
                    break;
                case 1:
                    // Second option selected, show phone image
                    startPictureActivity(PhoneDatabase.ALL_PHONES.get(position));
                    break;
                case 2:
                    // Third option selected, show phone website
                    startBrowserActivity(PhoneDatabase.ALL_PHONES.get(position));
                    break;
            }
        });

        // Create and show the dialog
        builder.create().show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        // Show the phone picture
        startPictureActivity(PhoneDatabase.ALL_PHONES.get(position));

        // Do not call onItemClick also
        return true;
    }

    public void startSpecActivity(Phone phone) {
        // Create explicit intent and start PhoneSpecActivity
        Intent intent = new Intent(getApplicationContext(), PhoneSpecActivity.class);
        intent.putExtra("phone", phone);
        startActivity(intent);
    }

    public void startPictureActivity(Phone phone) {
        // Create explicit intent and start PhoneImageActivity
        Intent intent = new Intent(getApplicationContext(), PhoneImageActivity.class);
        intent.putExtra("phone", phone);
        startActivityForResult(intent, RC_PICTURE_ACTIVITY);
    }

    public void startBrowserActivity(Phone phone) {
        // Create implicit intent and start the browser
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(phone.getUrl()));
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // If returning from PhonePictureActivity, ask the GC to run to unload high-res image
        if (requestCode == RC_PICTURE_ACTIVITY)
            System.gc();
    }

}
