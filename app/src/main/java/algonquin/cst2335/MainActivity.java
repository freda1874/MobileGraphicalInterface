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

        btn.setOnClickListener(clk ->{
            String password =et.getText().toString();

       if( checkPasswordComplexity(password)){
           tv.setText("Your password meets the requirements");
       }else   tv.setText("You shall not pass!") ;
    }
        );
    }

    /**
     * Checks if the given character is a special character.
     *
     * @param c The character to check.
     * @return true if it is a special character, false otherwise.
     */
    boolean isSpecialCharacter(char c){
        switch (c){
            case '#':
            case '?':
            case '*':
            case '%':
            case '^':
            case '&':
            case '$':
            case '!':
            case '@':
                return true;
            default:
                return false;

        }
    }
    /**
     * Checks the complexity of the given password string.
     *
     * @param pw The password string to check.
     * @return Returns true if the password meets all complexity requirements, false otherwise.
     */
    boolean checkPasswordComplexity(String pw){
        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;

        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;


        for(int i=0;i<  pw.length(); i++){
            char currentchar=pw.charAt(i);
            if(Character.isUpperCase(currentchar)){
                foundUpperCase=true; 
            } else if (Character.isLowerCase(currentchar)) {
                foundLowerCase=true;
            }else if(Character.isDigit(currentchar)){
                foundNumber=true;
            } else if (isSpecialCharacter(currentchar)) {
                foundSpecial=true;
                
            }
        }


        if(!foundUpperCase)
        {
            Toast.makeText(this, "Missing an uppercase letter", Toast.LENGTH_SHORT).show();

            return false;

        }

        else if( !foundLowerCase)
        {

            Toast.makeText(this, "Missing an lower case letter", Toast.LENGTH_SHORT).show();


            return false;

        }

        else if( !foundNumber)
        {

            Toast.makeText(this, "Missing a number ", Toast.LENGTH_SHORT).show();


            return false;

        }

        else if(!foundSpecial) {   Toast.makeText(this, "Missing a special character ",
                Toast.LENGTH_SHORT).show();


            return false; }

        else

            return true; //only get here if they're all true

}
}