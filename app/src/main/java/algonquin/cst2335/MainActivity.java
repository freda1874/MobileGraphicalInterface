package algonquin.cst2335;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This is the main activity class responsible for handling the UI related to password complexity.
 *  @author Lei Luo
 *  @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     * TextView that displays messages at the center of the screen.
     */
    private TextView tv;
    /**
     * EditText field for user to input their password.
     */
    private EditText et;
    /**
     * Button to initiate the password complexity check.
     */
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv =findViewById(R.id.textView);
         et = findViewById(R.id.editText);
          btn=findViewById(R.id.button);

//         Button click listener to check the complexity of the entered password.


    }

}