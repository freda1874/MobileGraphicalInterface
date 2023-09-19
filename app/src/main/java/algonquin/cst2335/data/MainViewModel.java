package algonquin.cst2335.data;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import algonquin.cst2335.databinding.ActivityMainBinding;

public class MainViewModel extends ViewModel {
    public MutableLiveData<String> editString = new MutableLiveData<>();
    public MutableLiveData<Boolean> drinksCoffee = new MutableLiveData<>();

}
