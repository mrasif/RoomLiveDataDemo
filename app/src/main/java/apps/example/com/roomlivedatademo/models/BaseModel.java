package apps.example.com.roomlivedatademo.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.TypeConverters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import apps.example.com.roomlivedatademo.utils.AllKeys;
import apps.example.com.roomlivedatademo.utils.Converters;

/**
 * Created by root on 22/2/18.
 */

public class BaseModel {

    @ColumnInfo(name = "created_at")
    @TypeConverters({Converters.class})
    public Date created_at;

    @ColumnInfo(name = "updated_at")
    @TypeConverters({Converters.class})
    public Date updated_at;

    public BaseModel() {
        if(null==this.created_at){
            this.created_at = new Date();
        }
        if(null==this.updated_at){
            this.updated_at = new Date();
        }
    }
}
