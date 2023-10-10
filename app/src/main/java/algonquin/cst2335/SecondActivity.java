package algonquin.cst2335;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        TextView  textView3= findViewById(R.id.textView3);
        ImageView profileImage=findViewById(R.id.profileImage);
        Intent fromPrevious = getIntent();
        String  emailAddress= fromPrevious.getStringExtra("EmailAddress");
        EditText phoneNumber=findViewById(R.id.phoneNumber);

        String savedPhoneNumber = prefs.getString("PhoneNumber", "");
        phoneNumber.setText(savedPhoneNumber);






        textView3.setText("Welcome back " + emailAddress);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

/*    get data back from the next activity
 The first parameter to pass  says that you are registering code for when the next activity finishes and returns a result.
        The second parameter to pass in is a callback function to receive data from the next activity:
              */
        ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {

                    public void onActivityResult(ActivityResult result) {

                        /*the result of another activity can either RESULT_OK, or RESULT_CANCELED. If the result is RESULT_OK, then the camera activity sends back a Bitmap  in the Intent data object*/
                        Intent data = result.getData();
                        Bitmap thumbnail =data.getParcelableExtra("data");


                        if (result.getResultCode() == Activity.RESULT_OK) {


                            profileImage.setImageBitmap(thumbnail);
                        }

//                        save a bitmap object to the disk using the code
                        FileOutputStream fOut = null;

                        try { fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);


                            thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);

                            fOut.flush();

                            fOut.close();

                        }
                        catch (IOException e) { e.printStackTrace();

                        }
                    }

                } );


        Button callButton = findViewById(R.id.callButton);


        callButton.setOnClickListener(clk -> {
            Intent call = new Intent(Intent.ACTION_DIAL);

            call.setData(Uri.parse("tel:" + phoneNumber.getText().toString()));
              startActivity(call);
        });


        Button changePicture = findViewById(R.id.changePicture);


        changePicture.setOnClickListener(clk -> {
            cameraResult.launch(cameraIntent);

        });

//        test if a file exists (if you already have a bitmap saved) with the code:

        File file = new File( getFilesDir(), "Picture.png");

//        If the file exists, then y ou can read it within the if() statement:
        if(file.exists())

        {
            Bitmap theImage = BitmapFactory.decodeFile(file.getAbsolutePath());

//            You can then set the src of y our ImageView to the bitmap that you loaded:
            profileImage.setImageBitmap(   theImage  );
        }

    }

    /* save the value of the phone number that is currently entered in the EditText on that page,
    using the same SharedPreferences file that you used in MainActivity.java. */
    protected void onPause() {
        super.onPause();

        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        /*The method edit() on a SharedPreferences object (prefs in this case) returns an instance of SharedPreferences.Editor. This editor provides methods to add, remove, and modify the key-value pairs inside the SharedPreferences.*/
        SharedPreferences.Editor editor = prefs.edit();

        EditText phoneNumber = findViewById(R.id.phoneNumber);
             editor.putString("PhoneNumber", phoneNumber.getText().toString());
            //        This method writes the data asynchronously
            editor.apply();


    }
}