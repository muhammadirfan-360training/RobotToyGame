package com.springboot.service;

import org.springframework.stereotype.Service;

import com.springboot.model.Board;
import com.springboot.model.Command;
import com.springboot.model.Direction;
import com.springboot.model.Position;
import com.springboot.model.SquareBoard;
import com.springboot.model.ToyRobot;

import helper.ToyRobotException;

/** 
 * The ToyRobotServiceImpl class is the service of  
 * toy robot.It abstract the functionality of the toy robot
 */

@Service
public class ToyRobotServiceImpl implements ToyRobotService{
	/*
	@Autowired
	Board squareBoard;
	*/
	
	static Board squareBoard;
	//@Autowired
    public static ToyRobot robot;
   
    static{
    	robot = new ToyRobot();
    	squareBoard = new SquareBoard(5,5);
    }
    
    
	@Override
	public String eval(String simulate) throws ToyRobotException {
		// TODO Auto-generated method stub
		// validate command
        Command command;
        try {
            command = Command.valueOf(simulate);
        } catch (IllegalArgumentException e) {
            throw new ToyRobotException("Invalid command");
        }
       
        
		String output;
		  switch (command) {          
          case MOVE:
              Position newPosition = null;
			try {
				newPosition = robot.getPosition();
				if(newPosition != null) {
					newPosition = newPosition.getNextPosition();
				}else {
					throw new ToyRobotException("RobotNotFound");
				}
						
			} catch (Exception e) {				
				return "RobotNotFound";
			}
              if (!squareBoard.isValidPosition(newPosition))
                  output = String.valueOf(false);
              else
                  output = String.valueOf(robot.move(newPosition));
              break;
          case LEFT:
              output = String.valueOf(robot.rotateLeft());
              break;
          case RIGHT:
              output = String.valueOf(robot.rotateRight());
              break;         
          default:
              throw new ToyRobotException("Invalid command");
      }
		  return output;
	}

	@Override
	public String report() {
		if (robot.getPosition() == null) {
            return "Robot Missing";
		}

       return robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getPosition().getDirection().toString();		
	}
	

	@Override
	public void placeToyRobot(int xPosition, int yPosition, String direction) throws ToyRobotException {
		
		 if (squareBoard == null)
	            throw new ToyRobotException("Invalid squareBoard object");

	       

	        // if position is valid then assign values to fields
	        //(new Position(x, y, commandDirection)
	        // validate PLACE params
	        //String[] params;
	        int x = 0;
	        int y = 0;
	        Direction commandDirection = null;	        
            try {
                x = xPosition;
                y = yPosition;
                if(x >5 || y >5) {
                	throw new ToyRobotException("Invalid position");
                }
                commandDirection = Direction.valueOf(direction);
            } catch (Exception e) {
                throw new ToyRobotException("Invalid command");
            }
            Position position = new Position(x, y, commandDirection);            
	        robot.setPosition(position);	       
		
	}
	

}
