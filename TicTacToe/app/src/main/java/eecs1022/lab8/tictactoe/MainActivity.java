package eecs1022.lab8.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

import eecs1022.lab8.tictactoe.model.Game;

public class MainActivity extends AppCompatActivity {
Game g;
    /* Hint: How do you share the same game object between button clicks
     * (attached with controller methods) of the app?
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* Hint: How do you display the initial status to the output textview
         * when the app is first launched?
         */
        g = new Game("","");
    }

    /* this mutator sets the output label */
    private void setContentsOfTextView(int id, String newContents) {
        View view = findViewById(id);
        TextView textView = (TextView) view;
        textView.setText(newContents);
    }

    /* this accessor retrieves input entered on the text view  */
    private String getInputOfTextField(int id) {
        View view = findViewById(id);
        EditText editText = (EditText) view;
        String input = editText.getText().toString();
        return input;
    }

    /* this accessor retrieves input chosen from some spinner (drop-down menu) */
    private String getItemSelected(int id) {
        View view = findViewById(id);
        Spinner spinner = (Spinner) view;
        String string = spinner.getSelectedItem().toString();
        return string;
    }

    public void computeButton (View view) {
        String player1 = getInputOfTextField(R.id.inputPlayer1);
        String player2 =  getInputOfTextField(R.id.inputPlayer2);
        String spinnerOption = getItemSelected(R.id.selectPlayer);

        g = new Game(player1,player2);
        if (spinnerOption.equals("Player X")) {
            g.setWhoPlaysFirst('x');
        }else {
            g.setWhoPlaysFirst('o');
        }
        for (int i = 0; i < g.getBoard().length; i++) {
            for (int j = 0; j < g.getBoard()[i].length; j++) {
                System.out.print(g.getBoard()[i][j] + " ");
            }
            System.out.println();
        }
        String str = "";
        for (int i = 0; i < g.getBoard().length; i++) {
            for (int j = 0; j < g.getBoard()[i].length; j++) {
                str += (g.getBoard()[i][j] + " ");
            }
            str += "\n";

        }

        setContentsOfTextView(R.id.textVisual, "Current Game Board: " + "\n"  + str + "Current Game Status: " + "\n" + g.getStatus());

    }



    public void computeButton2 (View view){
        String textRowNum = getInputOfTextField(R.id.inputRow);
        String textColNum =  getInputOfTextField(R.id.inputCol);
        int rowNum = Integer.parseInt(textRowNum);
        int colNum = Integer.parseInt(textColNum);
        g.move(rowNum,colNum);
        String str = "";
        for (int i = 0; i < g.getBoard().length; i++) {
            for (int j = 0; j < g.getBoard()[i].length; j++) {
                str += (g.getBoard()[i][j] + " ");
            }
            str += "\n";

        }
        setContentsOfTextView(R.id.textVisual, "Current Game Board: " + "\n"  + str +  "Current Game Status: " + "\n" + g.getStatus());
    }

    
}