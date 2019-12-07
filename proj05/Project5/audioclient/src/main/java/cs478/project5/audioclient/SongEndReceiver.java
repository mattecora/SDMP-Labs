package cs478.project5.audioclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SongEndReceiver extends BroadcastReceiver {

    private MainActivity activity;

    public SongEndReceiver(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        activity.handleSongEnd();
    }

}
