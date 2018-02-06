package com.example.cho.findrecipe.entity;

import com.orm.SugarRecord;

/**
 * Created by cho on 2018-01-29.
 */

public class CookingPrepare extends SugarRecord<CookingPrepare> {

    private String name;
    private String havingEnum;
    private Boolean selected;

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public String getHavingEnum() {
        return havingEnum;
    }

    public boolean isSelected() {
        return selected;
    }

    public CookingPrepare() {

    }

    public CookingPrepare(String name, String havingEnum, Boolean selected) {
        this.name = name;
        this.havingEnum = havingEnum;
        this.selected = selected;
    }
}
