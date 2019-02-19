/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;

public class GrabInitialPanel extends CommandGroup {
  /**
   * Add your docs here.
   */
  public GrabInitialPanel() {
    addSequential(new SetSolenoid(RobotMap.panelGrip, true));
    addSequential(new Wait(.15));
    addSequential(new SetSolenoid(RobotMap.panelRaise, true));
    addSequential(new Wait(.35));
    addSequential(new SetSolenoid(RobotMap.panelGrip, false));
    addSequential(new Wait(.15));
    addSequential(new SetSolenoid(RobotMap.panelExtend, true));
  }
}
