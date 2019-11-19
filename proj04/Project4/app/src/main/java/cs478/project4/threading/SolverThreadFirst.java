package cs478.project4.threading;

import java.util.Random;

import cs478.project4.view.GameActivity;

public class SolverThreadFirst extends SolverThread {

    public SolverThreadFirst(GameActivity gameActivity, int threadNo) {
        super(gameActivity, threadNo);
    }

    @Override
    public int[] makeInitialGuess() {
        Random rand = new Random();
        return new int[]{rand.nextInt(GameActivity.BOARD_SIZE), rand.nextInt(GameActivity.BOARD_SIZE)};
    }

    @Override
    public int[] makeNearMissGuess(int[] oldGuess) {
        Random rand = new Random();
        return new int[]{rand.nextInt(GameActivity.BOARD_SIZE), rand.nextInt(GameActivity.BOARD_SIZE)};
    }

    @Override
    public int[] makeCloseGuessGuess(int[] oldGuess) {
        Random rand = new Random();
        return new int[]{rand.nextInt(GameActivity.BOARD_SIZE), rand.nextInt(GameActivity.BOARD_SIZE)};
    }

    @Override
    public int[] makeCompleteMissGuess(int[] oldGuess) {
        Random rand = new Random();
        return new int[]{rand.nextInt(GameActivity.BOARD_SIZE), rand.nextInt(GameActivity.BOARD_SIZE)};
    }

    @Override
    public int[] makeDisasterGuess(int[] oldGuess) {
        Random rand = new Random();
        return new int[]{rand.nextInt(GameActivity.BOARD_SIZE), rand.nextInt(GameActivity.BOARD_SIZE)};
    }

}
