package training.luxoft.ro.myapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                Intent i = new Intent();
                i.putExtra("name", mNameET.getText().toString());
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
    }
}
