package cs478.project5.audioclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StatusResponseReceiver extends BroadcastReceiver {

    private MainActivity activity;

    public StatusResponseReceiver(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        activity.onStartServiceButtonClick(null);
        Toast.makeText(context, context.getString(R.string.service_already_started_msg), Toast.LENGTH_SHORT).show();
    }

}
