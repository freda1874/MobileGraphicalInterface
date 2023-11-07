package algonquin.cst2335;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ChatMessageDAO {
    @Insert
    public long insertMessage(ChatMessage m);//inserting, long is the id

    @Query( "Select * from ChatMessage;")//the SQL query
    public List<ChatMessage> getAllMessages();

    //number of rows deleted
    @Delete
    void deleteMessage(ChatMessage m);//delete the message


}
