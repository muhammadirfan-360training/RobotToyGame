package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.service.ToyRobotServiceImpl;

/** 
 * The ToyRobotRestController class is the rest entry point of Spring Boot
 * it creates the all routes of the application
 */

@RestController
public class ToyRobotRestController {
	
	@Autowired
	ToyRobotServiceImpl  robotService = null;
	
	@RequestMapping(path ="/toyrobot/place/{xPosition}/{yPosition}/{direction}", method=RequestMethod.POST,produces = "application/json")
	public ResponseEntity<?> robotGameStart(@PathVariable("xPosition") String xPosition,@PathVariable("yPosition") String yPosition,@PathVariable("direction") String direction){			
		
		try {
			int x = Integer.parseInt(xPosition);
			int y = Integer.parseInt(yPosition);
			String dir = direction;			
			robotService.placeToyRobot(x, y, dir);
		}catch(Exception e) {
			
			return ResponseEntity.ok("Invalid Position"); 
		}
		return ResponseEntity.ok("Robot Placed")	;
	}

	
	@RequestMapping(path ="/toyrobot/simulate/{move}", method=RequestMethod.POST,produces = "application/json")
	public ResponseEntity<?> move(@PathVariable("move") String move){			
		
		try {			
			String dir = move;			
			String s = robotService.eval(dir);
			if(s.equals("RobotNotFound")) {
				return ResponseEntity.ok("Robot Missing"); 
			}
		}catch(Exception e) {			
			return ResponseEntity.ok("Invalid Command"); 
		}
		return ResponseEntity.ok("Robot Moved")	;
	}
	
	@RequestMapping(path ="/toyrobot/report", method=RequestMethod.GET,produces = "application/json")
	public ResponseEntity<?> report(){			
		
		String s = null;
		try {						
			s = robotService.report();
		}catch(Exception e) {			
			return ResponseEntity.ok("Invalid Command"); 
		}
		return ResponseEntity.ok(s)	;
	}
}
