package cs478.project4.threading;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.Random;

import cs478.project4.model.Board;
import cs478.project4.view.GameActivity;

public abstract class SolverThread extends Thread {

    private int threadNo;
    private Handler myHandler, otherHandler;
    private GameActivity gameActivity;
    private int[] nextGuess;

    public SolverThread(GameActivity gameActivity, int threadNo) {
        this.gameActivity = gameActivity;
        this.threadNo = threadNo;
    }

    public int getThreadNo() {
        return threadNo;
    }

    public int getMode() {
        return gameActivity.getMode();
    }

    public Handler getMyHandler() {
        return myHandler;
    }

    public Handler getOtherHandler() {
        return otherHandler;
    }

    public void setOtherHandler(Handler otherHandler) {
        this.otherHandler = otherHandler;
    }

    @Override
    public void run() {
        // Prepare the looper
        Looper.prepare();

        // Create current thread's handler
        myHandler = new GuessResultHandler(this);

        // Insert a first message in the queue to start looping
        Message startMsg = myHandler.obtainMessage();
        startMsg.what = Board.INVALID;
        startMsg.sendToTarget();

        // Run the looper
        Looper.loop();
    }

    public void prepareNextGuess(int[] guess) {
        this.nextGuess = guess;
    }

    public void sendNextGuess() {
        if (nextGuess != null) {
            // Sleep for some time to allow user to notice changes
            Random rand = new Random();
            try {
                Thread.sleep(500 + rand.nextInt(1500));
            } catch (InterruptedException e) {}

            // Post a GuessAndUpdateUiRunnable to the UI thread
            gameActivity.getUiHandler().post(new GuessAndUpdateUiRunnable(nextGuess, gameActivity, this));
        }
    }

    public abstract int[] makeInitialGuess();
    public abstract int[] makeNearMissGuess(int[] oldGuess);
    public abstract int[] makeCloseGuessGuess(int[] oldGuess);
    public abstract int[] makeCompleteMissGuess(int[] oldGuess);
    public abstract int[] makeDisasterGuess(int[] oldGuess);

}
