package cs478.project3c.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import cs478.project3.R;
import cs478.project3c.model.PhoneDatabase;

public class MainActivity extends AppCompatActivity implements PhoneListFragment.OnPhoneListItemSelectedListener {

    private PhoneListFragment phoneListFragment;
    private PhoneImageFragment phoneImageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieve or create fragments
        FragmentManager fm = getSupportFragmentManager();

        phoneListFragment = (PhoneListFragment) fm.findFragmentByTag("phoneListFragment");
        if (phoneListFragment == null) {
            phoneListFragment = new PhoneListFragment();
            fm.beginTransaction()
                    .replace(R.id.fragmentContainer1, phoneListFragment, "phoneListFragment")
                    .commit();
        }

        phoneImageFragment = (PhoneImageFragment) fm.findFragmentByTag("phoneImageFragment");
        if (phoneImageFragment == null) {
            phoneImageFragment = new PhoneImageFragment();
        }

        fm.addOnBackStackChangedListener(this::adjustFragmentLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.option_open_AB:
            sendOrderedBroadcast(new Intent(), "edu.uic.cs478.f19.kaboom");
            break;
        case R.id.option_close_app:
            finish();
            break;
        }
        return true;
    }

    @Override
    public void onPhoneListItemSelected(int selected) {
        // Add image fragment if not added
        if (!phoneImageFragment.isAdded()) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction t = fm.beginTransaction();
            t.add(R.id.fragmentContainer2, phoneImageFragment, "phoneImageFragment");
            t.addToBackStack(null);
            t.commit();
        }

        // Set the phone image and adjust the layout
        phoneImageFragment.setPhoneImage(PhoneDatabase.ALL_PHONES.get(selected).getPictureResource());
        adjustFragmentLayout();
    }

    public void adjustFragmentLayout() {
        // Get fragment containers
        FrameLayout fragmentContainer1 = findViewById(R.id.fragmentContainer1);
        FrameLayout fragmentContainer2 = findViewById(R.id.fragmentContainer2);

        if (phoneImageFragment.isAdded()) {
            // Show the corresponding FrameLayout and hide the other if in portrait
            fragmentContainer2.setVisibility(View.VISIBLE);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                fragmentContainer1.setVisibility(View.GONE);
        } else {
            // Hide the corresponding FrameLayout and show the other if in portrait
            fragmentContainer2.setVisibility(View.GONE);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                fragmentContainer1.setVisibility(View.VISIBLE);
        }
    }

}