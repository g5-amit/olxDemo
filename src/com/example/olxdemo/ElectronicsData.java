package com.example.olxdemo;

public class ElectronicsData {

	String desc;
	int res;
	String location;
	String category;
	double price;
	
	ElectronicsData(String desc, int resid, String loc, String category, double price){
		this.desc = desc;
		this.res = resid;
		this.location =loc;
		this.category = category;
		this.price = price;
	}
}
