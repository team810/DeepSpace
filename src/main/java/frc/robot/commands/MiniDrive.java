/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class MiniDrive extends Command {
  public MiniDrive() {
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /*if (!RobotMap.limitSwitch.get())
      return; */
    double mini = Robot.oi.right.getRawAxis(1);
    double main = -Robot.oi.left.getRawAxis(1);

    if (Math.abs(main) < .2)
      main = 0;
    if (Math.abs(mini) < .2)
      mini = 0;

    mini *= .25;
    main *= .75;
    RobotMap.miniDrive.set(mini);
    Robot.driveTrain.arcadeDrive(mini * -2.1, 0);
    //DriverStation.reportWarning("Mini drive: " + mini + " Main drive: " + main, false);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.arcadeDrive(0, 0);
    RobotMap.miniDrive.set(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
