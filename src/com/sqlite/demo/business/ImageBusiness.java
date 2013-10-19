package com.sqlite.demo.business;

import java.util.ArrayList;
import java.util.List;

import com.sqlite.demo.constant.CommonData;
import com.sqlite.demo.dao.SqliteDao;
import com.sqlite.demo.model.ImageBean;

import android.content.Context;

public class ImageBusiness {
	
	
	public void update(Context context,String imageid,String columnname,String value){
		SqliteDao obSqliteDao = new SqliteDao(context);
		obSqliteDao.updateres(CommonData.IMAGENAME, columnname,value, imageid);
		
		
	}
	
	public void insertImage(Context context) throws Exception {
		
		ImageBean imBean = new ImageBean();
		imBean.setImageid("1");
		imBean.setImagefile("���");
		imBean.setImagename("�������Ӧ��û����");
		SqliteDao obSqliteDao = new SqliteDao(context);
		//ʵ�������������Ҫ�����ݿ��ֶ�������ͬ
		obSqliteDao.save(CommonData.SAVEIMAGE, imBean);

	}

	public Boolean delImageItem(Context context, String imageid) {
		try {
			SqliteDao obSqliteDao = new SqliteDao(context);
			obSqliteDao.delete(CommonData.DELETEIMAGE + imageid);
			//dao�ص���dbhelper�ص�
			obSqliteDao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ImageBean> getDbImage(Context context) {
		List<ImageBean> list = new ArrayList<ImageBean>();
		try {
			SqliteDao obDbService = new SqliteDao(context);
			list = obDbService.query(CommonData.SELECTIMAGE, ImageBean.class);
			obDbService.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
