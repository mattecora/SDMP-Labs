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
        if (phone == null)
            finish();

        // Retrieve views
        ImageView phoneThumbnailImageSpec = findViewById(R.id.phoneThumbnailImageSpec);
        TextView brandModelTextSpec = findViewById(R.id.brandModelTextSpec);
        TextView screenPriceTextSpec = findViewById(R.id.screenPriceTextSpec);
        ListView specsList = findViewById(R.id.specsList);

        // Set views to match current Phone
        phoneThumbnailImageSpec.setImageResource(phone.getLowResPicture());
        brandModelTextSpec.setText(getString(R.string.brand_model_text, phone.getBrand(), phone.getModel()));
        screenPriceTextSpec.setText(getString(R.string.screen_size_price_text, phone.getScreenSize(), phone.getPriceRange()));
        specsList.setAdapter(new PhoneSpecAdapter(this, phone.getSpecs()));
    }

}
