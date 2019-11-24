package cs478.project4.threading;

import java.util.ArrayList;
import java.util.List;

import cs478.project4.view.GameActivity;

public class SolverThreadFirst extends SolverThread {

    /*
     * First solver thread heuristic:
     * - Maintain a list of possible states, updating it with the result of each guess
     * - Among all possible states, randomly choose one when guessing
     */

    private List<int[]> possibleStates = new ArrayList<>(GameActivity.BOARD_SIZE * GameActivity.BOARD_SIZE);

    public SolverThreadFirst(GameActivity gameActivity, int threadNo) {
        super(threadNo, gameActivity);

        // Populate the list of possible states
        for (int i = 0; i < GameActivity.BOARD_SIZE; i++)
            for (int j = 0; j < GameActivity.BOARD_SIZE; j++)
                possibleStates.add(new int[]{i, j});
    }

    @Override
    public int[] makeInitialGuess() {
        // Return a random element from the possible states
        return possibleStates.get(getRand().nextInt(possibleStates.size()));
    }

    @Override
    public int[] makeNearMissGuess(int[] oldGuess) {
        // Remove all elements not within the first-level frame
        possibleStates.removeIf(state -> (state[0] < oldGuess[0] - 1 || state[0] > oldGuess[0] + 1)
            || (state[1] < oldGuess[1] - 1 || state[1] > oldGuess[1] + 1));

        // Remove old guess from possible states
        possibleStates.remove(oldGuess);

        return possibleStates.get(getRand().nextInt(possibleStates.size()));
    }

    @Override
    public int[] makeCloseGuessGuess(int[] oldGuess) {
        // Remove all elements not within the second-level frame
        possibleStates.removeIf(state -> (state[0] < oldGuess[0] - 2 || state[0] > oldGuess[0] + 2)
                || (state[1] < oldGuess[1] - 2 || state[1] > oldGuess[1] + 2));

        // Remove all elements within the first-level frame
        possibleStates.removeIf(state -> (state[0] >= oldGuess[0] - 1 && state[0] <= oldGuess[0] + 1)
                && (state[1] >= oldGuess[1] - 1 && state[1] <= oldGuess[1] + 1));

        // Remove old guess from possible states
        possibleStates.remove(oldGuess);

        return possibleStates.get(getRand().nextInt(possibleStates.size()));
    }

    @Override
    public int[] makeCompleteMissGuess(int[] oldGuess) {
        // Remove guess from possible states
        possibleStates.remove(oldGuess);

        // Return a random element from the possible states
        return possibleStates.get(getRand().nextInt(possibleStates.size()));
    }

    @Override
    public int[] makeDisasterGuess(int[] oldGuess) {
        // Remove guess from possible states
        possibleStates.remove(oldGuess);

        // Return a random element from the possible states
        return possibleStates.get(getRand().nextInt(possibleStates.size()));
    }

}
