package recipes.varuna.com.magicrecipe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import recipes.varuna.com.magicrecipe.R;
import recipes.varuna.com.magicrecipe.model.Result;

public class RecipeAdapter extends BaseAdapter {

    List<Result> myList = new ArrayList<Result>();
    LayoutInflater inflater;
    Context context;

    public RecipeAdapter(Context context, List<Result> myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Result getItem(int position) {

        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.recipe_list_item, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        Result currentListData = getItem(position);

        mViewHolder.tvTitle.setText(currentListData.getTitle());
        mViewHolder.tvDesc.setText(currentListData.getIngredients());

        if (currentListData.getThumbnail() != null && !currentListData.getThumbnail().equalsIgnoreCase("")) {
            loadBitmap(mViewHolder.imageView, currentListData);
        }

        return convertView;
    }

    public void loadBitmap(ImageView imageView, Result currentListData) {
        Picasso.with(this.context).load(currentListData.getThumbnail())
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher).tag(this.context).into(imageView);
    }

    private class MyViewHolder {
        TextView tvTitle, tvDesc;
        ImageView imageView;

        public MyViewHolder(View item) {
            tvTitle = (TextView) item.findViewById(R.id.title_value);
            tvDesc = (TextView) item.findViewById(R.id.ingredients_value);
            imageView = (ImageView) item.findViewById(R.id.image);
        }
    }
}
