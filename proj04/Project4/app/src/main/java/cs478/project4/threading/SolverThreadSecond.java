package cs478.project4.threading;

import java.util.ArrayList;
import java.util.List;

import cs478.project4.view.GameActivity;

public class SolverThreadSecond extends SolverThread {

    /*
     * Second solver thread heuristic:
     * - Perform random guesses on quadrants of the board
     * - If a near miss or close guess is found, populate the possible states and loop through them
     */

    private List<int[]> possibleStates = new ArrayList<>(GameActivity.BOARD_SIZE * GameActivity.BOARD_SIZE);
    private int quadrant = 0;
    private boolean random = true;

    public SolverThreadSecond(GameActivity gameActivity, int threadNo) {
        super(threadNo, gameActivity);
    }

    @Override
    public int[] makeInitialGuess() {
        // Return a random element in the considered quadrant
        return makeRandomGuessOnQuadrant();
    }

    @Override
    public int[] makeNearMissGuess(int[] oldGuess) {
        // If possible states is still empty, populate it with elements from first-level frame
        if (possibleStates.size() == 0) {
            random = false;

            for (int i = oldGuess[0] - 1; i <= oldGuess[0] + 1; i++)
                for (int j = oldGuess[1] - 1; j <= oldGuess[1] + 1; j++)
                    possibleStates.add(new int[]{i, j});
        }

        // Remove old guess from possible states
        possibleStates.remove(oldGuess);

        return possibleStates.get(0);
    }

    @Override
    public int[] makeCloseGuessGuess(int[] oldGuess) {
        // If possible states is still empty, populate it with elements from second-level frame
        if (possibleStates.size() == 0) {
            random = false;

            for (int i = oldGuess[0] - 2; i <= oldGuess[0] + 2; i++)
                for (int j = oldGuess[1] - 2; j <= oldGuess[1] + 2; j++)
                    possibleStates.add(new int[]{i, j});
        }

        // Remove old guess from possible states
        possibleStates.remove(oldGuess);

        return possibleStates.get(0);
    }

    @Override
    public int[] makeCompleteMissGuess(int[] oldGuess) {
        // Remove guess from possible states
        possibleStates.remove(oldGuess);

        // Return a random element from the possible states
        if (random)
            return makeRandomGuessOnQuadrant();
        else
            return possibleStates.get(0);
    }

    @Override
    public int[] makeDisasterGuess(int[] oldGuess) {
        // Remove guess from possible states
        possibleStates.remove(oldGuess);

        // Return a random element from the possible states
        if (random)
            return makeRandomGuessOnQuadrant();
        else
            return possibleStates.get(0);
    }

    private int[] makeRandomGuessOnQuadrant() {
        int x, y;

        if (quadrant < 2)
            x = getRand().nextInt(GameActivity.BOARD_SIZE / 2);
        else
            x = GameActivity.BOARD_SIZE / 2 + getRand().nextInt(GameActivity.BOARD_SIZE / 2);

        if (quadrant % 2 == 0)
            y = getRand().nextInt(GameActivity.BOARD_SIZE / 2);
        else
            y = GameActivity.BOARD_SIZE / 2 + getRand().nextInt(GameActivity.BOARD_SIZE / 2);

        quadrant = (quadrant + 1) % 4;
        return new int[]{x, y};
    }

}
