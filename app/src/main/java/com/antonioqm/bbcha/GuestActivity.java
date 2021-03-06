package com.antonioqm.bbcha;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.antonioqm.bbcha.dao.GiftDAO;
import com.antonioqm.bbcha.dao.GuestDAO;
import com.antonioqm.bbcha.model.Gift;
import com.antonioqm.bbcha.model.Guest;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GuestActivity extends AppCompatActivity {

    GuestDAO guestDAO;
    GiftDAO giftDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        guestDAO = new GuestDAO(this);
        giftDAO = new GiftDAO(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = (TextView) findViewById(R.id.guest_ed_name);
                Guest guest = new Guest();
                guest.setName(textView.getText().toString());
                guestDAO.insert(guest);
                startActivity(new Intent(view.getContext(), GuestsActivity.class));
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public String[] giftsNames(List<Gift> gifts) {
        List<String> names = new ArrayList<>();

        for (Gift gift : gifts)
            names.add(gift.getName());

        return (String[]) names.toArray();
    }
}
