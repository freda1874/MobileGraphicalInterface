package algonquin.cst2335;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";



    @Override
    protected void onResume() {
        super.onResume();
        Log.w( TAG, "In onResume() - The application is now responding to user input" );

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w( TAG, "In onPause()- The application no longer responds to user input" );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w( TAG, "In onDestroy() - Any memory used by the application is freed." );

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w( TAG, "In onStop() - The application is no longer visible." );

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.w( TAG, "In onStart() - The application is now visible on screen." );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w( TAG, "In onCreate() - Loading Widgets" );
/*     The parameter "MyData" is the name of the file that will be opened for saving, and the Context.
MODE_PRIVATE means that only the application that created the file can open it.
      */
        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        String emailAddress = prefs.getString("LoginName", "");
       EditText emailEditText= findViewById(R.id.emailEditText);
        emailEditText.setText(emailAddress);
        /*The method edit() on a SharedPreferences object (prefs in this case) returns an instance of SharedPreferences.Editor. This editor provides methods to add, remove, and modify the key-value pairs inside the SharedPreferences.*/
        SharedPreferences.Editor editor = prefs.edit();


        Intent nextPage = new Intent( MainActivity.this, SecondActivity.class);

        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(clk -> {
            editor.putString("LoginName", emailEditText.getText().toString());
            //        This method writes the data asynchronously
            editor.apply();
            nextPage.putExtra( "EmailAddress", emailEditText.getText().toString());
            startActivity(nextPage);
        });



    }
}