package apps.example.com.roomlivedatademo.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.text.SimpleDateFormat;

import apps.example.com.roomlivedatademo.utils.AllKeys;

/**
 * Created by root on 21/2/18.
 */
@Entity(tableName = "items")
public class Item extends BaseModel{
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "body")
    private String body;

    public Item(String title, String body) {
        super();
        this.title = title;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat(AllKeys.TIME_STAMP_FORMAT);
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", created_at=" + ((null!=created_at)?df.format(created_at):"null") +
                ", updated_at=" + ((null!=updated_at)?df.format(updated_at):"null") +
                '}';
    }
}
