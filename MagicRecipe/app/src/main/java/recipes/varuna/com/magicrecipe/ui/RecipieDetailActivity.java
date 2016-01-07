package recipes.varuna.com.magicrecipe.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import recipes.varuna.com.magicrecipe.R;
import recipes.varuna.com.magicrecipe.model.DataStore;
import recipes.varuna.com.magicrecipe.model.Result;

public class RecipieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Recipes Details");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        List<Result> listOfRecipes = DataStore.getInstance().getListOfRecipes();

        int position = intent.getIntExtra(RecipeActivity.POSITION, 0);

        TextView title = (TextView) findViewById(R.id.title_value);
        TextView ingredients = (TextView) findViewById(R.id.ingredients_value);
        ImageView image = (ImageView) findViewById(R.id.image);

        title.setText(listOfRecipes.get(position).getTitle());
        ingredients.setText(listOfRecipes.get(position).getIngredients());

        if (listOfRecipes.get(position).getThumbnail() != null && !listOfRecipes.get(position).getThumbnail().equalsIgnoreCase("")) {
            Picasso.with(this).load(listOfRecipes.get(position).getThumbnail())
                    .placeholder(R.drawable.ic_launcher)
                    .error(R.drawable.ic_launcher).tag(this).into(image);
        }
    }

}
