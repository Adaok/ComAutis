package com.lpiem_lyon1.comautis.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lpiem_lyon1.comautis.Models.DrawerItem;
import com.lpiem_lyon1.comautis.R;

import java.util.List;

/**
 * Created by marcoloiodice on 06/01/2016.
 */
public class DrawerAdapter extends BaseAdapter {

    List<DrawerItem> mDrawerItemList;
    Context mContext;

    public DrawerAdapter(Context mContext, List<DrawerItem> mDrawerItemList) {
        this.mContext = mContext;
        this.mDrawerItemList = mDrawerItemList;
    }

    @Override
    public int getCount(){
        return mDrawerItemList.size();
    }

    @Override
    public Object getItem(int position){
        return null;
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LinearLayout layout;
        if (convertView == null){
            layout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.drawer_item_row, null);
        }else {
            layout = (LinearLayout)convertView;
        }

        TextView textViewItemName = (TextView) layout.findViewById(R.id.tv_drawer_item);
        ImageView iconViewTtem = (ImageView) layout.findViewById(R.id.icon_drawer_item);

        textViewItemName.setText(mDrawerItemList.get(position).getName());
        iconViewTtem.setImageResource(mDrawerItemList.get(position).getIconId());

        return layout;
    }
}
