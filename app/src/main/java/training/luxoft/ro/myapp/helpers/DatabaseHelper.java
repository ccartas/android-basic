package training.luxoft.ro.myapp.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import training.luxoft.ro.myapp.models.ToDoItem;
import training.luxoft.ro.myapp.models.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="todo-app.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_USERS_TABLE = "CREATE TABLE " + Entities.UserEntity.USER_TABLE_NAME +
                                                    " (" + Entities.UserEntity.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                    Entities.UserEntity.USER_NAME + " TEXT, " + Entities.UserEntity.USER_SURNAME +
                                                    " TEXT, " + Entities.UserEntity.USER_USERNAME + " TEXT, " + Entities.UserEntity.USER_PASSWORD +
                                                    " TEXT)";

    private static final String CREATE_TODO_TABLE = "CREATE TABLE " + Entities.ToDoEntity.TODO_TABLE_NAME + " (" + Entities.ToDoEntity.TODO_ID +
                                                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + Entities.ToDoEntity.TODO_TASK_NAME + " TEXT," +
                                                    Entities.ToDoEntity.TODO_PRIORITY + " TEXT, " + Entities.ToDoEntity.TODO_DURATION + " INTEGER, " +
                                                    Entities.ToDoEntity.TODO_STATUS + " INTEGER DEFAULT 0)";

    private static final String DROP_USERS_TABLE = "DROP TABLE IF EXISTS " + Entities.UserEntity.USER_TABLE_NAME;
    private static final String DROP_TODO_TABLE = "DROP TABLE IF EXISTS " + Entities.ToDoEntity.TODO_TABLE_NAME;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USERS_TABLE);
        db.execSQL(DROP_TODO_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long registerUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Entities.UserEntity.USER_NAME, user.getName());
        values.put(Entities.UserEntity.USER_SURNAME, user.getSurname());
        values.put(Entities.UserEntity.USER_USERNAME, user.getUsername());
        values.put(Entities.UserEntity.USER_PASSWORD, user.getPassword());
        long result = db.insert(Entities.UserEntity.USER_TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public boolean checkIfUserExists(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String selection =  Entities.UserEntity.USER_USERNAME + "= ?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(Entities.UserEntity.USER_TABLE_NAME,
                null, selection,selectionArgs,
                null, null, null);

        if(cursor.getCount() > 0){
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

    public User loginUser(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = Entities.UserEntity.USER_USERNAME + "= ? AND " +
                        Entities.UserEntity.USER_PASSWORD + "= ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(Entities.UserEntity.USER_TABLE_NAME,
                null,
                selection, selectionArgs, null, null, null);
        User user = null;
        if(cursor.getCount() == 1){
            cursor.moveToFirst();
            user = new User();
            user.setName(cursor.getString(1));
            user.setSurname(cursor.getString(2));
            user.setUsername(cursor.getString(3));
            user.setPassword(cursor.getString(4));
        }
        cursor.close();
        db.close();
        return user;
    }

    public long addToDo(ToDoItem item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Entities.ToDoEntity.TODO_TASK_NAME, item.getTaskName());
        values.put(Entities.ToDoEntity.TODO_PRIORITY, item.getPriority());
        values.put(Entities.ToDoEntity.TODO_DURATION, item.getDuration());
        long result = db.insert(Entities.ToDoEntity.TODO_TABLE_NAME, null, values);

        db.close();
        return result;
    }

    public List<ToDoItem> getAllToDoItems(){
        List<ToDoItem> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Entities.ToDoEntity.TODO_TABLE_NAME,
                            null, null, null, null, null, null);
        while(cursor.moveToNext()){
            ToDoItem toDo = new ToDoItem();
            toDo.setTaskName(cursor.getString(1));
            toDo.setPriority(cursor.getString(2));
            toDo.setDuration(cursor.getInt(3));
            toDo.setIsDone(cursor.getInt(4) > 0);
            items.add(toDo);
        }
        cursor.close();
        db.close();
        return items;
    }

    public List<ToDoItem> getUndoneToDoItems(){
        List<ToDoItem> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = Entities.ToDoEntity.TODO_STATUS + "= ?";
        String[] selectionArgs = {"0"};
        Cursor cursor = db.query(Entities.ToDoEntity.TODO_TABLE_NAME,
                null, selection, selectionArgs, null, null, null);
        while(cursor.moveToNext()){
            ToDoItem toDo = new ToDoItem();
            toDo.setTaskName(cursor.getString(1));
            toDo.setPriority(cursor.getString(2));
            toDo.setDuration(cursor.getInt(3));
            toDo.setIsDone(false);
            items.add(toDo);
        }
        cursor.close();
        db.close();
        return items;
    }




}
