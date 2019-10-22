package edu.ktu.caloriecounter123;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private ListView myList;
    private ListAdapter adapter;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        myList = (ListView) findViewById(R.id.listView);

        Button buttonSort = findViewById(R.id.button_sort);

        preferences = this.getSharedPreferences("My_Pref", MODE_PRIVATE);

        getMyList();

    }

    private void getMyList(){
        List<ListProduct> items = new ArrayList<>();

        Intent intent = getIntent();
        if(intent.getBooleanExtra("flag",true)){

            items.add(new ListProduct("Egg", R.drawable.ic_image_24dp,"One egg has only 75 calories"));
            items.add(new ListProduct("Apple", R.drawable.ic_image_24dp,"Medium-sized apple (100 grams) has 52 calories"));
            items.add(new ListProduct("Bread",R.drawable.ic_image_24dp,"One slice, or 45 grams (g) has 140 calories"));
            items.add(new ListProduct("Tea",R.drawable.ic_image_24dp, "100 grams tea has 1 calories"));
            items.add(new ListProduct("Pear",R.drawable.ic_image_24dp,"One medium pear has 101 calories"));
            items.add(new ListProduct("Chicken",R.drawable.ic_image_24dp,"A whole chicken breast with skin provides 366 calories"));
            items.add(new ListProduct("Meat",R.drawable.ic_image_24dp,"100 grams meat has 143 calories"));
            items.add(new ListProduct("Rice",R.drawable.ic_image_24dp,"100 grams rice has 130 calories"));
            items.add(new ListProduct("Coffee",R.drawable.ic_image_24dp,"100 grams coffe has 0 calories"));
        } else{

            items.add(new ListProduct("Egg",R.drawable.ic_image_24dp,
                    "One egg has only 75 calories but 7 grams of high-quality protein, " +
                            "5 grams of fat, and 1.6 grams of saturated fat, along with iron, vitamins, "+
                            "minerals, and carotenoids. The egg is a powerhouse of disease-fighting nutrients like " +
                            "lutein and zeaxanthin."));
            items.add(new ListProduct("Apple",R.drawable.ic_image_24dp,
                    "Apple nutrition facts:\n" +
                            "Calories: 52.\n" +
                            "Water: 86%\n" +
                            "Protein: 0.3 grams.\n" +
                            "Carbs: 13.8 grams.\n" +
                            "Sugar: 10.4 grams.\n" +
                            "Fiber: 2.4 grams.\n" +
                            "Fat: 0.2 grams."));
            items.add(new ListProduct("Bread",R.drawable.ic_image_24dp,
                    "(28g) wheat bread has:\n" +
                            "Calories: 69\n" +
                            "Fat: 0.9g\n" +
                            "Sodium: 132mg\n" +
                            "Carbohydrates: 11.6g\n" +
                            "Fiber: 1.9g\n" +
                            "Sugars: 1.6g\n" +
                            "Protein: 3.6g"));
            items.add(new ListProduct("Tea",R.drawable.ic_image_24dp,
                    "Calories 1\n" +
                            "Total Fat 0g\t0 %\n" +
                            "Sodium 3mg\t0 %\n" +
                            "Total Carbohydrate 0.2g\t0 %\n" +
                            "Dietary Fiber 0g\t0 %\n" +
                            "Sugar 0g\t\n" +
                            "Protein 0g\t0 %"));
            items.add(new ListProduct("Pear",R.drawable.ic_image_24dp,
                    "101 calories.\n" +
                            "0 g of fat.\n" +
                            "27 g of carbohydrate, including 17 g of sugar and 6 g.\n" +
                            "1 g of protein."));
        }

        String mSortSetting = preferences.getString("Sort", "Ascending");

        if (mSortSetting.equals("Ascending")) {
            Collections.sort(items, ListProduct.By_TITLE_ASCENDING);
        }
        else if (mSortSetting.equals("Descending")){
            Collections.sort(items, ListProduct.By_TITLE_DESCENDING);
        }

        adapter = new ListAdapter(this,items);
        myList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.sorting){
            sortDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sortDialog(){
        String[] options = {"Ascending", "Descending"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Sort by");
        builder.setIcon(R.drawable.ic_action_sort);

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Sort", "Ascending");
                    editor.apply();
                    getMyList();
                }
                if (which == 1){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Sort", "Descending");
                    editor.apply();
                    getMyList();
                }
            }
        });

        builder.create().show();
    }


/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem itemm = menu.findItem(R.id.search_food);
        SearchView searchView = (SearchView)itemm.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }*/
}
