package com.example.cho.findrecipe.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cho.findrecipe.Dto.DetailComponentDto;
import com.example.cho.findrecipe.Dto.Having;
import com.example.cho.findrecipe.R;
import com.example.cho.findrecipe.adapter.DetailPageListViewAdapter;
import com.example.cho.findrecipe.entity.CookingPrepare;
import com.example.cho.findrecipe.repository.CookingPrepareRepository;
import com.example.cho.findrecipe.service.CookingPrepareService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cho on 2018-01-19.
 */

public class DetailPageActivity extends AppCompatActivity {

    private Having selected;
    private CookingPrepareService cookingPrepareService;
    private DetailPageListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);

        cookingPrepareService = new CookingPrepareService(new CookingPrepareRepository());

        Intent intent = getIntent();
        String message = intent.getStringExtra("having");

        TextView title = findViewById(R.id.detail_page_title);
        title.setText(message);

        selected = Having.findByName(message);

        Button addButton = findViewById(R.id.detail_add_button);
        registerAddButtonClickListener(addButton, selected, this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView listView = findViewById(R.id.detail_listview);

        //fetch
        List<CookingPrepare> sugarRecords = cookingPrepareService.getAll();

        List<DetailComponentDto> detailComponentDtoList = sugarRecords.stream()
                .filter(cookingPrepare -> cookingPrepare.getHavingEnum().equals(selected.getName()))
                .map(cookingPrepare -> new DetailComponentDto(cookingPrepare.getName(), cookingPrepare.isSelected(), cookingPrepare.getId()))
                .collect(Collectors.toList());

        adapter = new DetailPageListViewAdapter(this,  detailComponentDtoList, R.layout.detail_listview_component);

        listView.setAdapter(adapter);


        Button selectButton = findViewById(R.id.detail_select_button);

        selectButton.setOnClickListener(view -> {

            List<DetailComponentDto> changedList = adapter.getChangedDataList();

            for(DetailComponentDto detailComponentDto : changedList){
                CookingPrepare updateTarget = sugarRecords.stream().filter(cookingPrepare -> cookingPrepare.getId().equals(detailComponentDto.getCookingPrepareId())).findFirst().get();
                updateTarget.setSelected(detailComponentDto.isSelected());
                cookingPrepareService.update(updateTarget);
            }

            finish();
        });

        Button removeButton = findViewById(R.id.detail_remove_button);

        removeButton.setOnClickListener(view -> {

            List<DetailComponentDto> changedList = adapter.getChangedDataList();
            List<DetailComponentDto> removeTargetList = changedList.stream().filter(DetailComponentDto::isSelected).collect(Collectors.toList());

            for(DetailComponentDto detailComponentDto : removeTargetList){
                CookingPrepare removeTarget = sugarRecords.stream().filter(cookingPrepare -> cookingPrepare.getId().equals(detailComponentDto.getCookingPrepareId())).findFirst().get();
                cookingPrepareService.delete(removeTarget);
            }

            onResume();
        });

    }

    private void registerAddButtonClickListener(Button addButton, Having selected, Context context) {

        addButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, RegisterActivity.class);
            intent.putExtra("having", selected.getName());
            startActivity(intent);
        });
    }
}
