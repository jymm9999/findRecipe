package com.example.cho.findrecipe.service;

import com.example.cho.findrecipe.Dto.Having;
import com.example.cho.findrecipe.entity.CookingPrepare;
import com.example.cho.findrecipe.repository.CookingPrepareRepository;

import java.util.List;

/**
 * Created by cho on 2018-01-29.
 */

public class CookingPrepareService {

    private CookingPrepareRepository cookingPrepareRepository;

    public CookingPrepareService(CookingPrepareRepository cookingPrepareRepository) {
        this.cookingPrepareRepository = cookingPrepareRepository;
    }

    public void save(String input, Having having, boolean selected) {
        cookingPrepareRepository.save(input, having, selected);
    }

    public void update(CookingPrepare cookingPrepare){
        cookingPrepareRepository.update(cookingPrepare);
    }

    public List<CookingPrepare> getAll() {
        return cookingPrepareRepository.getAll();
    }

    public void delete(CookingPrepare cookingPrepare){
        cookingPrepareRepository.delete(cookingPrepare);
    }
}
