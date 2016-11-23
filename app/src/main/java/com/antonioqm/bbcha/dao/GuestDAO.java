package com.antonioqm.bbcha.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.antonioqm.bbcha.model.Gift;
import com.antonioqm.bbcha.model.Guest;
import com.lukefrozz.pedidos.daos.GenericDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AntonioQM on 23/11/16.
 */

public class GuestDAO extends GenericDAO<Guest> {
    public GuestDAO(Context context) {
        super(context);
    }

    @Override
    public Guest insert(Guest guest) {
        openWrite();
        ContentValues values = new ContentValues();

        values.put("name", guest.getName());
        if (guest.getGift() != null)
            values.put("gift_id", guest.getGift().getId());

        guest.setId(db.insert("guests", null, values));
        close();
        return guest;
    }

    @Override
    public List<Guest> retrieveDigest() {
        openRead();
        List<Guest> guests = new ArrayList<>();
        String query = "SELECT " +
                "gu._id as gu_id, " +
                "gu.name as gu_name, " +
                "gi._id as gi_id, " +
                "gi.name as gi_name, " +
                "gi.description as gu_descritption " +
                "FROM guests as gu " +
                "LEFT JOIN gifts as gi on " +
                "gi._id = gu.gift_id " +
                "ORDER BY gu.name; ";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            Guest guest = new Guest();
            guest.setId(cursor.getLong(0));
            guest.setName(cursor.getString(1));
            Gift gift = new Gift();
            gift.setId(cursor.getLong(2));
            gift.setName(cursor.getString(3));
            gift.setDescription(cursor.getString(4));
            guest.setGift(gift);
            guests.add(guest);
        }

        cursor.close();
        close();
        return guests;
    }

    @Override
    public Guest retrieveById(Long id) {
        openRead();
        Guest guest = new Guest();
        String query = "SELECT " +
                "gu._id as gu_id, " +
                "gu.name as gu_name, " +
                "gi._id as gi_id, " +
                "gi.name as gi_name, " +
                "gi.description as gi_descritption " +
                "FROM guests as gu " +
                "INNER JOIN gifts as gi ON gi._id = gu.gift_id " +
                "WHERE " +
                "active = 1 " +
                "ORDER BY gu.name;";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            guest.setId(cursor.getLong(0));
            guest.setName(cursor.getString(1));
            Gift gift = new Gift();
            gift.setId(cursor.getLong(2));
            gift.setName(cursor.getString(3));
            gift.setDescription(cursor.getString(4));
            guest.setGift(gift);
        } else
            guest = null;

        cursor.close();
        close();
        return guest;
    }

    @Override
    public Guest retrieveByUUID(String uuid) {
        return null;
    }

    @Override
    public Guest update(Guest guest) {
        openWrite();
        ContentValues values = new ContentValues();

        values.put("nome", guest.getName());
        values.put("gift_id", guest.getGift().getId());

        int i = db.update("produtos", values, String.format("_id = "+guest.getId().toString()), null);
        close();
        return guest;
    }

    @Override
    public Guest delete(Guest guest) {
        openWrite();
        ContentValues values = new ContentValues();

        values.put("ativo", 0);

        int i = db.update("produtos", values, String.format("_id = %d", guest.getId()), null);
        close();
        return guest;
    }
}
