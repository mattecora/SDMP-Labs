package cs478.project5.clipserver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.core.app.NotificationCompat;

import cs478.project5.aidl.ClipServiceInterface;

public class ClipService extends Service {

    private static final int NUM_SONGS = 8;

    private static final String CHANNEL_ID = "cs478.project5.clipserver.notifications";
    private static final int NOTIFICATION_ID = 1;

    private static final String CLIENT_PKG = "cs478.project5.audioclient";
    private static final String CLIENT_CLS = "cs478.project5.audioclient.MainActivity";

    private static final String ACTION_SONG_END = "cs478.project5.ACTION_SONG_END";
    private static final String ACTION_STATUS_REQ = "cs478.project5.ACTION_STATUS_REQ";

    private MediaPlayer player;
    private StatusRequestReceiver receiver;

    private final ClipServiceInterface.Stub binder = new ClipServiceInterface.Stub() {
        @Override
        public int play(int num) throws RemoteException {
            // Retrieve the song's resource ID
            int songRes = getResources()
                    .getIdentifier("track" + num, "raw", getPackageName());

            // Stop the old player
            if (player != null) {
                player.stop();
                player.release();
                player = null;
            }

            // Check that the song is valid
            if (songRes == 0)
                return -NUM_SONGS;

            // Prepare and start a new player
            player = MediaPlayer.create(getApplicationContext(), songRes);
            player.setOnCompletionListener(mp -> sendBroadcast(new Intent(ACTION_SONG_END)));
            player.start();

            return player.getDuration();
        }

        @Override
        public void pause() throws RemoteException {
            // Pause the player, if playing
            if (player != null && player.isPlaying())
                player.pause();
        }

        @Override
        public void resume() throws RemoteException {
            // Resume the player, if not playing
            if (player != null && !player.isPlaying())
                player.start();
        }

        @Override
        public void stop() throws RemoteException {
            // Stop the player, if any
            if (player != null) {
                player.stop();
                player.release();
                player = null;
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        // Register the status receiver
        receiver = new StatusRequestReceiver();
        registerReceiver(receiver, new IntentFilter(ACTION_STATUS_REQ));

        // Create the notification channel
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                "ClipService channel", NotificationManager.IMPORTANCE_DEFAULT);

        // Register the notification channel
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        // Create the pending intent
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(CLIENT_PKG, CLIENT_CLS));
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

        // Create the notification
        Notification notification =
                new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setSmallIcon(android.R.drawable.ic_media_play)
                        .setOngoing(true)
                        .setContentTitle(getString(R.string.notification_title))
                        .setContentText(getString(R.string.notification_content_text))
                        .setFullScreenIntent(pi, false)
                        .build();

        // Start in the foreground
        startForeground(NOTIFICATION_ID, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        // Stop and release the player, if any
        if (player != null) {
            player.stop();
            player.release();
        }

        // Unregister the receiver
        unregisterReceiver(receiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

}
