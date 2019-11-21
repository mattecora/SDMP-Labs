package cs478.project4.model;

import java.util.Random;

public class Board {

    public final static int ALREADY_SOLVED = -2;
    public final static int INVALID = -1;
    public final static int SUCCESS = 0;
    public final static int NEAR_MISS = 1;
    public final static int CLOSE_GUESS = 2;
    public final static int COMPLETE_MISS = 3;
    public final static int DISASTER = 4;

    private int fieldSize;
    private boolean[][] guesses;
    private boolean solved;
    private int gopherX, gopherY;

    public Board(int fieldSize) {
        this.fieldSize = fieldSize;
        this.guesses = new boolean[fieldSize][fieldSize];
        this.solved = false;

        Random gen = new Random();
        this.gopherX = gen.nextInt(fieldSize);
        this.gopherY = gen.nextInt(fieldSize);
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public int getGopherX() {
        return gopherX;
    }

    public int getGopherY() {
        return gopherY;
    }

    public synchronized int guess(int x, int y) {
        int retVal = COMPLETE_MISS;

        // Check if already solved
        if (solved)
            retVal = ALREADY_SOLVED;

        // Check invalid
        if (x < 0 || x >= fieldSize || y < 0 || y >= fieldSize)
            retVal = INVALID;

        // Check disaster
        else if (guesses[x][y])
            retVal = DISASTER;

        // Check success
        else if (x == gopherX && y == gopherY) {
            retVal = SUCCESS;
            solved = true;
        }

        // Check near miss
        else if (x + 1 == gopherX || x - 1 == gopherX || y + 1 == gopherY || y - 1 == gopherY)
            retVal = NEAR_MISS;

        // Check close guess
        else if (x + 2 == gopherX || x - 2 == gopherX || y + 2 == gopherY || y - 2 == gopherY)
            retVal = CLOSE_GUESS;

        // Mark position as guessed
        guesses[x][y] = true;

        return retVal;
    }

    public static String getResultName(int result) {
        String name = null;

        switch (result) {
            case ALREADY_SOLVED:
                name = "already solved";
                break;
            case INVALID:
                name = "invalid";
                break;
            case SUCCESS:
                name = "success";
                break;
            case NEAR_MISS:
                name = "near miss";
                break;
            case CLOSE_GUESS:
                name = "close guess";
                break;
            case COMPLETE_MISS:
                name = "complete miss";
                break;
            case DISASTER:
                name = "disaster";
                break;
        }

        return name;
    }

}
