package recipes.varuna.com.magicrecipe.api;


import recipes.varuna.com.magicrecipe.model.Data;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface RecipeAPI {

    @GET("/api/?&q=omelet&p=3")
    void getRecipe(@Query("i")  String ingredients, Callback<Data> response);
}
