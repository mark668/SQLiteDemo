package com.sqlite.demo.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sqlite.demo.db.MyHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
 * sqliteopenhelper的各个操作的封装，这个类实例化的时候，openhelper就实例化了，在构造函数里面
 * @author Mark
 *
 */
public class SqliteDao {
	private  MyHelper dbHelper;
	
	public SqliteDao(Context context) {
		// TODO Auto-generated method stub
		dbHelper = new MyHelper(context);
	}

	public void  close() {
		if (dbHelper != null) {  
			dbHelper.close();  
		     }  
	}
	
	
	public int getCount(String sql,String type) {
		int t = 0;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cs = db.rawQuery(sql, new String[] {});
		if(cs.moveToNext()) {
			return cs.getInt(0);
		}
		return t;
	}
	
	//增，通用的一个方法，最后执行的第一个参数带占位符，后面那个就是占位符的值
	public void save(String sql,Object o) throws Exception{
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		//字符全部转化为小写字符
		sql=sql.toLowerCase();
		String insert = null, values = null, excSql = null;
		String[] valuesParam = null, insertParm = null;
		String regex = "values\\s*(.*)";
		Matcher m = Pattern.compile(regex).matcher(sql);
		while (m.find()) {
			if (!"".equals(m.group())) {
				values = m.group();
				valuesParam = values.replace("values", "").replace(" ", "")
						.replace("(", "").replace(")", "").split(",");
			}
		}
		insert = sql.replaceFirst(regex, "");
		String st = insert.substring(insert.indexOf("(") + 1, insert.indexOf(")"));
		insertParm = st.replace(" ", "").split(",");
		if (valuesParam == null||insertParm==null) {
			throw new Exception("sql values 错误");
		}
		int opc = valuesParam.length;
		Object[] objects = new Object[opc];
		StringBuilder sb = new StringBuilder("values (");
		Class c=o.getClass();
		Method[] ms=c.getMethods();
		for (int i = 0;; i++) {
			objects[i] = null;
			String getName=valuesParam[i];
			if(getName==null||getName.equals("?")){
				getName=insertParm[i];
			}
			for(int j=0;j<ms.length;j++){
				if(ms[j].getParameterTypes().length>0){
					continue;
				}
				if(ms[j].getName().replace("get", "").toLowerCase().equals(valuesParam[i].toLowerCase())){
					objects[i]=ms[j].invoke(o);
				}
			}
			if (i == opc - 1) {
				sb.append("?)");
				break;
			} else {
				sb.append("?,");
			}
		}
		excSql = insert + sb.toString();
		Log.i("Mark",excSql);
		Log.i("Mark",objects.toString());
		db.execSQL( excSql,objects);	
		
	}
	
	//删
	public void delete(String sql) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL(sql, new Integer[] {});
		db.close();
	}
	
	//改
	public void updateres(String tablename,String columnname,String value,String imageid) {
		ContentValues cv = new ContentValues();  
		cv.put(columnname, value);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.update(tablename, cv, "imageid = ?", new String[]{imageid+""});
	}
	
	//查,运用了泛型的方法，泛型方法都这么写，T写在修饰符后面
	public <T> List query(String sql, Class<T> ot) {
		// TODO Auto-generated method stub
		List<T> list = new ArrayList<T>();
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cs = db.rawQuery(sql,new String[] {});
		try {
			while (cs.moveToNext()) {
				
				//这个很明显是反射机制，下面三行
				T o = ot.newInstance();
				Class c = o.getClass();
				Method[] ms = c.getMethods();
				
				for (int i = 0; i < cs.getColumnCount(); i++) {
					String cn = cs.getColumnName(i);
					for (int j = 0; j < ms.length; j++) {
						if (ms[j].getParameterTypes().length != 1) {
							continue;
						}
						if (ms[j].getName().replace("set", "").toLowerCase().equals(cn.toLowerCase())) {
							String mt = ms[j].getParameterTypes()[0].getName();
							try {
								if (mt.equals("java.lang.String")) {
									ms[j].invoke(o, cs.getString(i));
								}
								if (mt.equals("java.lang.Integer")) {
									ms[j].invoke(o, cs.getInt(i));
								}
								if (mt.equals("int")) {
									ms[j].invoke(o, cs.getInt(i));
								}
								if (mt.equals("java.lang.Double")) {
									ms[j].invoke(o, cs.getDouble(i));
								}
								if (mt.equals("double")) {
									ms[j].invoke(o, cs.getInt(i));
								}
								
								// '''''''''''
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
						}
					}
				}
				list.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			cs.close();
			db.close();
		}
		return list;
	}
	
	
}
