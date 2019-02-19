/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.DigitalInput;
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
  public static Solenoid cargoRamp, cargoHold, panelRaise, panelExtend, panelGrip;
  public static Encoder leftEnc, rightEnc;
  public static AHRS navX;
  public static DifferentialDrive drive;
  public static DigitalInput limitSwitch;

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
    miniDrive.setSafetyEnabled(false);

    climbF = new DoubleSolenoid(PortNumbers.CLIMB_PCM, PortNumbers.CLIMB_FRONT_FORWARD, PortNumbers.CLIMB_FRONT_REVERSE);
    climbF.set(Value.kReverse);
    climbR = new DoubleSolenoid(PortNumbers.CLIMB_PCM, PortNumbers.CLIMB_BACK_FORWARD, PortNumbers.CLIMB_BACK_REVERSE);
    climbR.set(Value.kReverse);

    cargoRamp = new Solenoid(PortNumbers.MAIN_PCM, PortNumbers.CARGO_RAMP);
    cargoRamp.set(false);
    cargoHold = new Solenoid(PortNumbers.MAIN_PCM, PortNumbers.CARGO_HOLD);
    cargoHold.set(false);

    panelRaise = new Solenoid(PortNumbers.MAIN_PCM, PortNumbers.PANEL_RAISE);
    panelRaise.set(false);
    panelExtend = new Solenoid(PortNumbers.MAIN_PCM, PortNumbers.PANEL_EXTEND);
    panelExtend.set(false);
    panelGrip = new Solenoid(PortNumbers.MAIN_PCM, PortNumbers.PANEL_GRAB);
    panelGrip.set(false);

    leftEnc = new Encoder(PortNumbers.LEFT_ENCODER_A, PortNumbers.LEFT_ENCODER_B, false, CounterBase.EncodingType.k4X);
    rightEnc = new Encoder(PortNumbers.RIGHT_ENCODER_A, PortNumbers.RIGHT_ENCODER_B, true, CounterBase.EncodingType.k4X);
    AutoPutData.addNumber("Left Encoder", leftEnc::get);
    AutoPutData.addNumber("Right Encoder", rightEnc::get);

    navX = new AHRS(I2C.Port.kMXP);
    AutoPutData.addNumber("NavX", navX::getAngle);

    //limitSwitch = new DigitalInput(PortNumbers.LIMIT_SWITCH);
  }
}
