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

    // Using ViewHolder pattern to limit the number of findViewById() operations
    private class ViewHolder {
        final TextView brandModelText, screenPriceText;
        final ImageView phoneThumbnailImage;

        ViewHolder(View convertView) {
            brandModelText = convertView.findViewById(R.id.brandModelText);
            screenPriceText = convertView.findViewById(R.id.screenPriceText);
            phoneThumbnailImage = convertView.findViewById(R.id.phoneThumbnailImage);
        }

        void update(Phone phone) {
            brandModelText.setText(context.getString(R.string.brand_model_text, phone.getBrand(), phone.getModel()));
            screenPriceText.setText(context.getString(R.string.screen_size_price_text, phone.getScreenSize(), phone.getPriceRange()));
            phoneThumbnailImage.setImageBitmap(ImageResizer.scaleImage(
                    context, phone.getPictureResource(),
                    phoneThumbnailImage.getLayoutParams().width, phoneThumbnailImage.getLayoutParams().height));
        }
    }

    private Context context;
    private List<Phone> phones;
    private LayoutInflater inflater;

    public PhoneListAdapter(Context context, List<Phone> phones) {
        this.context = context;
        this.phones = phones;
        this.inflater = LayoutInflater.from(context);
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
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.phone_list_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }

        // Get current Phone object and update views
        Phone phone = phones.get(position);
        ViewHolder vh = (ViewHolder) convertView.getTag();
        vh.update(phone);

        return convertView;
    }

}
