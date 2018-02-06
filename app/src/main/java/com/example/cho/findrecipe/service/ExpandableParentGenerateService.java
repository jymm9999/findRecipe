package com.example.cho.findrecipe.service;

import com.example.cho.findrecipe.Dto.ExpandableParentDto;
import com.example.cho.findrecipe.Dto.Having;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cho on 2018-01-31.
 */

public class ExpandableParentGenerateService {

    private List<ExpandableParentDto> expandableParentDtoList;

    public synchronized List<ExpandableParentDto> getExpandableParentDtoList() {

        if (expandableParentDtoList == null) {
            expandableParentDtoList = new ArrayList<>();

            expandableParentDtoList.add(new ExpandableParentDto(Having.COOKING_INGREDIENTS.getName()));
            expandableParentDtoList.add(new ExpandableParentDto(Having.HAVING_COOKING_TOOLS.getName()));
            expandableParentDtoList.add(new ExpandableParentDto(Having.NON_HAVING_COOKING_TOOLS.getName()));
        }
        return expandableParentDtoList;
    }
}
