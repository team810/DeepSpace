/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class DriveToTarget extends Command {

  private boolean invalid = false;
  private double maxSpeed = 0,
    maxAccel = 0,
    maxJerk = 0,
    kP = 0,
    kI = 0,
    kD = 0,
    kA = 0,
    turnkP = 0,
    wheelBase = 22,
    wheelDiameter = 6;
  
  private EncoderFollower leftFollower, rightFollower;
  private Notifier notifier;

  public DriveToTarget() {
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (!SmartDashboard.getBoolean("Targets Seen", false)) {
      invalid = true;
      return;
    }

    Robot.driveTrain.setReverseFront(false);
    RobotMap.navX.reset();
    Waypoint[] points = new Waypoint[2];
    points[0] = new Waypoint(0, 0, 0);
    points[1] = new Waypoint(SmartDashboard.getNumber("Distance", 0), 0, Pathfinder.d2r(SmartDashboard.getNumber("Offset Angle", 0)));

    maxSpeed = SmartDashboard.getNumber("Max Speed", 0);
    maxAccel = SmartDashboard.getNumber("Max Acceleration", 0);
    maxJerk = SmartDashboard.getNumber("Max Jerk", 0);
    kP = SmartDashboard.getNumber("kP", 0);
    kI = SmartDashboard.getNumber("kI", 0);
    kD = SmartDashboard.getNumber("kD", 0);
    kA = SmartDashboard.getNumber("kA", 0);
    turnkP = SmartDashboard.getNumber("Turn kP", 0);

    Trajectory traj;
    try {
      Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_FAST, .05, maxSpeed, maxAccel, maxJerk);
      traj = Pathfinder.generate(points, config);
    } catch (Exception e) {
      DriverStation.reportError("Error generating trajectory", false);
      invalid = true;
      return;
    }

    TankModifier tank = new TankModifier(traj).modify(wheelBase);
    leftFollower = new EncoderFollower(tank.getLeftTrajectory());
    leftFollower.configureEncoder(RobotMap.leftEnc.get(), 360, wheelDiameter);
    leftFollower.configurePIDVA(kP, kI, kD, 1/maxSpeed, kA);
    rightFollower = new EncoderFollower(tank.getRightTrajectory());
    rightFollower.configureEncoder(RobotMap.rightEnc.get(), 360, wheelDiameter);
    rightFollower.configurePIDVA(kP, kI, kD, 1/maxSpeed, kA);

    notifier = new Notifier(this::followPath);
    notifier.startPeriodic(tank.getLeftTrajectory().get(0).dt);
  }

  private void followPath() {
    double l = leftFollower.calculate(RobotMap.leftEnc.get());
    double r = rightFollower.calculate(RobotMap.rightEnc.get());

    double gyro = RobotMap.navX.getAngle();
    double desiredHeading = Pathfinder.r2d(leftFollower.getHeading());
    double headingDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyro);
    double turn = turnkP * headingDifference;

    Robot.driveTrain.tankDrive(l + turn, r - turn);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return invalid || leftFollower.isFinished() || rightFollower.isFinished() || Robot.oi.stopAuto.get();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    notifier.stop();
    Robot.driveTrain.tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
