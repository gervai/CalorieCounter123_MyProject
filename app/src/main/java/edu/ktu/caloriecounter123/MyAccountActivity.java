package edu.ktu.caloriecounter123;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MyAccountActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPassObject;
    EditText etName, etAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        btnPassObject = (Button)findViewById(R.id.btn_pass_obj);
        etName = (EditText)findViewById(R.id.et_name);
        etAge = (EditText)findViewById(R.id.et_age);

        btnPassObject.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SecondPersonActivity.class);

        Person person = new Person();
        person.setName(etName.getText().toString());
        person.setAge(Integer.parseInt(etAge.getText().toString()));

        intent.putExtra("person", person);

        startActivity(intent);
    }
}
