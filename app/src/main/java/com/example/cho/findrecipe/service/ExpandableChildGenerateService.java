package com.example.cho.findrecipe.service;

import com.example.cho.findrecipe.Dto.ExpandableChildDto;
import com.example.cho.findrecipe.Dto.Having;
import com.example.cho.findrecipe.entity.CookingPrepare;
import com.example.cho.findrecipe.repository.CookingPrepareRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cho on 2018-01-31.
 */

public class ExpandableChildGenerateService {

    private CookingPrepareService cookingPrepareService;

    public ExpandableChildGenerateService() {
        this.cookingPrepareService = new CookingPrepareService(new CookingPrepareRepository());
    }

    public List<List<ExpandableChildDto>> getExpandableChildDtoList() {

        List<CookingPrepare> cookingPrepareList = cookingPrepareService.getAll();

        List<ExpandableChildDto> cookingIngredientList = filterExpandableChildDtoList(cookingPrepareList, Having.COOKING_INGREDIENTS);
        List<ExpandableChildDto> havingCookingToolList = filterExpandableChildDtoList(cookingPrepareList, Having.HAVING_COOKING_TOOLS);
        List<ExpandableChildDto> nonHavingCookingToolList = filterExpandableChildDtoList(cookingPrepareList, Having.NON_HAVING_COOKING_TOOLS);

        List<List<ExpandableChildDto>> expandableChildDtoList = new ArrayList<>();

        expandableChildDtoList.add(cookingIngredientList);
        expandableChildDtoList.add(havingCookingToolList);
        expandableChildDtoList.add(nonHavingCookingToolList);

        return expandableChildDtoList;
    }

    private List<ExpandableChildDto> filterExpandableChildDtoList(List<CookingPrepare> cookingPrepareList, Having having) {
        return cookingPrepareList.stream()
                .filter(cookingPrepare -> cookingPrepare.getHavingEnum().equals(having.getName()))
                .filter(CookingPrepare::isSelected)
                .map(cookingPrepare -> new ExpandableChildDto(cookingPrepare.getName()))
                .collect(Collectors.toList());
    }
}
