package cs478.project5.aidl;

interface ClipServiceInterface {
    // Play a song
    int play(int num);

    // Pause the playback
    void pause();

    // Resume the playback
    void resume();

    // Stop the playback altogether
    void stop();
}
