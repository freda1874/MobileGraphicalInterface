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

        // Observe changes in the MutableLiveData for the EditText and TextView
        model.editString.observe(this, s -> {
            // Do something with the string here, if needed
        });

        // Observe changes in the drinksCoffee MutableLiveData
        model.drinksCoffee.observe(this, selected -> {
            variableBinding.checkBox.setChecked(selected);
            variableBinding.switch1.setChecked(selected);
            variableBinding.radioButton.setChecked(selected);

            Toast.makeText(this, "The value is now: " + selected, Toast.LENGTH_SHORT).show();
        });

        // Set up OnClickListener for the EditText and TextView
        variableBinding.mybutton.setOnClickListener(click -> {
            model.editString.postValue(variableBinding.myedittext.getText().toString());
        });

        // Registering the CompoundButton listeners
        variableBinding.checkBox.setOnCheckedChangeListener((btn, selected) -> model.drinksCoffee.postValue(selected));
        variableBinding.switch1.setOnCheckedChangeListener((btn, selected) -> model.drinksCoffee.postValue(selected));
        variableBinding.radioButton.setOnCheckedChangeListener((btn, selected) -> model.drinksCoffee.postValue(selected));
    }
}
