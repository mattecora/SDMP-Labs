package cs478.project3c.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cs478.project3.R;

public class PhoneImageFragment extends Fragment {

    private ImageView phoneImage;
    private int imgResource = -1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View retView = inflater.inflate(R.layout.fragment_phone_image, container, false);
        phoneImage = retView.findViewById(R.id.phoneImage);
        if (imgResource != -1)
            phoneImage.setImageResource(imgResource);

        return retView;
    }

    public void setPhoneImage(int imgResource) {
        this.imgResource = imgResource;
        if (phoneImage != null)
            phoneImage.setImageResource(imgResource);
    }

}
