package com.sqlite.demo.activity;

import java.util.ArrayList;

import com.sqlite.demo.adapter.QueryResultAdapter;
import com.sqlite.demo.model.ImageBean;
import com.sqlite.demo.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class QueryResultActivity extends Activity{

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	private ArrayList<ImageBean> lb;
	private String text;
	private ListView lv;
	private QueryResultAdapter adapter;
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultlist);
		context = this;
		//成功传递List<Object>对象，其实可以不这么做，直接在这页获取数据就行，免得传来传去
		lb =  (ArrayList<ImageBean>)getIntent().getSerializableExtra("data");
		
		adapter = new QueryResultAdapter(context, lb);
		lv = (ListView)findViewById(R.id.ListView01);
	    lv.setAdapter(adapter);
	
		
		//循环输出日志
		for (ImageBean i : lb) {
			Log.i("Mark","ResultActivity:" + i.getImageid() + " " + i.getImagefile() + " "
					+ i.getImagename());
		}
		

		
		
	}
     
  

}
