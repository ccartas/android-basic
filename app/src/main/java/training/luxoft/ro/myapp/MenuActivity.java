package training.luxoft.ro.myapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import training.luxoft.ro.myapp.helpers.SharedPreferencesHelper;
import training.luxoft.ro.myapp.models.ToDoItem;

public class MenuActivity extends AppCompatActivity {

    Button mNewItemBtn;
    Button mListItemsBtn;
    Button mProfileBtn;
    Button mLogoutBtn;


    public static final int NEW_ITEM_REQUEST_CODE = 10;

    SharedPreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.mNewItemBtn = findViewById(R.id.menuNewItemBtn);
        this.mListItemsBtn = findViewById(R.id.menuListItemsBtn);
        this.mProfileBtn = findViewById(R.id.menuProfileBtn);
        this.mLogoutBtn = findViewById(R.id.menuLogoutBtn);

        this.preferencesHelper = new SharedPreferencesHelper(MenuActivity.this);

        this.mNewItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AddItemActivity.class);
                startActivityForResult(intent, NEW_ITEM_REQUEST_CODE);
            }
        });

        this.mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MenuActivity.this);
                builder.setTitle(R.string.logoutDialogTitle);
                builder.setMessage(R.string.logoutDialogMessage);
                builder.setPositiveButton(R.string.logoutDialogPText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        preferencesHelper.removeAllEntries();
                        Intent i = new Intent(MenuActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                });

                builder.setNegativeButton(R.string.logoutDialogNText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_ITEM_REQUEST_CODE && resultCode == Activity.RESULT_OK){
           LoginActivity.items.add((ToDoItem) data.getExtras().get("item"));
            Toast.makeText(MenuActivity.this, "To Do Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
