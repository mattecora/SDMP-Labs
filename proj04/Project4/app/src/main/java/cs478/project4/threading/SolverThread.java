package cs478.project4.threading;

import android.os.Handler;
import android.os.Looper;

import java.util.Random;

import cs478.project4.view.GameActivity;

public abstract class SolverThread extends Thread {

    private final static int MIN_SLEEP = 500;
    private final static int MAX_RAND_SLEEP = 1500;

    private int threadNo;
    private GameActivity gameActivity;

    private Random rand = new Random();

    private Looper myLooper;
    private Handler myGuessResultHandler, myNextGuessHandler;

    private int[] nextGuess;

    public SolverThread(int threadNo, GameActivity gameActivity) {
        this.gameActivity = gameActivity;
        this.threadNo = threadNo;
    }

    public int getThreadNo() {
        return threadNo;
    }

    public GameActivity getGameActivity() {
        return gameActivity;
    }

    public Random getRand() {
        return rand;
    }

    public Handler getMyGuessResultHandler() {
        return myGuessResultHandler;
    }

    public Handler getMyNextGuessHandler() {
        return myNextGuessHandler;
    }

    @Override
    public void interrupt() {
        super.interrupt();

        // Quit the thread's looper (if any)
        if (myLooper != null)
            myLooper.quit();
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
            try {
                Thread.sleep(MIN_SLEEP + rand.nextInt(MAX_RAND_SLEEP));
            } catch (InterruptedException e) {
                return;
            }

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
