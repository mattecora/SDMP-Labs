package cs478.project2.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cs478.project2.R;
import cs478.project2.model.Phone;

public class PhoneListAdapter extends BaseAdapter {

    private Context context;
    private List<Phone> phones;

    public PhoneListAdapter(Context context, List<Phone> phones) {
        this.context = context;
        this.phones = phones;
    }

    @Override
    public int getCount() {
        return phones.size();
    }

    @Override
    public Object getItem(int position) {
        return phones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Possibly recycle old view
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.phone_list_item, parent, false);

        // Get current Phone object and retrieve views
        Phone phone = phones.get(position);
        TextView brandModelText = convertView.findViewById(R.id.brandModelText);
        TextView screenPriceText = convertView.findViewById(R.id.screenPriceText);
        ImageView phoneThumbnailImage = convertView.findViewById(R.id.phoneThumbnailImage);

        // Set views to match current Phone
        brandModelText.setText(context.getString(R.string.brand_model_text, phone.getBrand(), phone.getModel()));
        screenPriceText.setText(context.getString(R.string.screen_size_price_text, phone.getScreenSize(), phone.getPriceRange()));

        phoneThumbnailImage.setImageBitmap(ImageResizer.scaleImage(
                context, phone.getPictureResource(),
                phoneThumbnailImage.getLayoutParams().width, phoneThumbnailImage.getLayoutParams().height));

        return convertView;
    }

}
