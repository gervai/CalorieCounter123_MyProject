package edu.ktu.caloriecounter123;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    Button fooddiarybtn;
    Button prdctlist;
    Button btnLogout;
    Button myaccnt;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fooddiarybtn = findViewById(R.id.fooddiary);
        prdctlist = findViewById(R.id.productlist);
        btnLogout = findViewById(R.id.logout);
        myaccnt = findViewById(R.id.my_account);

        myaccnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyAccount();
            }
        });

        fooddiarybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFoodDiary();
            }
        });

        prdctlist.setOnClickListener(startProductActivity);
        prdctlist.setOnLongClickListener(startProductActivityLong);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intToMain);
            }
        });

    }

    public void openMyAccount(){
        Intent intent = new Intent(this, MyAccountActivity.class);
        startActivity(intent);
    }

    public void openFoodDiary(){
        Intent intent = new Intent(this, FoodDiaryActivity.class);
        startActivity(intent);
    }

    public void openProductList(boolean b){
        Intent intent = new Intent(context, ProductListActivity.class);
        intent.putExtra("flag",b);
        context.startActivity(intent);
    }

    View.OnClickListener startProductActivity = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            openProductList(true);
        }
    };

    View.OnLongClickListener startProductActivityLong = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            openProductList(false);
            return true;
        }
    };
}
