package com.antonioqm.bbcha.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.lukefrozz.pedidos.daos.GenericDAO;

import java.util.ArrayList;
import java.util.List;

import com.antonioqm.bbcha.model.Gift;

/**
 * Created by AntonioQM on 23/11/16.
 */

public class GiftDAO extends GenericDAO<Gift> {

    public GiftDAO(Context context) {
        super(context);
    }

    @Override
    public Gift insert(Gift gift) {
        openWrite();
        ContentValues values = new ContentValues();

        values.put("name", gift.getName());
        values.put("description", gift.getDescription());

        gift.setId(db.insert("gifts", null, values));
        close();
        return gift;
    }

    @Override
    public List<Gift> retrieveDigest() {
        openRead();
        List<Gift> gifts = new ArrayList<>();
        String query = "SELECT " +
                "_id as id, " +
                "name as name, " +
                "description as descritption " +
                "FROM gifts " +
                "WHERE " +
                "active = 1 " +
                "ORDER BY name;";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            Gift gift = new Gift();
            gift.setId(cursor.getLong(0));
            gift.setName(cursor.getString(1));
            gift.setDescription(cursor.getString(2));
            gifts.add(gift);
        }

        cursor.close();
        close();
        return gifts;
    }


    @Override
    public Gift retrieveById(Long id) {
        openRead();
        Gift gift = new Gift();
        String query = "SELECT " +
                "_id as id, " +
                "name as name, " +
                "description as description " +
                "FROM gits " +
                "WHERE active = 1 AND id = ? " +
                "ORDER BY nome;";
        Cursor cursor = db.rawQuery(query, new String[]{id.toString()});

        if (cursor.moveToFirst()) {
            gift.setId(cursor.getLong(0));
            gift.setName(cursor.getString(1));
            gift.setDescription(cursor.getString(2));
        } else
            gift = null;

        cursor.close();
        close();
        return gift;
    }

    @Override
    public Gift retrieveByUUID(String uuid) {
        return null;
    }

    @Override
    public Gift update(Gift gift) {
        openWrite();
        ContentValues values = new ContentValues();

        values.put("nome", gift.getName());
        values.put("description", gift.getDescription());

        int i = db.update("produtos", values, String.format("_id = "+gift.getId().toString()), null);
        close();
        return gift;
    }

    @Override
    public Gift delete(Gift gift) {
        openWrite();
        ContentValues values = new ContentValues();

        values.put("ativo", 0);
        values.put("data_modificacao", "CURRENT_TIMESTAMP");

        int i = db.update("produtos", values, String.format("_id = %d", gift.getId()), null);
        close();
        return gift;
    }
}
