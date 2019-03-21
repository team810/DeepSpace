/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ContinueClimb extends InstantCommand {
  /**
   * Add your docs here.
   */

  Command step;

  public ContinueClimb() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    if (RobotMap.climbF.get() == Value.kReverse && RobotMap.climbR.get() == Value.kReverse) {
      if (Robot.oi.climbSafety.get() || Robot.oi.climbSafety2.get()) {
        step = new InitClimb();
        Robot.driveTrain.setReverseFront(false);
      }
    }
    else if (RobotMap.climbF.get() == Value.kForward) {
      step = new SetSolenoid(RobotMap.climbF, false);
      RobotMap.panelRaise.set(false);
      RobotMap.panelExtend.set(false);
      RobotMap.panelGrip.set(false);
    }
    else
      step = new SetSolenoid(RobotMap.climbR, false);

    if (step != null)
      step.start();
  }

}
