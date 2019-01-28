/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;

public class InitClimb extends CommandGroup {
  /**
   * Add your docs here.
   */
  public InitClimb() {
    addSequential(new SetSolenoid(RobotMap.climbControl, true));
    addSequential(new Wait(.1));
    addSequential(new SetSolenoid(RobotMap.climbF, true));
    addParallel(new SetSolenoid(RobotMap.climbR, true));
    addSequential(new Wait(.1));
    addSequential(new SetSolenoid(RobotMap.climbControl, false));
  }
}