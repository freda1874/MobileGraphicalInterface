package algonquin.cst2335;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import algonquin.cst2335.databinding.ActivityMainBinding;

/**
 * This is the main activity class responsible for handling the UI related to password complexity.
 *
 * @author Lei Luo
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {


    protected String cityName;

    protected RequestQueue queue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        queue = Volley.newRequestQueue(this);

        ActivityMainBinding binding =
                ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//         Button click listener to check the complexity of the entered password.

        binding.forecastButton.setOnClickListener(clk -> {


                    try {
                        cityName =
                                URLEncoder.encode(binding.cityTextField.getText().toString(),
                                        "UTF-8");


                        String stringURL = "http://api.openweathermap.org/data/2" +
                                ".5/weather?q=" + cityName +
                                "&appid=7e943c97096a9784391a981c4d878b22&units=metric";


                        JsonObjectRequest request = new
                                JsonObjectRequest(Request.Method.GET, stringURL,
                                null, (response) -> {
                            try {
                                JSONObject position0 =
                                        response.getJSONArray("weather").getJSONObject(0);
                                String description = position0.getString("description");
                                String iconName = position0.getString("icon");
                                JSONObject mainObject = response.getJSONObject("main");
                                JSONObject weatherOBJ = response.getJSONArray(
                                        "weather").getJSONObject(0);

                                runOnUiThread( (  )  -> {


                                    try {
                                      Double  current = mainObject.getDouble(
                                              "temp");

                                    binding.temp.setText("The current temperature  is: "+current);
                                binding.temp.setVisibility(View.VISIBLE);
                                double min = mainObject.getDouble("temp_min");
                                binding.minTemp.setText("The min temperature " +
                                        "is: "+ min);
                                binding.minTemp.setVisibility(View.VISIBLE);
                                double max = mainObject.getDouble("temp_max");
                                binding.maxTemp.setText("The max temperature " +
                                        "is: "+max);
                                binding.maxTemp.setVisibility(View.VISIBLE);
                                int humidity = mainObject.getInt("humidity");
                                binding.humidity.setText("The current humidity " +
                                        "is: "+humidity);
                                binding.humidity.setVisibility(View.VISIBLE);

                                String descript = weatherOBJ.getString(
                                        "description");
                                binding.description.setText(descript);
                                binding.description.setVisibility(View.VISIBLE);} catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }});


//second query for the image
                                String imageURL = "http://openweathermap" + ".org/img/w/" + iconName + ".png";
                                ImageRequest imgReq =
                                        new ImageRequest(imageURL,
                                                new Response.Listener<Bitmap>() {
                                                    public void onResponse(Bitmap bitmap) {
                                                        binding.weatherImage.setImageBitmap(bitmap);
                                                        binding.weatherImage.setVisibility(View.VISIBLE);
//                                                        save the icon to the device

                                                        FileOutputStream fOut = null;
                                                        try {
                                                            fOut = openFileOutput( iconName + ".png", Context.MODE_PRIVATE);

                                                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                                                            fOut.flush();
                                                            fOut.close();
                                                        }   catch (
                                                                IOException e) {
                                                            throw new RuntimeException(e);
                                                        }
                                                    }
                                                }, 1024, 1024,
                                                ImageView.ScaleType.FIT_CENTER, null, (error) -> {
                                            Log.e("MainActivity", "Image request error: " + error.getMessage());
                                        });
                                queue.add(imgReq);



                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }, (error) -> {  /*this gets called if the
                             server responded*/
                        });
                        queue.add(request);


                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }

                }
        );
    }


}