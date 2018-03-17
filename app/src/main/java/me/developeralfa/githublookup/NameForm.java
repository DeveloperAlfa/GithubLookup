package me.developeralfa.githublookup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NameForm extends AppCompatActivity {

    EditText editText;
    Button button;
    UserAsyncTask userAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_form);

        editText = findViewById(R.id.editText1);
        button = findViewById(R.id.button);
        final Intent intent  = new Intent(this,Details.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit = editText.getText().toString();
                intent.putExtra("text",edit);
                startActivity(intent);

            }
        });

    }
}
