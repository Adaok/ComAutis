package com.lpiem_lyon1.comautis.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lpiem_lyon1.comautis.Models.Child;
import com.lpiem_lyon1.comautis.R;

import java.util.List;

/**
 * Created by marcoloiodice on 07/01/2016.
 */
public class ListChildAdapter extends BaseAdapter {

    List<Child> mChildListItem;
    Context mContext;

    public ListChildAdapter(List<Child> mChildListItem, Context mContext) {
        this.mChildListItem = mChildListItem;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mChildListItem.size();
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
            layout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.choose_child_item_row, null);
        }else {
            layout = (RelativeLayout)convertView;
        }

        TextView textViewNameChild = (TextView) layout.findViewById(R.id.tv_child_list_item);
        ImageView iconViewChild = (ImageView) layout.findViewById(R.id.icon_child_list_item);
        ImageView iconIsFavoriteChild = (ImageView) layout.findViewById(R.id.icon_favorite_child);

        textViewNameChild.setText(mChildListItem.get(position).getName());
        iconViewChild.setImageResource(R.drawable.ic_child_face);
        iconIsFavoriteChild.setImageResource(R.drawable.ic_no_favorite);

        return layout;
    }
}
