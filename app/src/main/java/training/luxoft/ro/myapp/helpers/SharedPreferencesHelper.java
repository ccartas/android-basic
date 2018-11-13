package training.luxoft.ro.myapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {

    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;

    private static final String SHARED_PREFERENCE_NAME = "app-secret";

    public SharedPreferencesHelper(Context context){
        this.preferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        this.editor = this.preferences.edit();
    }

    public void addValue(String key, String value){
        this.editor.putString(key, value);
        this.editor.commit();
    }

    public String getValue(String key, String defValue){
        return this.preferences.getString(key, defValue);
    }

    public void removeAllEntries(){
        this.editor.clear();
        this.editor.commit();
    }

}
