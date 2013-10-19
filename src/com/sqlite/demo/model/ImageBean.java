package com.sqlite.demo.model;

import java.io.Serializable;



public class ImageBean implements Serializable{
	private String imageid;
	private String imagename;
	private String imagefile;
	
	
	
	public String getImagefile() {
		return imagefile;
	}
	public void setImagefile(String imagefile) {
		this.imagefile = imagefile;
	}
	public String getImageid() {
		return imageid;
	}
	public void setImageid(String imageid) {
		this.imageid = imageid;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

}
