package com.antonioqm.bbcha.model;

import com.lukefrozz.pedidos.models.EntidadeSimples;

/**
 * Created by AntonioQM on 23/11/16.
 */

public class Gift extends EntidadeSimples {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Gift() {
    }

    public Gift(Long id, Boolean ativo) {
        super(id, ativo);
    }

    public Gift(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Gift(Long id, Boolean ativo, String name, String description) {
        super(id, ativo);
        this.name = name;
        this.description = description;
    }
}
