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
    @ColumnInfo(name = "messageColumn")
    protected String message;



    @ColumnInfo(name = "timeSentColumn")
    protected String timeSent;

    @ColumnInfo(name = "sentOrReceiveColumn")
    protected boolean isSentButton;

    public ChatMessage(){}

    public ChatMessage(String messageColumn, String timeSentColumn, boolean sentOrReceiveColumn) {
        message = messageColumn;
        timeSent = timeSentColumn;
        isSentButton = sentOrReceiveColumn;
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

