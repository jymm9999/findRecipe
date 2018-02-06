package com.example.cho.findrecipe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cho.findrecipe.Dto.BlogInfoDto;
import com.example.cho.findrecipe.R;
import com.example.cho.findrecipe.activity.ResultPopupActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by cho on 2018-02-02.
 */

public class ResultPageListViewAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private List<BlogInfoDto> dataList;
    private int layout;
    private Context context;

    public ResultPageListViewAdapter(Context context, List<BlogInfoDto> dataList, int layout) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dataList = dataList;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public BlogInfoDto getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(layout, parent, false);
        }

        BlogInfoDto blogInfoDto = dataList.get(position);

        LinearLayout linearLayout = convertView.findViewById(R.id.result_href_layout);
        linearLayout.setOnClickListener(view -> {

            //TODO : 바꾸기 popup 으로

            Intent intent = new Intent(context, ResultPopupActivity.class);
            intent.putExtra("href", blogInfoDto.getHref());
            context.startActivity(intent);

//            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(blogInfoDto.getHref()));
//            context.startActivity(intent);

        });


        ImageView imageView = convertView.findViewById(R.id.result_image);
        Picasso.with(context)
                .load(blogInfoDto.getImageUrl())
                .into(imageView);

        TextView titleText = convertView.findViewById(R.id.result_title);
        titleText.setText(blogInfoDto.getContentTitle());

        TextView contentText = convertView.findViewById(R.id.result_content);
        contentText.setText(blogInfoDto.getContentInfo());

        return convertView;
    }
}
