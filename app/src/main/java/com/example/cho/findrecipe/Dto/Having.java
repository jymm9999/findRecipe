package com.example.cho.findrecipe.Dto;

/**
 * Created by cho on 2018-01-19.
 */

public enum Having {

    COOKING_INGREDIENTS("요리 재료", 0),
    HAVING_COOKING_TOOLS("있는 요리 도구", 1),
    NON_HAVING_COOKING_TOOLS("없는 요리 도구", 2);

    private String name;

    public String getName(){
        return name;
    }

    private int index;

    public int getIndex(){
        return index;
    }

    Having(String name, int index){
        this.name = name;
        this.index = index;
    }

    public static Having findByName(String name){
        for(Having selected : Having.values()){
            if(selected.getName().equals(name)){
                return selected;
            }
        }
        return null;
    }

}
