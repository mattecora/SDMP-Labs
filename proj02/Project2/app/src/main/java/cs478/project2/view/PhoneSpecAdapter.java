package cs478.project2.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cs478.project2.R;
import cs478.project2.model.PhoneSpec;

public class PhoneSpecAdapter extends BaseAdapter {

    private class ViewHolder {
        final TextView specNameText, specValueText;

        ViewHolder(View convertView) {
            specNameText = convertView.findViewById(R.id.specNameText);
            specValueText = convertView.findViewById(R.id.specValueText);
        }

        void update(PhoneSpec spec) {
            specNameText.setText(spec.getSpecName());
            specValueText.setText(spec.getSpecValue());
        }
    }

    private Context context;
    private List<PhoneSpec> specs;

    public PhoneSpecAdapter(Context context, List<PhoneSpec> specs) {
        this.context = context;
        this.specs = specs;
    }

    @Override
    public int getCount() {
        return specs.size();
    }

    @Override
    public Object getItem(int position) {
        return specs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Possibly recycle old view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.phone_spec_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }

        // Get current PhoneSpec object and update views
        PhoneSpec spec = specs.get(position);
        ViewHolder vh = (ViewHolder) convertView.getTag();
        vh.update(spec);

        return convertView;
    }

}
