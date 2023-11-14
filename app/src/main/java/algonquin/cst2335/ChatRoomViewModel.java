package algonquin.cst2335;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;



    public class ChatRoomViewModel extends ViewModel {
        public    ArrayList<ChatMessage> theMessages = new java.util.ArrayList<>();
        public MutableLiveData<ChatMessage> selectedMessage = new MutableLiveData< >();

    }

