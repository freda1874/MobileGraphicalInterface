package algonquin.cst2335;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {ChatMessage.class}, version = 1)
public abstract class MessageDatabase extends RoomDatabase {


    //only one function:return DAO object
    public abstract ChatMessageDAO cmDAO();
}
