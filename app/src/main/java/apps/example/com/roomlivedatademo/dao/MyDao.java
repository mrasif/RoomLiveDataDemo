package apps.example.com.roomlivedatademo.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import apps.example.com.roomlivedatademo.models.Item;

/**
 * Created by root on 21/2/18.
 */
@Dao
public interface MyDao {
    @Insert
    void insertAll(Item... items);

    @Delete
    void delete(Item item);

    @Query("DELETE FROM items")
    void deleteAll();

    @Query("SELECT * FROM items")
    LiveData<List<Item>> getItems();

    @Query("SELECT COUNT(id) FROM items")
    int getCount();
}
