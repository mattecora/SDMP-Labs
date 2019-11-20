package cs478.project4.threading;

import android.os.Message;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import cs478.project4.R;
import cs478.project4.model.Board;
import cs478.project4.view.GameActivity;

public class GuessAndUpdateUiRunnable implements Runnable {

    private int[] guess;
    private GameActivity gameActivity;
    private SolverThread solverThread;

    public GuessAndUpdateUiRunnable(int[] guess, GameActivity gameActivity, SolverThread solverThread) {
        this.guess = guess;
        this.gameActivity = gameActivity;
        this.solverThread = solverThread;
    }

    @Override
    public void run() {
        // Check guess result
        int result = gameActivity.getBoard().guess(guess[0], guess[1]);

        // Create message
        Message msg = new Message();
        msg.what = result;
        msg.obj = guess;

        // Inform the thread of the result
        solverThread.getMyGuessResultHandler().sendMessage(msg);

        // Do nothing if invalid, disaster or already solved
        if (result == Board.INVALID || result == Board.DISASTER || result == Board.ALREADY_SOLVED)
            return;

        // Retrieve corresponding view
        ImageView cell = (ImageView) ((GridLayout) gameActivity.findViewById(R.id.boardGrid))
                .getChildAt(guess[0] * gameActivity.getBoard().getFieldSize() + guess[1]);

        // Set the correct drawable
        if (solverThread.getThreadNo() == 0)
            cell.setImageResource(R.drawable.ic_circle);
        else if (solverThread.getThreadNo() == 1)
            cell.setImageResource(R.drawable.ic_cross);

        // Show a toast message in case of winning
        if (result == Board.SUCCESS)
            Toast.makeText(gameActivity,
                    gameActivity.getString(R.string.thread_won_msg, solverThread.getThreadNo()),
                    Toast.LENGTH_SHORT).show();
    }

}
