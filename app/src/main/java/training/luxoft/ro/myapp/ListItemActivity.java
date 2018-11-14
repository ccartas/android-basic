package training.luxoft.ro.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import training.luxoft.ro.myapp.helpers.DatabaseHelper;
import training.luxoft.ro.myapp.models.ToDoItem;

public class ListItemActivity extends AppCompatActivity {

    ListView mItemsLV;
    DatabaseHelper databaseHelper;
    List<ToDoItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        this.mItemsLV = findViewById(R.id.liItemsLV);
        this.databaseHelper = new DatabaseHelper(ListItemActivity.this);
        this.items = this.databaseHelper.getAllToDoItems();

        List<String> values = new ArrayList<>();
        for(ToDoItem item : this.items){
            values.add(item.getTaskName() + " DONE: "+item.getIsDone());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(ListItemActivity.this,
                R.layout.support_simple_spinner_dropdown_item,
                values);
        this.mItemsLV.setAdapter(adapter);

    }
}
