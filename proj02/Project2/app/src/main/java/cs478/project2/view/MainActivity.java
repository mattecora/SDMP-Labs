package cs478.project2.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import cs478.project2.R;
import cs478.project2.model.Phone;
import cs478.project2.model.PhoneDatabase;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    public static final int RC_PICTURE_ACTIVITY = 1;

    private ListView phonesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the adapter for the phones list
        phonesList = findViewById(R.id.phonesList);
        phonesList.setAdapter(new PhoneListAdapter(this, PhoneDatabase.ALL_PHONES));

        // Enable the context menu for the phones list
        registerForContextMenu(phonesList);

        // Set the listeners for the phones list
        phonesList.setOnItemClickListener(this);
        phonesList.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // NOTE: implemented as in the first version, short click opens menu

        // Disable the LongClickListener (otherwise, it gets called by showContextMenuForChild)
        phonesList.setOnItemLongClickListener(null);

        // Show the context menu
        phonesList.showContextMenuForChild(view);

        // Restore the LongClickListener
        phonesList.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        // NOTE: implemented as in the first version, long click opens image

        // Show the phone picture
        startPictureActivity(PhoneDatabase.ALL_PHONES.get(position));

        // Do not call onItemClick also
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // Inflate the menu layout
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.phone_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // Get the info on the selected item
        AdapterView.AdapterContextMenuInfo info	= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.actionShowSpecs:
                // First option selected, show phone specs
                startSpecActivity(PhoneDatabase.ALL_PHONES.get(info.position));
                break;
            case R.id.actionShowPicture:
                // Second option selected, show phone image
                startPictureActivity(PhoneDatabase.ALL_PHONES.get(info.position));
                break;
            case R.id.actionShowWebsite:
                // Third option selected, show phone website
                startBrowserActivity(PhoneDatabase.ALL_PHONES.get(info.position));
                break;
        }

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
