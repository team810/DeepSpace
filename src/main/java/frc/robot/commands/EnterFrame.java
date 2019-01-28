/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class EnterFrame extends InstantCommand {
  /**
   * Add your docs here.
   */
  public EnterFrame() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    RobotMap.cargoRamp.set(false);
    RobotMap.panelRaise.set(false);
    RobotMap.panelGrip.set(!RobotMap.panelGrip.get());
    RobotMap.panelExtend.set(!RobotMap.panelExtend.get());
  }

}
