package algonquin.cst2335;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;



    public class ChatRoomViewModel extends ViewModel {
        public MutableLiveData<ArrayList<ChatMessage>> messages = new MutableLiveData<>();
        public ChatRoomViewModel() {
            if (messages.getValue() == null) {
                messages.setValue(new ArrayList<>());
            }
        }

    }
