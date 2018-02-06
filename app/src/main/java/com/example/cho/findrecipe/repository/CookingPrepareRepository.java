package com.example.cho.findrecipe.repository;

import com.example.cho.findrecipe.Dto.Having;
import com.example.cho.findrecipe.entity.CookingPrepare;

import java.util.List;

/**
 * Created by cho on 2018-01-29.
 */

public class CookingPrepareRepository {


    public void save(String input, Having having, boolean selected) {
        CookingPrepare cookingPrepare = new CookingPrepare(input, having.getName(), selected);
        cookingPrepare.save();
    }

    public List<CookingPrepare> getAll() {
        return CookingPrepare.listAll(CookingPrepare.class);
    }

    public void update(CookingPrepare cookingPrepare){
        cookingPrepare.save();
    }

    public void delete(CookingPrepare cookingPrepare){
        cookingPrepare.delete();
    }
}
