package recipes.varuna.com.magicrecipe.model;

import java.util.List;

/**
 * Created by mobileuser2 on 1/7/2016.
 */
public class DataStore {

    private static DataStore singleton = new DataStore();

    private List<Result> listOfRecipes;

    private DataStore() {
    }

    public static DataStore getInstance() {
        return singleton;
    }

    public List<Result> getListOfRecipes() {
        return listOfRecipes;
    }

    public void setListOfRecipes(List<Result> listOfRecipes) {
        this.listOfRecipes = listOfRecipes;
    }
}
