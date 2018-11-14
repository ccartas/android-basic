package training.luxoft.ro.myapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        this.mItemsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ListItemActivity.this, ItemDetailsActivity.class);
                i.putExtra("selected-todo", items.get(position));
                startActivity(i);
            }
        });

        this.mItemsLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog dialog = new AlertDialog.Builder(ListItemActivity.this).create();
                dialog.setTitle("You selected the following task:" + items.get(position).getTaskName());
                dialog.setMessage("Please select one of the following operations:");
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Complete Task", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseHelper.updateItem(position + 1);
                    }
                });
                dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Delete Task", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseHelper.deleteItem(position + 1);
                    }
                });
                dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

                return false;
            }
        });
    }
}
