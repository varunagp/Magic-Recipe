package recipes.varuna.com.magicrecipe.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import recipes.varuna.com.magicrecipe.R;
import recipes.varuna.com.magicrecipe.adapter.RecipeAdapter;
import recipes.varuna.com.magicrecipe.model.DataStore;

public class RecipeActivity extends AppCompatActivity {

    private ListView listView;
    public static final String POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("List of Recipes");
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.listViewBooks);
        showList();
    }

    private void showList() {
        RecipeAdapter adapter = new RecipeAdapter(this, DataStore.getInstance().getListOfRecipes());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(RecipeActivity.this,RecipieDetailActivity.class);
                intent.putExtra(POSITION,position);
                startActivity(intent);
            }
        });
    }

}
