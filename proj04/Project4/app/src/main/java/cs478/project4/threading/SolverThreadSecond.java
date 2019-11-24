package cs478.project4.threading;

import cs478.project4.view.GameActivity;

public class SolverThreadSecond extends SolverThread {

    /*
     * Second solver thread heuristic:
     * - Always perform a random guess, without caring about the previous results
     */

    public SolverThreadSecond(GameActivity gameActivity, int threadNo) {
        super(threadNo, gameActivity);
    }

    @Override
    public int[] makeInitialGuess() {
        return new int[]{getRand().nextInt(GameActivity.BOARD_SIZE), getRand().nextInt(GameActivity.BOARD_SIZE)};
    }

    @Override
    public int[] makeNearMissGuess(int[] oldGuess) {
        return new int[]{getRand().nextInt(GameActivity.BOARD_SIZE), getRand().nextInt(GameActivity.BOARD_SIZE)};
    }

    @Override
    public int[] makeCloseGuessGuess(int[] oldGuess) {
        return new int[]{getRand().nextInt(GameActivity.BOARD_SIZE), getRand().nextInt(GameActivity.BOARD_SIZE)};
    }

    @Override
    public int[] makeCompleteMissGuess(int[] oldGuess) {
        return new int[]{getRand().nextInt(GameActivity.BOARD_SIZE), getRand().nextInt(GameActivity.BOARD_SIZE)};
    }

    @Override
    public int[] makeDisasterGuess(int[] oldGuess) {
        return new int[]{getRand().nextInt(GameActivity.BOARD_SIZE), getRand().nextInt(GameActivity.BOARD_SIZE)};
    }

}
