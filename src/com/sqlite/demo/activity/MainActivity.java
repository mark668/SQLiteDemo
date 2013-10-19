package com.sqlite.demo.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.sqlite.demo.business.ImageBusiness;
import com.sqlite.demo.dao.SqliteDao;
import com.sqlite.demo.model.ImageBean;
import com.sqlite.demo.model.JsonBean;
import com.sqlite.demo.untils.TimeUtil;
import com.sqlite.demo.R;

import android.os.Bundle;
import android.os.Parcelable;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button addBtn, queryBtn,delBtn,updateBtn;
	private ImageBusiness bw;
	private Context context;
	private ArrayList<ImageBean> lb;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		context = this;
		bw = new ImageBusiness();
		addBtn = (Button) findViewById(R.id.add);
		queryBtn = (Button) findViewById(R.id.query);
		delBtn = (Button)findViewById(R.id.delete);
		updateBtn = (Button)findViewById(R.id.update);
		
		
		//四个按钮的监听
		addBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					// 上下文对象是由Activity一直传到sqliteopenhelper的，其余中间的类的上下文都没什么用
					bw.insertImage(context);
					Toast.makeText(context, "成功加入", Toast.LENGTH_LONG).show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		queryBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				lb =(ArrayList) bw.getDbImage(context);
				// 增强型的for循环
				for (ImageBean i : lb) {
					Log.i("Mark", i.getImageid() + " " + i.getImagefile() + " "
							+ i.getImagename());

				}
				Toast.makeText(context, "成功查询", Toast.LENGTH_LONG).show();
				//intent的第二个参数是一个类对象，反射机制那边用到挺多，类名.class
				Intent intent = new Intent(MainActivity.this,QueryResultActivity.class);
				Bundle data = new Bundle();
				data.putSerializable("data", lb);
				intent.putExtras(data);
                startActivity(intent);
			}
		});
		
		
		delBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				bw.delImageItem(context,"1");
				Toast.makeText(context, "成功删除", Toast.LENGTH_LONG).show();
			}
		});
		
		
		updateBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				bw.update(context, "1", "imagefile", "TTT");
				Toast.makeText(context, "成功修改", Toast.LENGTH_LONG).show();
			}
		});
		
		
		JsonBean bean = new JsonBean();
		bean.setAddress("江南水都");
		bean.setEmail("12444@qq.com");
		bean.setFax("1244129891");
		bean.setName("Mark");
		bean.setTelephone("23871871");
		Gson gson = new Gson();
		
		String jstring = gson.toJson(bean);
		Log.i("Mark", "Json字符串为"+jstring);
		
		String s1="[{'gradeId':1,'subjects':[{'id':1,'name':'语文'}," +
				"{'id':2,'name':'数学'},{'id':3,'name':'英语'},{'id':4,'name':'物理'},{'id':5,'name':'化学'}]}," +
				"{'gradeId':2,'subjects':[{'id':1,'name':'语文'},{'id':2,'name':'数学'},{'id':3,'name':'英语'}," +
				"{'id':4,'name':'物理'},{'id':5,'name':'化学'}]},{'gradeId':3,'subjects':[{'id':1,'name':'语文'}," +
				"{'id':2,'name':'数学'},{'id':3,'name':'英语'},{'id':4,'name':'物理'},{'id':5,'name':'化学'}]}," +
				"{'gradeId':4,'subjects':[{'id':1,'name':'语文'},{'id':2,'name':'数学'},{'id':3,'name':'英语'},{'id':4,'name':'物理'}," +
				"{'id':5,'name':'化学'}]},{'gradeId':5,'subjects':[{'id':1,'name':'语文'},{'id':2,'name':'数学'}," +
				"{'id':3,'name':'英语'},{'id':4,'name':'物理'},{'id':5,'name':'化学'}]}]";
		
        
		
	}

}
