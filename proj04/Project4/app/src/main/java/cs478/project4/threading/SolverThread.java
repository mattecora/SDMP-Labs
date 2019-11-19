package cs478.project4.threading;

import android.os.Handler;
import android.os.Looper;

import java.util.Random;

import cs478.project4.view.GameActivity;

public abstract class SolverThread extends Thread {

    private int threadNo;
    private GameActivity gameActivity;

    private Looper myLooper;
    private Handler myGuessResultHandler, myNextGuessHandler, otherNextGuessHandler;

    private int[] nextGuess;

    public SolverThread(GameActivity gameActivity, int threadNo) {
        this.gameActivity = gameActivity;
        this.threadNo = threadNo;
    }

    public int getThreadNo() {
        return threadNo;
    }

    public GameActivity getGameActivity() {
        return gameActivity;
    }

    public Looper getMyLooper() {
        return myLooper;
    }

    public Handler getMyGuessResultHandler() {
        return myGuessResultHandler;
    }

    public Handler getMyNextGuessHandler() {
        return myNextGuessHandler;
    }

    public Handler getOtherNextGuessHandler() {
        return otherNextGuessHandler;
    }

    public void setOtherNextGuessHandler(Handler otherNextGuessHandler) {
        this.otherNextGuessHandler = otherNextGuessHandler;
    }

    @Override
    public void run() {
        // Prepare the looper
        Looper.prepare();

        // Store looper's reference
        myLooper = Looper.myLooper();

        // Create current thread's handlers
        myGuessResultHandler = new GuessResultHandler(this);
        myNextGuessHandler = new NextGuessHandler(this);

        // Prepare an initial guess
        prepareNextGuess(makeInitialGuess());

        // Call the activity's callback
        gameActivity.onSolverThreadStarted(this);

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
