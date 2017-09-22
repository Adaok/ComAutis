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
import com.lpiem_lyon1.comautis.Models.Child;
import com.lpiem_lyon1.comautis.R;

import java.util.List;

/**
 * Created by marcoloiodice on 07/01/2016.
 */
public class ListChildAdapter extends BaseAdapter {

    List<Child> mChildListItem;
    Context mContext;
    protected LocalDataBase mLocalDataBase;
    private final IAdapterCommunication listener;

    public ListChildAdapter(List<Child> mChildListItem, Context mContext, LocalDataBase localDataBase, IAdapterCommunication listener) {
        this.mChildListItem = mChildListItem;
        this.mContext = mContext;
        this.mLocalDataBase = localDataBase;
        this.listener = listener;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final RelativeLayout layout;
        if (convertView == null){
            layout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.choose_child_item_row, null);
        }else {
            layout = (RelativeLayout)convertView;
        }

        TextView textViewNameChild = layout.findViewById(R.id.tv_child_list_item);
        ImageView iconViewChild = layout.findViewById(R.id.icon_child_list_item);
        ImageView iconIsFavoriteChild = layout.findViewById(R.id.icon_favorite_child);

        layout.findViewById(R.id.icon_favorite_child).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Child child = mChildListItem.get(position);
                if (child.isFavorite() == 0){
                    child.setIsFavorite(1);
                    mLocalDataBase.updateChildById(child, null);
                    Toast.makeText(mContext, R.string.toast_favorite, Toast.LENGTH_SHORT).show();
                } else if (child.isFavorite() == 1){
                    child.setIsFavorite(0);
                    mLocalDataBase.updateChildById(child, null);
                    Toast.makeText(mContext, R.string.toast_no_favorite, Toast.LENGTH_SHORT).show();
                }
                notifyDataSetChanged();
                listener.updateListMenu();
            }
        });

        textViewNameChild.setText(mChildListItem.get(position).getName());
        iconViewChild.setImageResource(R.drawable.ic_child_face);

        if (mChildListItem.get(position).isFavorite() == 0) {
            iconIsFavoriteChild.setImageResource(R.drawable.ic_no_favorite);
        } else if (mChildListItem.get(position).isFavorite() == 1) {
            iconIsFavoriteChild.setImageResource(R.drawable.ic_favorite);
        }

        return layout;
    }
}
