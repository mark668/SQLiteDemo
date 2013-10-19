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
		
		
		//�ĸ���ť�ļ���
		addBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					// �����Ķ�������Activityһֱ����sqliteopenhelper�ģ������м����������Ķ�ûʲô��
					bw.insertImage(context);
					Toast.makeText(context, "�ɹ�����", Toast.LENGTH_LONG).show();
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
				// ��ǿ�͵�forѭ��
				for (ImageBean i : lb) {
					Log.i("Mark", i.getImageid() + " " + i.getImagefile() + " "
							+ i.getImagename());

				}
				Toast.makeText(context, "�ɹ���ѯ", Toast.LENGTH_LONG).show();
				//intent�ĵڶ���������һ������󣬷�������Ǳ��õ�ͦ�࣬����.class
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
				Toast.makeText(context, "�ɹ�ɾ��", Toast.LENGTH_LONG).show();
			}
		});
		
		
		updateBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				bw.update(context, "1", "imagefile", "TTT");
				Toast.makeText(context, "�ɹ��޸�", Toast.LENGTH_LONG).show();
			}
		});
		
		
		JsonBean bean = new JsonBean();
		bean.setAddress("����ˮ��");
		bean.setEmail("12444@qq.com");
		bean.setFax("1244129891");
		bean.setName("Mark");
		bean.setTelephone("23871871");
		Gson gson = new Gson();
		
		String jstring = gson.toJson(bean);
		Log.i("Mark", "Json�ַ���Ϊ"+jstring);
		
		String s1="[{'gradeId':1,'subjects':[{'id':1,'name':'����'}," +
				"{'id':2,'name':'��ѧ'},{'id':3,'name':'Ӣ��'},{'id':4,'name':'����'},{'id':5,'name':'��ѧ'}]}," +
				"{'gradeId':2,'subjects':[{'id':1,'name':'����'},{'id':2,'name':'��ѧ'},{'id':3,'name':'Ӣ��'}," +
				"{'id':4,'name':'����'},{'id':5,'name':'��ѧ'}]},{'gradeId':3,'subjects':[{'id':1,'name':'����'}," +
				"{'id':2,'name':'��ѧ'},{'id':3,'name':'Ӣ��'},{'id':4,'name':'����'},{'id':5,'name':'��ѧ'}]}," +
				"{'gradeId':4,'subjects':[{'id':1,'name':'����'},{'id':2,'name':'��ѧ'},{'id':3,'name':'Ӣ��'},{'id':4,'name':'����'}," +
				"{'id':5,'name':'��ѧ'}]},{'gradeId':5,'subjects':[{'id':1,'name':'����'},{'id':2,'name':'��ѧ'}," +
				"{'id':3,'name':'Ӣ��'},{'id':4,'name':'����'},{'id':5,'name':'��ѧ'}]}]";
		
        
		
	}

}
