package training.luxoft.ro.myapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import training.luxoft.ro.myapp.models.ToDoItem;

public class AddItemActivity extends AppCompatActivity {

    EditText mItemNameET;
    RadioGroup mItemPriorityRG;
    SeekBar mItemDurationSB;
    Button mAddItemBtn;

    String priority;
    int duration = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        this.mItemNameET = findViewById(R.id.niItemNameET);
        this.mItemPriorityRG = findViewById(R.id.niPrioritiesRG);
        this.mItemDurationSB = findViewById(R.id.niDurationSB);

        this.mAddItemBtn = findViewById(R.id.niConfirmBtn);

        this.mItemPriorityRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton btn = findViewById(checkedId);
                priority = btn.getText().toString();
            }

        });

        this.mItemDurationSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                duration = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        this.mAddItemBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(validateFields()){
                    Intent i = new Intent();
                    ToDoItem item = new ToDoItem(mItemNameET.getText().toString(), priority, duration, false);
                    i.putExtra("item", item);
                    setResult(Activity.RESULT_OK, i);
                    finish();

                } else {
                    Toast.makeText(AddItemActivity.this,
                            "Trebuie sa completati toate campurile!",
                            Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    private boolean validateFields(){
        if(this.mItemNameET.getText().toString().trim().length() >= 2 &&
                this.priority != null &&
                this.duration >= 1) {
            return true;
        }
        return false;
    }
}
