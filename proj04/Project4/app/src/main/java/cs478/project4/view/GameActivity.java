package cs478.project4.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cs478.project4.R;
import cs478.project4.model.Board;
import cs478.project4.threading.SolverThread;
import cs478.project4.threading.SolverThreadFirst;
import cs478.project4.threading.SolverThreadSecond;

public class GameActivity extends AppCompatActivity {

    public final static int MODE_GUESS_BY_GUESS = 1;
    public final static int MODE_CONTINUOUS = 2;

    public final static int BOARD_SIZE = 10;

    private int mode;
    private Board board;

    private List<String> moves;
    private ArrayAdapter<String> movesAdapter;

    private Handler uiHandler;
    private SolverThread t1, t2;
    private boolean t1Started, t2Started;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Read mode from intent
        mode = getIntent().getIntExtra("mode", MODE_CONTINUOUS);

        // Create UI thread's handler
        uiHandler = new Handler();

        // Create a new board
        board = new Board(BOARD_SIZE);

        // Create and prepare the view
        createAndPrepareView();

        // Create and start solver threads
        createAndStartThreads();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Interrupt the two threads
        t1.interrupt();
        t2.interrupt();
    }

    private void createAndPrepareView() {
        // Show the gopher location
        TextView gopherLocText = findViewById(R.id.gopherLocText);
        gopherLocText.setText(getString(R.string.gopher_loc_text, board.getGopherX(), board.getGopherY()));

        // Get device width
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

        // Compute view sizes
        int cellSize = width / BOARD_SIZE;
        int cellMarginSize = cellSize / 8;

        // Create board view
        GridLayout boardLayout = findViewById(R.id.boardGrid);
        boardLayout.removeAllViews();
        boardLayout.setColumnCount(BOARD_SIZE);
        boardLayout.setRowCount(BOARD_SIZE);

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                // Create the cell
                ImageView cell = new ImageView(this);

                // Set the image, the size and the padding
                cell.setImageResource(R.drawable.ic_undef);
                cell.setLayoutParams(new ViewGroup.LayoutParams(cellSize, cellSize));
                cell.setPadding(cellMarginSize, cellMarginSize, cellMarginSize, cellMarginSize);

                // Set the background of the gopher's cell
                if (i == board.getGopherX() && j == board.getGopherY())
                    cell.setBackgroundColor(Color.GRAY);

                // Add the cell to the layout
                boardLayout.addView(cell);
            }
        }

        // Create moves list
        moves = new ArrayList<>();
        movesAdapter = new ArrayAdapter<>(this, R.layout.move_list_item, moves);

        // Adapt moves list to list view
        ListView movesList = findViewById(R.id.movesList);
        movesList.setAdapter(movesAdapter);
    }

    private void createAndStartThreads() {
        // Create solver threads
        t1 = new SolverThreadFirst(this, 0);
        t2 = new SolverThreadSecond(this, 1);

        // Start solver threads
        t1.start();
        t2.start();
    }

    public void onRestartButtonClick(View view) {
        // Reset started flags
        t1Started = false;
        t2Started = false;

        // Interrupt the two threads
        t1.interrupt();
        t2.interrupt();

        // Clear UI thread handler's queue
        uiHandler.removeCallbacksAndMessages(null);

        // Create a new board
        board = new Board(BOARD_SIZE);

        // Recreate view
        createAndPrepareView();

        // Recreate and start solver threads
        createAndStartThreads();
    }

    public void onSolverThreadStarted(SolverThread solverThread) {
        // Set other thread's handler in the two threads
        if (solverThread == t1)
            t1Started = true;
        else if (solverThread == t2)
            t2Started = true;

        if (t1Started && t2Started) {
            // Insert the first message in the first thread's queue to start looping
            t1.getMyNextGuessHandler().obtainMessage().sendToTarget();

            // If in continuous mode, unlock also the second thread
            if (mode == MODE_CONTINUOUS)
                t2.getMyNextGuessHandler().obtainMessage().sendToTarget();
        }
    }

    public int getMode() {
        return mode;
    }

    public Board getBoard() {
        return board;
    }

    public List<String> getMoves() {
        return moves;
    }

    public ArrayAdapter<String> getMovesAdapter() {
        return movesAdapter;
    }

    public SolverThread getT1() {
        return t1;
    }

    public SolverThread getT2() {
        return t2;
    }

    public Handler getUiHandler() {
        return uiHandler;
    }

}
