/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.I2C;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  public static Spark frontL, frontR, backL, backR, miniDrive;
  public static DoubleSolenoid climbF, climbR;
  public static Solenoid climbControl, cargoRamp, cargoHold, panelRaise, panelExtend, panelGrip;
  public static Encoder leftEnc, rightEnc;
  public static AHRS navX;
  public static DifferentialDrive drive;

  public static void init() {
    frontL = new Spark(PortNumbers.FRONT_LEFT);
    frontR = new Spark(PortNumbers.FRONT_RIGHT);
    backL = new Spark(PortNumbers.BACK_LEFT);
    backR = new Spark(PortNumbers.BACK_RIGHT);
    SpeedControllerGroup left = new SpeedControllerGroup(frontL, backL);
    SpeedControllerGroup right = new SpeedControllerGroup(frontR, backR);
    drive = new DifferentialDrive(left, right);
    drive.setSafetyEnabled(false);

    miniDrive = new Spark(PortNumbers.MINI_DRIVE);

    climbF = new DoubleSolenoid(PortNumbers.CLIMB_FRONT_FORWARD, PortNumbers.CLIMB_FRONT_REVERSE);
    climbF.set(Value.kReverse);
    climbR = new DoubleSolenoid(PortNumbers.CLIMB_BACK_FORWARD, PortNumbers.CLIMB_BACK_REVERSE);
    climbR.set(Value.kReverse);
    climbControl = new Solenoid(PortNumbers.CLIMB_AIR_CONTROL);
    climbControl.set(false);

    cargoRamp = new Solenoid(PortNumbers.CARGO_RAMP);
    cargoRamp.set(false);
    cargoHold = new Solenoid(PortNumbers.CARGO_HOLD);
    cargoHold.set(false);

    panelRaise = new Solenoid(PortNumbers.PANEL_RAISE);
    panelRaise.set(false);
    panelExtend = new Solenoid(PortNumbers.PANEL_EXTEND);
    panelExtend.set(false);
    panelGrip = new Solenoid(PortNumbers.PANEL_GRAB);
    panelGrip.set(true);

    leftEnc = new Encoder(PortNumbers.LEFT_ENCODER_A, PortNumbers.LEFT_ENCODER_B);
    rightEnc = new Encoder(PortNumbers.RIGHT_ENCODER_A, PortNumbers.RIGHT_ENCODER_B);

    navX = new AHRS(I2C.Port.kMXP);
  }
}
