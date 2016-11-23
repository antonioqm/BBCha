package com.antonioqm.bbcha.model;

import com.lukefrozz.pedidos.models.EntidadeSimples;

/**
 * Created by AntonioQM on 23/11/16.
 */

public class Guest extends EntidadeSimples {
    private String name;
    private Gift gift;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public Guest() {
    }

    public Guest(Long id, Boolean ativo) {
        super(id, ativo);
    }

    public Guest(String name, Gift gift) {
        this.name = name;
        this.gift = gift;
    }

    public Guest(Long id, Boolean ativo, String name, Gift gift) {
        super(id, ativo);
        this.name = name;
        this.gift = gift;
    }
}
