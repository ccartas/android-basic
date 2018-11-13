package training.luxoft.ro.myapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import training.luxoft.ro.myapp.models.User;


public class RegisterActivity extends AppCompatActivity {

    EditText mNameET;
    EditText mSurnameET;
    EditText mUsernameET;
    EditText mPasswordET;
    EditText mConfirmPassET;

    Button mConfirmRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.mNameET = findViewById(R.id.registerNameET);
        this.mSurnameET = findViewById(R.id.registerSurnameET);
        this.mUsernameET = findViewById(R.id.registerUsernameET);
        this.mPasswordET = findViewById(R.id.registerPasswordET);
        this.mConfirmPassET = findViewById(R.id.registerConfirmPassET);

        this.mConfirmRegisterBtn = findViewById(R.id.confirmRegisterBtn);
        this.mConfirmRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(allFieldsAreVlaid()){
                    Intent i = new Intent();
                    User u = new User(mNameET.getText().toString(), mSurnameET.getText().toString(),
                                        mUsernameET.getText().toString(), mPasswordET.getText().toString());
                    i.putExtra("user-object", u);
                    setResult(Activity.RESULT_OK, i);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this,
                            "Eroare! Atentie la completarea campurilor!",
                            Toast.LENGTH_LONG ).show();
                }
            }
        });
    }

    private boolean allFieldsAreVlaid(){
        if(this.mNameET.getText().toString().trim().length() >= 3 &&
                this.mSurnameET.getText().toString().trim().length() >=3 &&
                this.mUsernameET.getText().toString().trim().length() >=6 &&
                this.mPasswordET.getText().toString().length() >=6 &&
                this.mPasswordET.getText().toString().equals(this.mConfirmPassET.getText().toString())){
            return true;
        }
        return false;
    }
}
