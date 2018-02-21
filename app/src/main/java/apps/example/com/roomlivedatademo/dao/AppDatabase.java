package apps.example.com.roomlivedatademo.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import apps.example.com.roomlivedatademo.models.Item;

/**
 * Created by root on 21/2/18.
 */
@Database(entities = {Item.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MyDao getMyDao();
}
