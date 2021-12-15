package com.cap.train.model;


public class Fare {
	
	private double firstAc;
	private double secondAc;
	private double thirdAc;
	private double sleeper;
	
	public Fare() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double getFirstAc() {
		return firstAc;
	}

	public void setFirstAc(double firstAc) {
		this.firstAc = firstAc;
	}

	public double getSecondAc() {
		return secondAc;
	}

	public void setSecondAc(double secondAc) {
		this.secondAc = secondAc;
	}

	public double getThirdAc() {
		return thirdAc;
	}

	public void setThirdAc(double thirdAc) {
		this.thirdAc = thirdAc;
	}

	public double getSleeper() {
		return sleeper;
	}

	public void setSleeper(double sleeper) {
		this.sleeper = sleeper;
	}

	public Fare(double firstAc, double secondAc, double thirdAc, double sleeper) {
		super();
		this.firstAc = firstAc;
		this.secondAc = secondAc;
		this.thirdAc = thirdAc;
		this.sleeper = sleeper;
	}

	@Override
	public String toString() {
		return "Fare [firstAc=" + firstAc + ", secondAc=" + secondAc + ", thirdAc=" + thirdAc + ", sleeper=" + sleeper
				+ "]";
	}
	
	
}
