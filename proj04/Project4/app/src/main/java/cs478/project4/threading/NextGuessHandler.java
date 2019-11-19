package cs478.project4.threading;

import android.os.Handler;
import android.os.Message;

public class NextGuessHandler extends Handler {

    private SolverThread solverThread;

    public NextGuessHandler(SolverThread solverThread) {
        this.solverThread = solverThread;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        // Make the next guess
        solverThread.sendNextGuess();
    }

}
