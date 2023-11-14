package algonquin.cst2335.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import algonquin.cst2335.ChatMessage;
import algonquin.cst2335.databinding.DetailsLayoutBinding;

public class MessageDetailsFragment extends Fragment {

    ChatMessage myMessage;

    public MessageDetailsFragment(ChatMessage toDisplay) {
        myMessage = toDisplay;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        DetailsLayoutBinding binding =
                DetailsLayoutBinding.inflate(getLayoutInflater());

        binding.messageText.setText(myMessage.message);
        binding.timeView.setText(myMessage.timeSent);
        binding.sendReceiveView.setText(myMessage.sentOrReceive ? "True" : "False");
        binding.idView.setText("Id is:" + myMessage.id);
        return binding.getRoot();
    }
}
