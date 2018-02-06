package com.example.cho.findrecipe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.cho.findrecipe.Dto.DetailComponentDto;
import com.example.cho.findrecipe.R;

import java.util.List;

/**
 * Created by cho on 2018-01-31.
 */

public class DetailPageListViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<DetailComponentDto> dataList;
    private int layout;

    public DetailPageListViewAdapter(Context context, List<DetailComponentDto> dataList, int layout) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dataList = dataList;
        this.layout = layout;
    }

    public List<DetailComponentDto> getChangedDataList(){
        return dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public DetailComponentDto getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(layout, parent, false);
        }

        DetailComponentDto detailComponentDto = dataList.get(position);

        CheckBox selected = convertView.findViewById(R.id.detail_component_checkbox);
        selected.setChecked(detailComponentDto.isSelected());

        selected.setOnClickListener(view -> {
            dataList.get(position).setSelected(selected.isChecked());
        });

        TextView content = convertView.findViewById(R.id.detail_component_textview);
        content.setText(detailComponentDto.getName());

        return convertView;
    }
}
