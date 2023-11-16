package algonquin.cst2335;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.databinding.ReceiveMessageBinding;
import algonquin.cst2335.databinding.SentMessageBinding;

public class ChatRoom extends AppCompatActivity {
    ActivityChatRoomBinding binding;
    ArrayList<ChatMessage> theMessages = null;
    ChatRoomViewModel chatModel;
    RecyclerView.Adapter myAdapter;

    ChatMessageDAO mDao;

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


        //get data from ViewModel
        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);

        // Get messages from ViewModel
        theMessages = chatModel.theMessages;


        MessageDatabase db = Room.databaseBuilder(getApplicationContext(),
                        MessageDatabase.class,
                        "fileOnYourPhone")
                .fallbackToDestructiveMigration()
                .build();
        //load message from database
        mDao = db.cmDAO();//get a DAO object to interact with database

        //load all messages from database:
        Executor thread = Executors.newSingleThreadExecutor();
        thread.execute(() -> {
            List<ChatMessage> fromDatabase = mDao.getAllMessages();//return a List
            theMessages.addAll(fromDatabase);//this adds all messages from the database

        });

        //end of loading from database


//        E means day of the week, and having 4 EEEE means write the whole word of day of the week
        binding.sendButton.setOnClickListener(click -> {
            String textInput = binding.textInput.getText().toString();

            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());

            // Create a ChatMessage object with isSentButton as false for the Receive button
            ChatMessage thisMessage = new ChatMessage(textInput, currentDateandTime, true);

            theMessages.add(thisMessage);


            //clear the previous text:
            binding.textInput.setText("");
            //tell the recycle view to update:
            myAdapter.notifyDataSetChanged();//will redraw


            // add to database on another thread:
            Executor thread1 = Executors.newSingleThreadExecutor();
            thread1.execute(() -> {
                //this is on a background thread
                thisMessage.Id = mDao.insertMessage(thisMessage); //get the
                // ID from the database
                Log.d("TAG", "The id created is:" + thisMessage.Id);
            }); //the body of run()
        });

        binding.receiveButton.setOnClickListener(click -> {
            String textInput = binding.textInput.getText().toString();

            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());

            // Create a ChatMessage object with isSentButton as false for the Receive button
            ChatMessage thisMessage = new ChatMessage(textInput,
                    currentDateandTime, false);

            theMessages.add(thisMessage);


            //clear the previous text:
            binding.textInput.setText("");
            //tell the recycle view to update:
            myAdapter.notifyDataSetChanged();//will redraw


            // add to database on another thread:
            Executor thread1 = Executors.newSingleThreadExecutor();
            thread1.execute(() -> {
                //this is on a background thread
                thisMessage.Id = mDao.insertMessage(thisMessage); //get the
                // ID from the database
                Log.d("TAG", "The id created is:" + thisMessage.Id);
            }); //the body of run()
        });


        binding.recycleView.setAdapter(myAdapter =
                new RecyclerView.Adapter<MyRowHolder>() {
                    @NonNull
                    @Override

                    //        onCreateViewHolder function is responsible for creating a layout
//        for a row, and setting the TextViews in code
                    public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        if (viewType == 0) {
                            SentMessageBinding binding =
                                    SentMessageBinding.inflate(getLayoutInflater(), parent, false);

                            return new MyRowHolder(binding.getRoot());
                        } else {
                            ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(getLayoutInflater(), parent, false);
                            return new MyRowHolder(binding.getRoot());
                        }


                    }

                    /**
                     * Returns the view type for a given position in the chat messages list.
                     *
                     * @param position The position in the chat messages list.
                     * @return 0 for messages sent using the send button, 1 for messages received.
                     */
                    public int getItemViewType(int position) {
                        //given the row, return an layout id for that row

                        if (position < 3)
                            return 0;
                        else
                            return 1;
                    }

                    @Override


                    //        The function onBindViewHolder is where you set the objects in your layout for the row. Right now, your MyRowHolder class has two TextView objects.
                    //        The onBindViewHolder() function is meant to set the data for your ViewHolder object that will go at row position in the list
                    public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                        //replace the default text with text at row position

                        String forRow = theMessages.get(position).message;
                        holder.message.setText(forRow);
                        holder.time.setText(theMessages.get(position).timeSent);
                    }

                    @Override
                    public int getItemCount() {
                        return theMessages.size();
                    }


                });//populate the list
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Represents a row holder for displaying chat messages in the RecyclerView.
     */
    class MyRowHolder extends RecyclerView.ViewHolder {
        //        The whole point of the MyRowHolder class is to maintain variables for what you want to set on each row in your list.
        TextView message;
        TextView time;


        /**
         * Constructs a MyRowHolder with the specified itemView.
         *
         * @param entireRow The view for this row holder.

         */
        public MyRowHolder(@NonNull View entireRow) {
            super(entireRow);
            entireRow.setOnClickListener(clk -> {

                // tell you which row (position) this row is currently in the adapter object
                int position = getAbsoluteAdapterPosition();
                ChatMessage toDelete = theMessages.get(position);
                // alert dialog to ask if you want to do this first.
//                AlertDialog.Builder builder = new AlertDialog.Builder(ChatRoom.this);
//                //The AlertDialog gives you two buttons to use, a positive button, and a negative button
////                Clicking on the No shouldn't delete anything so just leave that lambda function empty
//
//                builder.setNegativeButton("No" , (btn, obj)->{ /* if no is clicked */  }  );
//                builder.setMessage("Do you want to delete this message?");
//                builder.setTitle("Delete");
//
//
//                builder.setPositiveButton("yes", (p1, p2) -> {
//                    //add to database on another thread
//                    Executor thread = Executors.newSingleThreadExecutor();
//                    /*this runs in another thread*/
//                    thread.execute(() -> {
//                        mDao.deleteMessage(toDelete);//get the id from
//                    });
//                    theMessages.remove(position);//remove from the array list
//                    myAdapter.notifyDataSetChanged();//redraw the list
//
////give feedback:anything on screen
//                    Snackbar.make( itemView , "You deleted the row", Snackbar.LENGTH_LONG)
//                            .setAction("Undo", (btn) -> {
//                                Executor thread2 = Executors.newSingleThreadExecutor();
//                                thread2.execute(( ) -> {
//                                    mDao.insertMessage(toDelete);
//                                });
//
//
//                                theMessages.add(position, toDelete);
//                                myAdapter.notifyDataSetChanged();//redraw the list
//                            })
//                            .show();
//
//                });
//                builder.create().show(); //this has to be last

            });


            message = itemView.findViewById(R.id.messageText);
            time = itemView.findViewById(R.id.timeText); //find the ids from XML to java

        }


    }


}