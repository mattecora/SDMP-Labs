package cs478.project4.threading;

import java.util.Random;

import cs478.project4.view.GameActivity;

public class SolverThreadSecond extends SolverThread {

    /*
     * Second solver thread heuristic:
     * - Always perform a random guess, without caring about the previous results
     */

    private Random rand = new Random();

    public SolverThreadSecond(GameActivity gameActivity, int threadNo) {
        super(gameActivity, threadNo);
    }

    @Override
    public int[] makeInitialGuess() {
        return new int[]{rand.nextInt(GameActivity.BOARD_SIZE), rand.nextInt(GameActivity.BOARD_SIZE)};
    }

    @Override
    public int[] makeNearMissGuess(int[] oldGuess) {
        return new int[]{rand.nextInt(GameActivity.BOARD_SIZE), rand.nextInt(GameActivity.BOARD_SIZE)};
    }

    @Override
    public int[] makeCloseGuessGuess(int[] oldGuess) {
        return new int[]{rand.nextInt(GameActivity.BOARD_SIZE), rand.nextInt(GameActivity.BOARD_SIZE)};
    }

    @Override
    public int[] makeCompleteMissGuess(int[] oldGuess) {
        return new int[]{rand.nextInt(GameActivity.BOARD_SIZE), rand.nextInt(GameActivity.BOARD_SIZE)};
    }

    @Override
    public int[] makeDisasterGuess(int[] oldGuess) {
        return new int[]{rand.nextInt(GameActivity.BOARD_SIZE), rand.nextInt(GameActivity.BOARD_SIZE)};
    }

}
