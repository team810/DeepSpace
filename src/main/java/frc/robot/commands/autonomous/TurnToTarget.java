/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class TurnToTarget extends Command {

  private PIDController pid;
  private double target;
  private double kP = 0,
    kI = 0,
    kD = 0;
  private boolean invalid = false;
  private int count = 0;

  public TurnToTarget() {
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    kP = SmartDashboard.getNumber("kP", 0);
    kI = SmartDashboard.getNumber("kI", 0);
    kD = SmartDashboard.getNumber("kD", 0);

    pid = new PIDController(kP, kI, kD, RobotMap.navX, a -> {});
    if (!SmartDashboard.getBoolean("Targets Seen", false)) {
      invalid = true;
      return;
    }

    RobotMap.navX.reset();
    target = SmartDashboard.getNumber("Angle To Center", 0);
    pid.setInputRange(-180, 180);
    pid.setOutputRange(-.95, .95);
    pid.setContinuous(true);
    pid.setSetpoint(target);
    pid.setAbsoluteTolerance(1);
    pid.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveTrain.arcadeDrive(0, pid.get());
    
    if (Math.abs(RobotMap.navX.getAngle() - pid.getSetpoint()) <= 3)
      count++;
    else
      count = 0;
    DriverStation.reportWarning("Count: " + count, false);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return invalid || (count > 10) || Robot.oi.stopAuto.get();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    pid.disable();
    pid.free();
    Robot.driveTrain.arcadeDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
