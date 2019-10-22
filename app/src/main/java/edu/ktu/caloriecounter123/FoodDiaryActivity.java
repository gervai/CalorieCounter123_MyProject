package edu.ktu.caloriecounter123;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class FoodDiaryActivity extends AppCompatActivity {

    private ArrayList<String> itemList;
    private ArrayAdapter<String> adapter;
    private EditText itemText;
    Button addButton;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_diary);

        lv = (ListView)findViewById(R.id.listview);
        itemText = (EditText)findViewById(R.id.txtinput);
        addButton = (Button)findViewById(R.id.btnadd);

        final String[] items = {"Egg", "Apple", "Bread", "Tea", "Pear", "Chicken", "Meat", "Rice", "Coffee"};

        itemList = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemList);

        View.OnClickListener addlistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList.add(itemText.getText().toString());
                System.out.println(itemList);

                //itemList.remove(1);

                int len = items.length;
                for ( int i = len; i  >= 0; i--)
                {
                    if (i%2 == 0)
                    {
                        itemList.remove(i);
                        System.out.println(items[i]);
                    }


                }

                System.out.println(itemList);

                itemText.setText("");
                adapter.notifyDataSetChanged();
            }
        };

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SparseBooleanArray positionchecker = lv.getCheckedItemPositions();
                int count = lv.getCount();
                for (int item = count-1; item>=0; item--){
                    if (positionchecker.get(item)){
                        adapter.remove(itemList.get(item));
                        Toast.makeText(FoodDiaryActivity.this,"Products Delete Successfully", Toast.LENGTH_SHORT).show();
                    }
                }
                positionchecker.clear();
                adapter.notifyDataSetChanged();

                return false;
            }
        });
        addButton.setOnClickListener(addlistener);
        lv.setAdapter(adapter);
    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)menuItem.getActionView();
        searchView.setQueryHint("Type here to Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
