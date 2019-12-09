package cs478.project5.clipserver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StatusRequestReceiver extends BroadcastReceiver {

    private static final String ACTION_STATUS_RESP = "cs478.project5.ACTION_STATUS_RESP";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent statusIntent = new Intent(ACTION_STATUS_RESP);
        context.sendBroadcast(statusIntent);
    }

}
