package com.example.cho.findrecipe.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.cho.findrecipe.Dto.BlogInfoDto;
import com.example.cho.findrecipe.R;
import com.example.cho.findrecipe.adapter.ResultPageListViewAdapter;
import com.example.cho.findrecipe.service.HtmlParseService;

import java.util.List;

/**
 * Created by cho on 2018-01-19.
 */

public class ResultPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_page);

        Intent intent = getIntent();
        String query = intent.getStringExtra("query");

        HtmlDownLoadTask htmlDownLoadTask = new HtmlDownLoadTask(this);
        htmlDownLoadTask.execute(query);

    }

    private static class HtmlDownLoadTask extends AsyncTask<String, Void, List<BlogInfoDto>> {

        private HtmlParseService htmlParseService;
        private ResultPageActivity context;

        public HtmlDownLoadTask(ResultPageActivity context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            htmlParseService = new HtmlParseService();
        }


        @Override
        protected List<BlogInfoDto> doInBackground(String... strings) {
            return htmlParseService.queryAndHtmlParse(strings[0]);
        }


        @Override
        protected void onPostExecute(List<BlogInfoDto> blogInfoDtoList) {
            super.onPostExecute(blogInfoDtoList);

            // list view build
            ListView listView = context.findViewById(R.id.result_listview);

            ResultPageListViewAdapter adapter = new ResultPageListViewAdapter(context,  blogInfoDtoList, R.layout.result_listview_component);

            listView.setAdapter(adapter);

        }
    }

}
