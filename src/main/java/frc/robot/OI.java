/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.CargoOut;
import frc.robot.commands.ContinueClimb;
import frc.robot.commands.EnterFrame;
import frc.robot.commands.MiniDrive;
import frc.robot.commands.SwitchFront;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public Joystick left, right, gamepad;
  public JoystickButton switchFront, cargo_rocketship, cargo_cargoship, climb, retractPanel, miniDrive, stopAuto;

  public OI() {
    left = new Joystick(PortNumbers.LEFT_JOYSTICK);
    right = new Joystick(PortNumbers.RIGHT_JOYSTICK);
    gamepad = new Joystick(PortNumbers.GAMEPAD);

    switchFront = new JoystickButton(right, 2);
    switchFront.whenPressed(new SwitchFront());

    cargo_rocketship = new JoystickButton(gamepad, 1);
    cargo_rocketship.whenPressed(new CargoOut(true));
    cargo_cargoship = new JoystickButton(gamepad, 4);
    cargo_cargoship.whenPressed(new CargoOut(false));

    climb = new JoystickButton(left, 2);
    climb.whenPressed(new ContinueClimb());

    retractPanel = new JoystickButton(right, 1);
    retractPanel.whenPressed(new EnterFrame());

    miniDrive = new JoystickButton(left, 1);
    miniDrive.whileHeld(new MiniDrive());

    stopAuto = new JoystickButton(gamepad, 10);

    //SmartDashboard Values
    SmartDashboard.putNumber("Max Speed", 0);
    SmartDashboard.putNumber("Max Acceleration", 0);
    SmartDashboard.putNumber("Max Jerk", 0);
    SmartDashboard.putNumber("kP", 0);
    SmartDashboard.putNumber("kI", 0);
    SmartDashboard.putNumber("kD", 0);
    SmartDashboard.putNumber("kA", 0);
    SmartDashboard.putNumber("Turn kP", 0);
  }
}
