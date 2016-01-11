package com.lpiem_lyon1.comautis.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lpiem_lyon1.comautis.Models.Child;
import com.lpiem_lyon1.comautis.Models.Page;
import com.lpiem_lyon1.comautis.R;

import java.util.List;

/**
 * Created by marcoloiodice on 11/01/2016.
 */
public class ListPageAdapter extends BaseAdapter{

    List<Page> mPageListItem;
    Context mContext;

    public ListPageAdapter(List<Page> mPageListItem, Context mContext) {
        this.mPageListItem = mPageListItem;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mPageListItem.size();
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
            layout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.choose_page_item_row, null);
        }else {
            layout = (RelativeLayout)convertView;
        }

        TextView textViewNamePage = (TextView) layout.findViewById(R.id.tv_page_list_item);
        ImageView iconViewPage = (ImageView) layout.findViewById(R.id.icon_page_list_item);
        ImageView iconIsFavoritePage = (ImageView) layout.findViewById(R.id.icon_favorite_page);

        textViewNamePage.setText(mPageListItem.get(position).getName());
        iconViewPage.setImageResource(R.drawable.ic_page);
        iconIsFavoritePage.setImageResource(R.drawable.ic_no_favorite);

        return layout;
    }

}
