package com.example.cho.findrecipe.Dto;

/**
 * Created by cho on 2018-01-29.
 */

public class DetailComponentDto {

    private long cookingPrepareId;
    private String name;
    private boolean selected;


    public DetailComponentDto(String name, boolean selected, long cookingPrepareId) {
        this.name = name;
        this.selected = selected;
        this.cookingPrepareId = cookingPrepareId;
    }


    public long getCookingPrepareId() {
        return cookingPrepareId;
    }

    public String getName() {

        return name;
    }

    public boolean isSelected() {
        return selected;
    }


    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
