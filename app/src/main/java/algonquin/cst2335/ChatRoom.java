package algonquin.cst2335;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import algonquin.cst2335.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.databinding.ReceiveMessageBinding;
import algonquin.cst2335.databinding.SentMessageBinding;

public class ChatRoom extends AppCompatActivity {
    ActivityChatRoomBinding binding;
    ArrayList<ChatMessage> messages = new ArrayList<>();
    ChatRoomViewModel chatModel;
    private RecyclerView.Adapter myAdapter;

    /**
     * This method is called when the activity is first created.
     * It initializes the UI components and sets up the click listeners.
     *
     * @param savedInstanceState Contains the data it most recently supplied in onSaveInstanceState(Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);

        // Get messages from ViewModel
        messages = chatModel.messages.getValue();
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

        if (messages == null) {
            messages = new ArrayList<>();
            chatModel.messages.postValue(messages);
        }


//        E means day of the week, and having 4 EEEE means write the whole word of day of the week
        binding.sendButton.setOnClickListener(click -> {
            ArrayList<ChatMessage> currentMessages = chatModel.messages.getValue();
            if (currentMessages != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
                String currentDateandTime = sdf.format(new Date());

                // Create a ChatMessage object with isSentButton as false for the Receive button
                ChatMessage chatMessage =
                        new ChatMessage(binding.textInput.getText().toString(), currentDateandTime, true);

                currentMessages.add(chatMessage);
                chatModel.messages.setValue(currentMessages);

                //clear the previous text:
                binding.textInput.setText("");
            }
        });

        binding.receiveButton.setOnClickListener(click -> {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            ChatMessage newMessage =
                    new ChatMessage(binding.textInput.getText().toString(),
                            currentDateandTime, false);

            messages.add(newMessage);
            chatModel.messages.setValue(messages);


            //clear the previous text:
            binding.textInput.setText("");
        });

        binding.recycleView.setAdapter(myAdapter =
                new RecyclerView.Adapter<MyRowHolder>() {
                    @NonNull
                    @Override

                    //        onCreateViewHolder function is responsible for creating a layout
//        for a row, and setting the TextViews in code
                    public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent,

                                                          int viewType) {

                        if (viewType == 0) {
                            SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
                            return new MyRowHolder(binding.getRoot(), viewType);
                        } else {
                            ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(getLayoutInflater());  // You need to create this layout
                            return new MyRowHolder(binding.getRoot(), viewType);
                        }


                    }

                    @Override


                    //        The function onBindViewHolder is where you set the objects in your layout for the row. Right now, your MyRowHolder class has two TextView objects. The onBindViewHolder() function is meant to set the data for your ViewHolder object that will go at row position in the list
                    public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {

                        ChatMessage chatMessage = messages.get(position);
                        holder.messageText.setText(chatMessage.getMessage());
                        holder.timeText.setText(chatMessage.getTimeSent());
                    }

                    @Override
                    public int getItemCount() {
                        return messages.size();
                    }

                    /**
                     * Returns the view type for a given position in the chat messages list.
                     *
                     * @param position The position in the chat messages list.
                     * @return 0 for messages sent using the send button, 1 for messages received.
                     */
                    public int getItemViewType(int position) {
                        ChatMessage chatMessage = messages.get(position);
                        return chatMessage.isSentButton() ? 0 : 1;
                    }
                });
    }

    /**
     * Represents a row holder for displaying chat messages in the RecyclerView.
     */
    class MyRowHolder extends RecyclerView.ViewHolder {
        //        The whole point of the MyRowHolder class is to maintain variables for what you want to set on each row in your list.
        TextView messageText;
        TextView timeText;
        int viewType;
        /**
         * Constructs a MyRowHolder with the specified itemView.
         *
         * @param itemView The view for this row holder.
         * @param viewType
         */
        public MyRowHolder(@NonNull View itemView, int viewType) {
            super(itemView);

            this.viewType = viewType;  // Correct this line

            // Depending on the viewType, find the appropriate TextViews
            if (this.viewType == 0) {
                messageText = itemView.findViewById(R.id.messageText);
                timeText = itemView.findViewById(R.id.timeText);
            } else {
                messageText = itemView.findViewById(R.id.receiveText);
                timeText = itemView.findViewById(R.id.receiveTime);
            }
        }

        /**
         * Returns the number of chat messages.
         *
         * @return The number of chat messages.
         */
        int getItemCount() {
            int size = messages.size();
            return size;
        }


    }


}