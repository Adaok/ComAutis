package com.lpiem_lyon1.comautis.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lpiem_lyon1.comautis.Database.LocalDataBase;
import com.lpiem_lyon1.comautis.Interface.IAdapterCommunication;
import com.lpiem_lyon1.comautis.Models.Page;
import com.lpiem_lyon1.comautis.R;

import java.util.List;

/**
 * Created by marcoloiodice on 11/01/2016.
 */
public class ListPageAdapter extends BaseAdapter{

    List<Page> mPageListItem;
    Context mContext;
    protected LocalDataBase mLocalDataBase;
    private final IAdapterCommunication listener;

    public ListPageAdapter(List<Page> mPageListItem, Context mContext, LocalDataBase mLocalDataBase, IAdapterCommunication listener) {
        this.mPageListItem = mPageListItem;
        this.mContext = mContext;
        this.mLocalDataBase = mLocalDataBase;
        this.listener = listener;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        RelativeLayout layout;
        if (convertView == null){
            layout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.choose_page_item_row, null);
        }else {
            layout = (RelativeLayout)convertView;
        }

        TextView textViewNamePage = layout.findViewById(R.id.tv_page_list_item);
        ImageView iconViewPage = layout.findViewById(R.id.icon_page_list_item);
        ImageView iconIsFavoritePage = layout.findViewById(R.id.icon_favorite_page);

        layout.findViewById(R.id.icon_favorite_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Page page = mPageListItem.get(position);
                if (page.isFavorite() == 0){
                    page.setIsFavorite(1);
                    mLocalDataBase.updatePageById(page, null);
                    Toast.makeText(mContext, R.string.toast_favorite, Toast.LENGTH_SHORT).show();
                } else if (page.isFavorite() == 1){
                    page.setIsFavorite(0);
                    mLocalDataBase.updatePageById(page, null);
                    Toast.makeText(mContext, R.string.toast_no_favorite, Toast.LENGTH_SHORT).show();
                }
                notifyDataSetChanged();
                listener.updateListMenu();
            }
        });

        textViewNamePage.setText(mPageListItem.get(position).getName());
        iconViewPage.setImageResource(R.drawable.ic_page);

        if (mPageListItem.get(position).isFavorite() == 0) {
            iconIsFavoritePage.setImageResource(R.drawable.ic_no_favorite);
        } else if (mPageListItem.get(position).isFavorite() == 1) {
            iconIsFavoritePage.setImageResource(R.drawable.ic_favorite);
        }

        return layout;
    }

}
