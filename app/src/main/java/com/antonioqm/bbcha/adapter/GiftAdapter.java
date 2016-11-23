package com.antonioqm.bbcha.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import com.antonioqm.bbcha.R;
import com.antonioqm.bbcha.model.Gift;

/**
 * Created by AntonioQM on 23/11/16.
 */

public class GiftAdapter extends ArrayAdapter<Gift> {

    private static class ViewHolder {
        TextView name;
        TextView description;
    }

    public GiftAdapter(Context context, List<Gift> gifts) {
        super(context, R.layout.item_gift, gifts);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Gift gift = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_gift, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.gift_name);
            viewHolder.description = (TextView) convertView.findViewById(R.id.gift_descrition);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(gift.getName());
        viewHolder.description.setText(gift.getDescription());
        return convertView;
    }
}
