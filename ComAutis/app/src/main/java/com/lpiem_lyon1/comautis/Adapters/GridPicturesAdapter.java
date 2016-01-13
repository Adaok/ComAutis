package com.lpiem_lyon1.comautis.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lpiem_lyon1.comautis.Models.Picture;
import com.lpiem_lyon1.comautis.R;

import java.util.List;

/**
 * Created by alexislp on 12/01/16.
 */
public class GridPicturesAdapter extends BaseAdapter{

    private final List<Boolean> mIsSelected;
    private final List<Picture> mListPictures;
    private final Context mContext;

    public GridPicturesAdapter(List<Picture> listPicture , List<Boolean> isSelected , Context context) {
        mListPictures = listPicture;
        mIsSelected = isSelected;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mListPictures.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout layout;
        if (convertView == null){
            layout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.grid_chosse_pictures_item , null);
        }else {
            layout = (RelativeLayout)convertView;
        }

        ImageView imageViewPicture = (ImageView) layout.findViewById(R.id.grid_item_image);
        ImageView imageSelected = (ImageView) layout.findViewById(R.id.selected_pictures);
        TextView textViewPictureName = (TextView) layout.findViewById(R.id.grid_item_name);

        imageViewPicture.setImageBitmap(mListPictures.get(position).getBitmap());
        if(mIsSelected.get(position))
            imageSelected.setVisibility(View.VISIBLE);
        textViewPictureName.setText(mListPictures.get(position).getName());

        return layout;
    }
}
