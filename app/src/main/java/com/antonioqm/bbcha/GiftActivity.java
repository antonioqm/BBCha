package com.antonioqm.bbcha;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.antonioqm.bbcha.dao.GiftDAO;
import com.antonioqm.bbcha.model.Gift;

public class GiftActivity extends AppCompatActivity {

    GiftDAO dao;
    TextView name, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (TextView) findViewById(R.id.gift_ed_name);
        description = (TextView) findViewById(R.id.gift_ed_description);

        dao = new GiftDAO(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gift gift = new Gift();

                gift.setName(name.getText().toString());
                gift.setDescription(name.getText().toString());
                dao.insert(gift);
                startActivity(new Intent(view.getContext(), GiftsActivity.class));
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
