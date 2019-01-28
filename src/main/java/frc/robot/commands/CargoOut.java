/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;

public class CargoOut extends CommandGroup {
  /**
   * Add your docs here.
   */
  public CargoOut(boolean rocketShip) {
    addSequential(new SetSolenoid(RobotMap.cargoRamp, !rocketShip));
    addSequential(new Wait(.2));
    addSequential(new SetSolenoid(RobotMap.cargoHold, false));
    addSequential(new Wait(5));
    addSequential(new SetSolenoid(RobotMap.cargoHold, true));
    addSequential(new SetSolenoid(RobotMap.cargoRamp, false));
  }
}
