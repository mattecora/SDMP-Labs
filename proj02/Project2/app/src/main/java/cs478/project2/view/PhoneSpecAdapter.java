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
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.phone_spec_item, parent, false);

        PhoneSpec spec = specs.get(position);
        TextView specNameText = convertView.findViewById(R.id.specNameText);
        TextView specValueText = convertView.findViewById(R.id.specValueText);

        specNameText.setText(spec.getSpecName());
        specValueText.setText(spec.getSpecValue());

        return convertView;
    }
}
