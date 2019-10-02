package cs478.project2.view;

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

    ListView phonesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phonesList = findViewById(R.id.phonesList);
        phonesList.setAdapter(new PhoneListAdapter(this, PhoneDatabase.ALL_PHONES));

        phonesList.setOnItemClickListener(this);
        phonesList.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(PhoneDatabase.ALL_PHONES.get(position).getModel());
        builder.setItems(new String[]{"Show specs", "Show picture", "Show website"}, (dialog, which) -> {
            switch (which) {
                case 0:
                    startSpecActivity(PhoneDatabase.ALL_PHONES.get(position));
                    break;
                case 1:
                    startPictureActivity(PhoneDatabase.ALL_PHONES.get(position));
                    break;
                case 2:
                    startBrowserActivity(PhoneDatabase.ALL_PHONES.get(position));
                    break;
            }
        });
        builder.create().show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        startPictureActivity(PhoneDatabase.ALL_PHONES.get(position));
        return true;
    }

    public void startSpecActivity(Phone phone) {
        Intent intent = new Intent(getApplicationContext(), PhoneSpecActivity.class);
        intent.putExtra("phone", phone);
        startActivity(intent);
    }

    public void startPictureActivity(Phone phone) {
        Intent intent = new Intent(getApplicationContext(), PhoneImageActivity.class);
        intent.putExtra("phone", phone);
        startActivity(intent);
    }

    public void startBrowserActivity(Phone phone) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(phone.getUrl()));
        startActivity(intent);
    }

}
