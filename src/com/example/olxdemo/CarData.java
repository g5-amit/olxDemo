package com.example.olxdemo;

public class CarData {

	String desc;
	int res;
	String location;
	String category;
	double price;
	
	CarData(String desc, int resid, String loc, String category, double price){
		this.desc = desc;
		this.res = resid;
		this.location =loc;
		this.category = category;
		this.price = price;
	}
}
