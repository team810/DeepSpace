/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Drive;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private boolean reverseFront = true;

  public boolean getReverseFront() {
    return reverseFront;
  }
  
  public void setReverseFront(boolean reverseFront) {
    this.reverseFront = reverseFront;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Drive());
  }

  public void tankDrive(double left, double right) {
    if (reverseFront)
      RobotMap.drive.tankDrive(-right, -left);
    else
      RobotMap.drive.tankDrive(left, right);
  }

  public void arcadeDrive(double forward, double rotate) {
    RobotMap.drive.arcadeDrive(forward, rotate);
  }
}
