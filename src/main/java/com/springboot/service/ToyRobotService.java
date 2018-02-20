package com.springboot.service;



import helper.ToyRobotException;

public interface ToyRobotService {
	public void placeToyRobot(int x,int y,String direction) throws ToyRobotException ;
	public String eval(String inputString) throws ToyRobotException ;
	public String report();
}
