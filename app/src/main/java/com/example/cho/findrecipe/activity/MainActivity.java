package com.example.cho.findrecipe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.example.cho.findrecipe.Dto.ExpandableChildDto;
import com.example.cho.findrecipe.Dto.ExpandableParentDto;
import com.example.cho.findrecipe.R;
import com.example.cho.findrecipe.adapter.ExpandableListAdapter;
import com.example.cho.findrecipe.service.ExpandableChildGenerateService;
import com.example.cho.findrecipe.service.ExpandableParentGenerateService;
import com.example.cho.findrecipe.service.QueryBuildService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableParentGenerateService expandableParentGenerateService;
    private ExpandableChildGenerateService expandableChildGenerateService;
    private QueryBuildService queryBuildService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        long kakaoId = intent.getLongExtra("kakaoId", 0L);


        expandableParentGenerateService = new ExpandableParentGenerateService();
        expandableChildGenerateService = new ExpandableChildGenerateService();
        queryBuildService = new QueryBuildService();

    }

    @Override
    protected void onResume() {
        super.onResume();

        //fetch
        List<ExpandableParentDto> expandableParentDtoList = expandableParentGenerateService.getExpandableParentDtoList();
        List<List<ExpandableChildDto>> expandableChildDtoList = expandableChildGenerateService.getExpandableChildDtoList();

        ExpandableListAdapter expandableListAdapter = new ExpandableListAdapter(this, expandableParentDtoList, expandableChildDtoList);

        ExpandableListView expandableListView = findViewById(R.id.main_expandable_listview);

        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener((parent, view, parentPosition, id) -> {

            Intent intent = new Intent(this, DetailPageActivity.class);
            intent.putExtra("having", expandableParentDtoList.get(parentPosition).getName());
            startActivity(intent);
            return false;
        });


        Button searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(view -> {

            String query = queryBuildService.buildQuery(expandableChildDtoList);
            Intent intent = new Intent(this, ResultPageActivity.class);
            intent.putExtra("query", query);
            startActivity(intent);
        });
    }
}
