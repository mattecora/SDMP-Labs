package cs478.project3a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class PhoneWebsiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_website);

        String url = getIntent().getStringExtra("phoneUrl");
        if (url == null) {
            finish();
        } else {
            WebView phoneWebView = findViewById(R.id.phoneWebView);
            phoneWebView.loadUrl(getIntent().getStringExtra("phoneUrl"));
            phoneWebView.getSettings().setLoadsImagesAutomatically(true);
            phoneWebView.getSettings().setJavaScriptEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
