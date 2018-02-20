package com.springboot;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.service.ToyRobotServiceImpl;

import helper.ToyRobotException;

/** 
 * The GameTest class is the test class of the
 * toy robot.It test the required functionality of the robot
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameTest {
	
	@Autowired	
	ToyRobotServiceImpl toyService = null;
	
	@Test
	public void robotNotFound(){		
		try {			
			toyService.eval("MOVE");			
			String s = report();
			assertTrue("Robot Missing", true);
		} catch (ToyRobotException e) {
			assertFalse("Robot Missing", false);
		}	
	}
	
	@Test
	public void placeNorthMove(){		
		try {
			toyService.placeToyRobot(0, 0, "NORTH");
			toyService.eval("MOVE");
			String report = report();
			assertTrue("0,1,NORTH", report.equals("0,1,NORTH"));
		} catch (ToyRobotException e) {			
			assertFalse("Robot Missing", false);
		}	
	}

	@Test
	public void placeNorthMoveLeft(){		
		try {
			toyService.placeToyRobot(0, 0, "NORTH");
			toyService.eval("LEFT");
			String report = report();
			assertTrue("0,0,WEST", report.equals("0,0,WEST"));
		} catch (ToyRobotException e) {			
			assertFalse("Robot Missing", false);
		}	
	}
	
	@Test
	public void placeEast(){		
		try {
			toyService.placeToyRobot(1, 2, "EAST");
			toyService.eval("MOVE");
			toyService.eval("MOVE");
			toyService.eval("LEFT");
			toyService.eval("MOVE");
			String report = report();
			assertTrue("3,3,NORTH", report.equals("3,3,NORTH"));
		} catch (ToyRobotException e) {			
			assertFalse("Robot Missing", false);
		}	
	}
	
	public String report(){		
		String  report = toyService.report();
		return report;
		
	}
	

}
	

