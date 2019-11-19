package cs478.project4.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

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
    private Handler uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Read mode from intent
        mode = getIntent().getIntExtra("mode", MODE_CONTINUOUS);

        // Create board view
        GridLayout boardLayout = findViewById(R.id.boardGrid);
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                ImageView cell = new ImageView(this);
                cell.setImageResource(R.drawable.ic_undef);
                cell.setPadding(10, 10, 10, 10);
                boardLayout.addView(cell);
            }
        }

        // Create playing field
        board = new Board(BOARD_SIZE);

        // Show the gopher location
        TextView gopherLocText = findViewById(R.id.gopherLocText);
        gopherLocText.setText(getString(R.string.gopher_loc_text, board.getGopherX(), board.getGopherY()));

        // Create handler
        uiHandler = new Handler();

        // Create solver threads
        SolverThread t1 = new SolverThreadFirst(this, 0);
        SolverThread t2 = new SolverThreadSecond(this, 1);

        // Start solver threads
        t1.start();
        t2.start();
    }

    public int getMode() {
        return mode;
    }

    public Board getBoard() {
        return board;
    }

    public Handler getUiHandler() {
        return uiHandler;
    }

}
