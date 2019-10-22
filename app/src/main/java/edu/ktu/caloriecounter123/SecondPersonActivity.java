package edu.ktu.caloriecounter123;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondPersonActivity extends AppCompatActivity {

    TextView tvPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_person);

        Intent intent = getIntent();

        Person person = (Person) intent.getSerializableExtra("person");

        tvPerson = (TextView)findViewById(R.id.tv_person);

        tvPerson.setText(person.toString());
    }
}
