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
import frc.robot.RobotMap;
import frc.robot.commands.SetSolenoid;
import frc.robot.commands.Wait;

public class GetPanel extends CommandGroup {
  /**
   * Add your docs here.
   */
  public GetPanel(boolean manual) {
    if (!manual) {
      addSequential(new TurnToTarget());
      addSequential(new DriveToTarget());
    }
    addSequential(new SetSolenoid(RobotMap.panelRaise, true));
    addSequential(new Wait(.35));
    addSequential(new SetSolenoid(RobotMap.panelGrip, false));
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
