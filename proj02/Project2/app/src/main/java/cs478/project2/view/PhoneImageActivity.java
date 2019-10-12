package cs478.project2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import cs478.project2.R;
import cs478.project2.model.Phone;

public class PhoneImageActivity extends AppCompatActivity {

    private Phone phone;
    private ImageView bigPhoneImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_image);

        // Get serializable Phone object from intent
        phone = (Phone) getIntent().getSerializableExtra("phone");
        if (phone == null) {
            finish();
            return;
        }

        // Set activity title to phone model
        setTitle(phone.getModel());

        // Set image to high-res phone image
        bigPhoneImage = findViewById(R.id.bigPhoneImage);
        bigPhoneImage.setImageResource(phone.getPictureResource());
    }

    public void startBrowserActivity(View view) {
        // Create implicit intent and start the browser
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(phone.getUrl()));
        startActivity(intent);
    }

}
