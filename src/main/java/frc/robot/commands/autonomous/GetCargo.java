/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class GetCargo extends CommandGroup {
  /**
   * Add your docs here.
   */
  public GetCargo() {
    addSequential(new TurnToTarget());
    addSequential(new DriveToTarget());
  }

  @Override
  protected boolean isFinished() {
    return super.isFinished() || Robot.oi.stopAuto.get();
  }

  @Override
  protected void initialize() {
    SmartDashboard.putBoolean("Teleop", false);
    super.initialize();
  }

  @Override
  protected void end() {
    SmartDashboard.putBoolean("Teleop", true);
    super.end();
  }

  @Override
  protected void interrupted() {
    SmartDashboard.putBoolean("Teleop", true);
    super.interrupted();
  }
}
