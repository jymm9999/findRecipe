package com.example.cho.findrecipe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.cho.findrecipe.Dto.ExpandableChildDto;
import com.example.cho.findrecipe.Dto.ExpandableParentDto;
import com.example.cho.findrecipe.R;

import java.util.List;

/**
 * Created by cho on 2018-01-31.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private List<ExpandableParentDto> groupList;
    private List<List<ExpandableChildDto>> childList;
    private LayoutInflater inflater;
    private ViewHolder viewHolder;


    public ExpandableListAdapter(Context context, List<ExpandableParentDto> groupList,
                                 List<List<ExpandableChildDto>> childList){
        super();
        this.inflater = LayoutInflater.from(context);
        this.groupList = groupList;
        this.childList = childList;
    }

    @Override
    public ExpandableParentDto getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null){
            viewHolder = new ViewHolder();

            view = inflater.inflate(R.layout.main_expandable_parent, parent, false);

            TextView parentTextView = view.findViewById(R.id.expandable_parent_textview);
            parentTextView.setText(getGroup(groupPosition).getName());

            viewHolder.setParentName(parentTextView);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        ExpandableListView expandableListView = (ExpandableListView) parent;
        expandableListView.expandGroup(groupPosition);

        return view;
    }


    @Override
    public ExpandableChildDto getChild(int parentPosition, int childPosition) {
        return childList.get(parentPosition).get(childPosition);
    }

    @Override
    public int getChildrenCount(int i) {
        return childList.get(i).size();
    }


    @Override
    public long getChildId(int parentPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int parentPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null){

            viewHolder = new ViewHolder();

            view = inflater.inflate(R.layout.main_expandable_child, null);

            TextView childTextView = view.findViewById(R.id.expandable_child_textview);
            childTextView.setText(getChild(parentPosition, childPosition).getName());

            viewHolder.setChildName(childTextView);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        return view;
    }


    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int parentPosition, int childPosition) {
        return false;
    }

    private class ViewHolder {

        private TextView parentName;
        private TextView childName;

        public TextView getParentName() {
            return parentName;
        }

        public TextView getChildName() {
            return childName;
        }

        public void setParentName(TextView parentName) {
            this.parentName = parentName;
        }

        public void setChildName(TextView childName) {
            this.childName = childName;
        }
    }
}
