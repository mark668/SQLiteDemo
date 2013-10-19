package com.sqlite.demo.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sqlite.demo.model.ImageBean;
import com.sqlite.demo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class QueryResultAdapter extends BaseAdapter{

	 private ArrayList<ImageBean> mList;
	 private LayoutInflater mLayoutInflater;
	
	 public QueryResultAdapter(Context context,ArrayList<ImageBean> list){
		 
		 mList = list;
		 mLayoutInflater = LayoutInflater.from(context);
	 }
	 
	 
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = mLayoutInflater.inflate(R.layout.cell, null);
		TextView tv = (TextView)convertView.findViewById(R.id.textView1);
		tv.setText(mList.get(position).getImageid()+" "+mList.get(position).getImagefile()+" "+mList.get(position).getImagename());
		return convertView;
	}


}
