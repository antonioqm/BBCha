package com.antonioqm.bbcha.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.antonioqm.bbcha.R;
import com.antonioqm.bbcha.model.Guest;

import java.util.List;

/**
 * Created by AntonioQM on 23/11/16.
 */

public class GuestAdapter extends ArrayAdapter<Guest> {


    public static class ViewHolder {
        TextView guestName;
        TextView giftName;
    }

    public GuestAdapter(Context context, List<Guest> guests) {
        super(context, R.layout.guests_list, guests);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Guest guest = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_guest, parent, false);
            viewHolder.guestName = (TextView) convertView.findViewById(R.id.guest_name);
            viewHolder.giftName = (TextView) convertView.findViewById(R.id.guests_gift);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.guestName.setText(guest.getName());
        viewHolder.giftName.setText(guest.getGift().getName());
        return  convertView;
    }
}