package algonquin.cst2335;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.RadioButton;
import android.widget.Toast;

import algonquin.cst2335.data.MainViewModel;
import algonquin.cst2335.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        MainViewModel model = new ViewModelProvider(this).get(MainViewModel.class);
        model.editString.observe(this, s -> {
            variableBinding.myedittext.setText(s);
        });

        variableBinding.mybutton.setOnClickListener(click -> {
            model.editString.postValue(variableBinding.myedittext.getText().toString());
            variableBinding.textview.setText("Your edit text has: " + variableBinding.myedittext.getText().toString());
        });



        // Observe changes in the drinksCoffee MutableLiveData
        model.drinksCoffee.observe(this, selected -> {
            variableBinding.checkBox.setChecked(selected);
            variableBinding.switch1.setChecked(selected);
            variableBinding.radioButton.setChecked(selected);

            Toast.makeText(this, "The value is now: " + selected, Toast.LENGTH_SHORT).show();
        });

        // Set up OnClickListener for the EditText and TextView
        variableBinding.myimagebutton.setOnClickListener(click -> {

            int width = variableBinding.myimagebutton.getWidth();
            int height = variableBinding.myimagebutton.getHeight();

            Toast.makeText(this, "The width = " + width + " and height = " + height, Toast.LENGTH_SHORT).show();


            model.editString.postValue(variableBinding.myedittext.getText().toString());
        });
// Accessing ImageView using ViewBinding
        variableBinding.myImageView.setImageResource(R.drawable.algonquin);



        // Registering the CompoundButton listeners
        variableBinding.checkBox.setOnCheckedChangeListener((btn, selected) -> model.drinksCoffee.postValue(selected));
        variableBinding.switch1.setOnCheckedChangeListener((btn, selected) -> model.drinksCoffee.postValue(selected));
        variableBinding.radioButton.setOnCheckedChangeListener((btn, selected) -> model.drinksCoffee.postValue(selected));

    }
}
