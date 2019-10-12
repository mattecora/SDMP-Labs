package cs478.project2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import cs478.project2.R;
import cs478.project2.model.Phone;

public class PhoneSpecActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_spec);

        // Get serializable Phone object from intent
        Phone phone = (Phone) getIntent().getSerializableExtra("phone");
        if (phone == null) {
            finish();
            return;
        }

        // Retrieve views
        TextView brandModelText = findViewById(R.id.brandModelText);
        TextView screenPriceText = findViewById(R.id.screenPriceText);
        ImageView phoneThumbnailImage = findViewById(R.id.phoneThumbnailImage);
        ListView specsList = findViewById(R.id.specsList);

        // Set views to match current Phone
        brandModelText.setText(getString(R.string.brand_model_text, phone.getBrand(), phone.getModel()));
        screenPriceText.setText(getString(R.string.screen_size_price_text, phone.getScreenSize(), phone.getPriceRange()));

        phoneThumbnailImage.setImageBitmap(ImageResizer.scaleImage(
                this, phone.getPictureResource(),
                phoneThumbnailImage.getLayoutParams().width, phoneThumbnailImage.getLayoutParams().height));

        specsList.setAdapter(new PhoneSpecAdapter(this, phone.getSpecs()));
    }

}
