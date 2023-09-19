package algonquin.cst2335;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

//        TextView textview = findViewById(R.id.textview);
//        TextView textview =variableBinding.textview;

        // Observe changes in the MutableLiveData
        model.editString.observe(this, s ->{


        });

//variableBinding.textview.setText(model.editString);
variableBinding.mybutton.setOnClickListener(click -> {
            model.editString.postValue(variableBinding.myedittext.getText().toString());

        });
}}