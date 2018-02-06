package com.example.cho.findrecipe.service;

import com.example.cho.findrecipe.Dto.ExpandableChildDto;
import com.example.cho.findrecipe.Dto.Having;

import java.util.List;

/**
 * Created by cho on 2018-02-02.
 */

public class QueryBuildService {

    private final String SPACE = " ";
    private final String WITHOUT = "없이";

    public String buildQuery(List<List<ExpandableChildDto>> expandableChildDtoList) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < expandableChildDtoList.get(Having.NON_HAVING_COOKING_TOOLS.getIndex()).size(); i++) {

            ExpandableChildDto nonHavingCookingTool = expandableChildDtoList.get(Having.NON_HAVING_COOKING_TOOLS.getIndex()).get(i);

            result.append(nonHavingCookingTool.getName());
            result.append(SPACE);
        }

        if (!expandableChildDtoList.get(Having.NON_HAVING_COOKING_TOOLS.getIndex()).isEmpty()) {
            result.append(WITHOUT);
        }

        for (int i = 0; i < expandableChildDtoList.get(Having.COOKING_INGREDIENTS.getIndex()).size(); i++) {

            ExpandableChildDto cookingIngredient = expandableChildDtoList.get(Having.COOKING_INGREDIENTS.getIndex()).get(i);

            result.append(cookingIngredient.getName());
            result.append(SPACE);
        }

        for (int i = 0; i < expandableChildDtoList.get(Having.COOKING_INGREDIENTS.getIndex()).size(); i++) {

            ExpandableChildDto cookingIngredient = expandableChildDtoList.get(Having.COOKING_INGREDIENTS.getIndex()).get(i);

            result.append(cookingIngredient.getName());
            result.append(SPACE);
        }

        for (int i = 0; i < expandableChildDtoList.get(Having.HAVING_COOKING_TOOLS.getIndex()).size(); i++) {

            ExpandableChildDto havingCookingTool = expandableChildDtoList.get(Having.HAVING_COOKING_TOOLS.getIndex()).get(i);

            result.append(havingCookingTool.getName());
            result.append(SPACE);
        }

        return result.toString();
    }
}
