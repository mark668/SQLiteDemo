package com.sqlite.demo.constant;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.util.Log;


/**
 * 
 * @author Mark
 * ������
 */

public class CommonData {
	//����p_s_photo��ĳ���
	public static String IMAGENAME = "p_s_photo";
	public static String DELETEIMAGE = "delete from p_s_photo where imageid=";
	public static String SELECTIMAGE ="select * from p_s_photo";
	public static String SAVEIMAGE = "insert into p_s_photo(imageid,imagename,imagefile) values(imageid,imagename,imagefile)";
	}  


