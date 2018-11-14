package training.luxoft.ro.myapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import training.luxoft.ro.myapp.helpers.DatabaseHelper;
import training.luxoft.ro.myapp.helpers.NotificationsHelper;
import training.luxoft.ro.myapp.helpers.SharedPreferencesHelper;
import training.luxoft.ro.myapp.models.ToDoItem;
import training.luxoft.ro.myapp.models.User;


public class LoginActivity extends AppCompatActivity {

    EditText mUsernameET;
    EditText mPasswordET;
    Button mLoginBtn;
    Button mRegisterBtn;

    SharedPreferencesHelper preferencesHelper;
    NotificationsHelper notificationsHelper;
    DatabaseHelper databaseHelper;


    private static final int REGISTER_REQUEST_CODE = 1;

    public static List<User> users = new ArrayList<>();
    public static List<ToDoItem> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.mUsernameET = findViewById(R.id.loginUsernameET);
        this.mPasswordET = findViewById(R.id.loginPasswordET);
        this.mLoginBtn = findViewById(R.id.loginBtn);
        this.mRegisterBtn = findViewById(R.id.registerBtn);

        this.preferencesHelper = new SharedPreferencesHelper(LoginActivity.this);
        this.notificationsHelper = new NotificationsHelper(LoginActivity.this);
        this.databaseHelper = new DatabaseHelper(LoginActivity.this); //getApplicationContext()

        if(this.preferencesHelper.getValue("username", null) != null){
            Intent i = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(i);
            finish();
        }

        this.mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this,
                                            RegisterActivity.class);

                startActivityForResult(intent, REGISTER_REQUEST_CODE);
            }
        });

        this.mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateLogin()){
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Eroare! Username sau parola gresite!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REGISTER_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            User u = (User) data.getExtras().get("user-object");
            //users.add(u);
            if(databaseHelper.registerUser(u) != -1){
                this.notificationsHelper.sendNotification("Congratulations!",
                        "Welcome to toDoApp " + u.getName());
            } else {
                Toast.makeText(LoginActivity.this, "An error occured!",
                            Toast.LENGTH_LONG).show();
            }

        }
    }

    private boolean validateLogin(){
        if(this.mUsernameET.getText().toString().trim().length() >= 6 &&
                this.mPasswordET.getText().toString().length() >= 6){
            for(User u: users){
                if(u.getUsername().equals(this.mUsernameET.getText().toString()) &&
                        u.getPassword().equals(this.mPasswordET.getText().toString())){
                    this.preferencesHelper.addValue("username", u.getUsername());
                    this.preferencesHelper.addValue("password", u.getPassword());
                    return true;
                }
            }
        }
        return false;
    }


}
