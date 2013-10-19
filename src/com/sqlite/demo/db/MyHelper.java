package com.sqlite.demo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

//SQLiteOpenHelper是一个抽象类，所以抽象方法必须被实现
public class MyHelper extends SQLiteOpenHelper{

	private static final String DBNAME = "sqlitedemo.db";
	private static final int VERSION = 1;
	
	public MyHelper(Context context) {
		super(context, DBNAME, null, VERSION);
	}
	
	public MyHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table Mark1 (id integer primary key autoincrement, taskid varchar, "
				+ "time varchar, msgtype varchar, rsname varchar, type varchar,status varchar,flags varchar)");
		
		db.execSQL("create table Mark2 (id integer primary key autoincrement, taskid varchar, "
				+ "rsid integer, powerupplyline varchar, drivings varchar, rsname varchar)");
		
		db.execSQL("create table Mark3 (id integer primary key autoincrement, oid integer, title varchar, webtime varchar)");
		db.execSQL("create table p_s_photo (id integer primary key autoincrement,imageid integer,type integer,time varchar, imagefile varchar, "
				+ "imagename vharchar)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
