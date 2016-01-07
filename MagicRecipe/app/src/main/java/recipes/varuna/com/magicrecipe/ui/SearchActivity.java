package recipes.varuna.com.magicrecipe.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import recipes.varuna.com.magicrecipe.R;
import recipes.varuna.com.magicrecipe.api.RecipeAPI;
import recipes.varuna.com.magicrecipe.model.Data;
import recipes.varuna.com.magicrecipe.model.DataStore;
import recipes.varuna.com.magicrecipe.utils.Utils;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SearchActivity extends AppCompatActivity {

    public static final String ROOT_URL = "http://www.recipepuppy.com";
    private EditText searchterm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchterm = (EditText) findViewById(R.id.search_term);
        final Button search = (Button) findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetworkAvaiable(SearchActivity.this)) {
                    if (!searchterm.getText().toString().equalsIgnoreCase("")) {
                        getRecipe();
                    } else {
                        Utils.showAlert(SearchActivity.this, "Alert!!", "Please enter the ingredients");
                    }
                } else {
                    Utils.showAlert(SearchActivity.this, "Internet is turned off", "Please check your internet connection and try again");
                }
            }
        });
    }

    private void getRecipe() {
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);
        RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(ROOT_URL).build();
        RecipeAPI api = restAdapter.create(RecipeAPI.class);
//        api.getRecipe(new Callback<Data>() {
//            @Override
//            public void success(Data data, Response response) {
//                Log.d("SUCCESS","SUCCESS");
//                loading.dismiss();
//                showList(data.getResults());
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//
//            }
//        });

        api.getRecipe(searchterm.getText().toString(), new Callback<Data>() {
            @Override
            public void success(Data data, Response response) {
                loading.dismiss();
                if (data.getResults() != null && data.getResults().size() != 0) {
                    DataStore.getInstance().setListOfRecipes(data.getResults());
                    Intent intent = new Intent(SearchActivity.this, RecipeActivity.class);
                    startActivity(intent);
                } else {
                    Utils.showAlert(SearchActivity.this, "Alert!!", "No recipes found for the ingredients.");
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Utils.showAlert(SearchActivity.this, "Alert!!", "Error");
            }
        });
    }

}
