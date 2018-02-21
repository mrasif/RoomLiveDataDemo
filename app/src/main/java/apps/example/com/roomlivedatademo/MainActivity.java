package apps.example.com.roomlivedatademo;

import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import apps.example.com.roomlivedatademo.adapters.ItemAdapter;
import apps.example.com.roomlivedatademo.dao.AppDatabase;
import apps.example.com.roomlivedatademo.models.Item;
import apps.example.com.roomlivedatademo.utils.AllKeys;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    AppDatabase db;
    EditText etTitle, etBody;
    Button btnAdd, btnDeleteAll;
    TextView tvTotal;
    RecyclerView rvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTitle=findViewById(R.id.etTitle);
        etBody=findViewById(R.id.etBody);
        btnAdd=findViewById(R.id.btnAdd);
        btnDeleteAll=findViewById(R.id.btnDeleteAll);
        tvTotal=findViewById(R.id.tvTotal);
        rvItems=findViewById(R.id.rvItems);

        db= Room.databaseBuilder(getApplicationContext(), AppDatabase.class, AllKeys.DB_NAME)
                .allowMainThreadQueries()
                .build();

        /*db.getMyDao().insertAll(new Item("Item 1","Body 1"));
        System.out.println(db.getMyDao().getCount());*/

        etTitle.addTextChangedListener(new MyTextWatcher(etTitle));
        etBody.addTextChangedListener(new MyTextWatcher(etBody));
        btnAdd.setOnClickListener(this);
        btnDeleteAll.setOnClickListener(this);
        db.getMyDao().getItems().observeForever(new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                Collections.reverse(items);
                tvTotal.setText("Total: "+String.valueOf(items.size()));
                rvItems.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rvItems.setAdapter(new ItemAdapter(MainActivity.this,items));
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAdd: {
                addItem(etTitle,etBody);
            } break;
            case R.id.btnDeleteAll: {
                deleteAllItems();
            } break;
        }
    }

    private void deleteAllItems() {
        db.getMyDao().deleteAll();
        Toast.makeText(this, "All items deleted.", Toast.LENGTH_SHORT).show();
    }

    private void addItem(EditText etTitle, EditText etBody) {
        String title= etTitle.getText().toString();
        String body=etBody.getText().toString();
        if(TextUtils.isEmpty(title)){
            etTitle.setError("Title cann't be empty.");
            etTitle.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(body)){
            etBody.setError("Body cann't be empty.");
            etBody.requestFocus();
            return;
        }

        Item item=new Item(title,body);
        db.getMyDao().insertAll(item);
        Toast.makeText(this, "Item added.", Toast.LENGTH_SHORT).show();
        etTitle.setText("");
        etBody.setText("");
        Log.d(TAG, "addItem: Item added -> "+item);
    }

    class MyTextWatcher implements TextWatcher{

        EditText editText;

        public MyTextWatcher(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (!TextUtils.isEmpty(editText.getText().toString())){
                editText.setError(null);
            }
        }
    }
}
