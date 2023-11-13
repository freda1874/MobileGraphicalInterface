package algonquin.cst2335;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ChatMessage.class}, version = 2)
public abstract class MessageDatabase extends RoomDatabase {

    //only 1function: return the DAO object
    public abstract ChatMessageDAO cmDAO();//name doesn't matter
}