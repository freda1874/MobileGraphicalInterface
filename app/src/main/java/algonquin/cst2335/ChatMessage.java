package algonquin.cst2335;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity //mapping variables to columns
public class ChatMessage {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long Id;
    @ColumnInfo(name = "message")
    protected String message;



    @ColumnInfo(name = "timeSent")
    protected String timeSent;

    @ColumnInfo(name = "sentOrReceive")
    protected boolean isSentButton;



    public ChatMessage(String m, String tm, boolean sr) {
        message = m;
        timeSent = tm;
        isSentButton = sr;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public boolean isSentButton() {
        return isSentButton;
    }
}

