package cs478.project4.threading;

import android.os.Handler;
import android.os.Message;

import cs478.project4.model.Board;
import cs478.project4.view.GameActivity;

public class GuessResultHandler extends Handler {

    private SolverThread solverThread;

    public GuessResultHandler(SolverThread solverThread) {
        this.solverThread = solverThread;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        // Check guess result
        switch (msg.what) {
            case Board.ALREADY_SOLVED:
            case Board.SUCCESS:
                // Game is solved, terminate the thread
                solverThread.interrupt();
                break;

            case Board.NEAR_MISS:
                // Near miss, proceed with the next guess
                solverThread.prepareNextGuess(solverThread.makeNearMissGuess((int[]) msg.obj));
                break;

            case Board.CLOSE_GUESS:
                // Close guess, proceed with the next guess
                solverThread.prepareNextGuess(solverThread.makeCloseGuessGuess((int[]) msg.obj));
                break;

            case Board.COMPLETE_MISS:
                // Complete miss, proceed with the next guess
                solverThread.prepareNextGuess(solverThread.makeCompleteMissGuess((int[]) msg.obj));
                break;

            case Board.DISASTER:
                // Disaster, proceed with the next guess
                solverThread.prepareNextGuess(solverThread.makeDisasterGuess((int[]) msg.obj));
                break;

            case Board.INVALID:
                // Invalid guess, go for a random guess
                solverThread.prepareNextGuess(solverThread.makeInitialGuess());
                break;
        }

        // Proceed to send next guess if mode is continuous
        if (solverThread.getGameActivity().getMode() == GameActivity.MODE_CONTINUOUS &&
                msg.what != Board.ALREADY_SOLVED && msg.what != Board.SUCCESS)
            solverThread.sendNextGuess();
    }

}
