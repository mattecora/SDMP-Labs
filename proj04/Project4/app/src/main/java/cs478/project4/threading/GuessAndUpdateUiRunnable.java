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

        // Create and send result message
        Message msg = solverThread.getMyGuessResultHandler().obtainMessage(result, guess);
        msg.sendToTarget();

        // Add the move to the moves list
        gameActivity.getMoves().add(gameActivity.getString(R.string.guess_msg,
                solverThread.getThreadNo(), guess[0], guess[1], Board.getResultName(result)));
        gameActivity.getMovesAdapter().notifyDataSetChanged();

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

        // Unlock the other thread in guess-by-guess mode
        if (gameActivity.getMode() == GameActivity.MODE_GUESS_BY_GUESS) {
            if (solverThread.getThreadNo() == 0)
                gameActivity.getT2().getMyNextGuessHandler().obtainMessage().sendToTarget();
            else if (solverThread.getThreadNo() == 1)
                gameActivity.getT1().getMyNextGuessHandler().obtainMessage().sendToTarget();
        }

        // In case of winning, end the game
        if (result == Board.SUCCESS) {
            // Show a toast message
            Toast.makeText(gameActivity,
                    gameActivity.getString(R.string.thread_won_msg, solverThread.getThreadNo()),
                    Toast.LENGTH_SHORT).show();

            // Interrupt the other thread
            if (solverThread.getThreadNo() == 0)
                gameActivity.getT2().interrupt();
            else if (solverThread.getThreadNo() == 1)
                gameActivity.getT1().interrupt();
        }
    }

}
