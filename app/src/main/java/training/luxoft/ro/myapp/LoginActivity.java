package training.luxoft.ro.myapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    EditText mUsernameET;
    EditText mPasswordET;
    Button mLoginBtn;
    Button mRegisterBtn;

    private static final int REGISTER_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.mUsernameET = findViewById(R.id.loginUsernameET);
        this.mPasswordET = findViewById(R.id.loginPasswordET);
        this.mLoginBtn = findViewById(R.id.loginBtn);
        this.mRegisterBtn = findViewById(R.id.registerBtn);

        this.mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,
                                            RegisterActivity.class);

                startActivityForResult(intent, REGISTER_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REGISTER_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Toast.makeText(LoginActivity.this,
                    "Bine ai venit " + data.getExtras().getString("name"),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
