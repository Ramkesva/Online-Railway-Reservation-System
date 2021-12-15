package com.cap.dto;



public class Train {
	
	
	private Integer  trainNum;
	private String  trainName;
	private String startLoc;
	private String endLoc;
	private Integer seat;
	private String time;
	
	
	public Train() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getTrainNum() {
		return trainNum;
	}
	public void setTrainNum(Integer trainNum) {
		this.trainNum = trainNum;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getStartLoc() {
		return startLoc;
	}
	public void setStartLoc(String startLoc) {
		this.startLoc = startLoc;
	}
	public String getEndLoc() {
		return endLoc;
	}
	public void setEndLoc(String endLoc) {
		this.endLoc = endLoc;
	}
	public Integer getSeat() {
		return seat;
	}
	public void setSeat(Integer seat) {
		this.seat = seat;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public Train(Integer trainNum, String trainName, String startLoc, String endLoc, Integer seat, String time) {
		super();
		this.trainNum = trainNum;
		this.trainName = trainName;
		this.startLoc = startLoc;
		this.endLoc = endLoc;
		this.seat = seat;
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "Train [trainNum=" + trainNum + ", trainName=" + trainName + ", startLoc=" + startLoc + ", endLoc="
				+ endLoc + ", seat=" + seat + ", time=" + time + "]";
	}

	
	
}