package algonquin.cst2335;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity //mapping variables to columns
public class ChatMessage {

    @PrimaryKey(autoGenerate = true) //the database will increment them for us
    @ColumnInfo(name="id")
    long id;


    @ColumnInfo(name="MessageColumn")
    String message;
    @ColumnInfo(name="TimeSentColumn")
    String timeSent;

    @ColumnInfo(name="SendRecieveColumn")
    boolean sentOrReceive;


    public ChatMessage() { }
    public ChatMessage(String m, String tm, boolean sr){
        message= m;
        timeSent = tm;
        sentOrReceive = sr;
    }
}