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
import frc.robot.commands.GetFailedPanel;
import frc.robot.commands.MiniDrive;
import frc.robot.commands.SwitchFront;
import frc.robot.commands.ToggleSolenoid;
import frc.robot.commands.autonomous.GetCargo;
import frc.robot.commands.autonomous.GetPanel;
import frc.robot.commands.autonomous.PlacePanel;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public Joystick left, right, gamepad;
  public JoystickButton switchFront, toggleRamp, releaseCargo, climb, retractPanel, miniDrive, stopAuto, climbSafety, getPanelManual, placePanelManual, getPanelAuto, placePanelAuto, getFailedPanel, climbSafety2;

  public OI() {
    left = new Joystick(PortNumbers.LEFT_JOYSTICK);
    right = new Joystick(PortNumbers.RIGHT_JOYSTICK);
    gamepad = new Joystick(PortNumbers.GAMEPAD);
    
    switchFront = new JoystickButton(right, 2);
    switchFront.whenPressed(new SwitchFront());

    toggleRamp = new JoystickButton(gamepad, 1);
    toggleRamp.whenPressed(new ToggleSolenoid(RobotMap.cargoRamp));
    releaseCargo = new JoystickButton(gamepad, 4);
    releaseCargo.whenPressed(new CargoOut());

    climb = new JoystickButton(left, 2);
    climb.whenPressed(new ContinueClimb());

    retractPanel = new JoystickButton(right, 1);
    retractPanel.whenPressed(new EnterFrame());

    miniDrive = new JoystickButton(right, 3);
    miniDrive.whileHeld(new MiniDrive());

    stopAuto = new JoystickButton(gamepad, 10);

    climbSafety = new JoystickButton(gamepad, 3);
    climbSafety2 = new JoystickButton(right, 10);

    getPanelManual = new JoystickButton(gamepad, 5);
    getPanelManual.whenPressed(new GetPanel(true));

    placePanelManual = new JoystickButton(gamepad, 7);
    placePanelManual.whenPressed(new PlacePanel(true));

    getFailedPanel = new JoystickButton(gamepad, 2);
    getFailedPanel.whenPressed(new GetFailedPanel());

    getPanelAuto = new JoystickButton(gamepad, 6);
    getPanelAuto.whenPressed(new GetPanel(false));

    placePanelAuto = new JoystickButton(gamepad, 8);
    placePanelAuto.whenPressed(new PlacePanel(false));

    //SmartDashboard Values
    /*
    SmartDashboard.putNumber("Max Speed", 133.5);
    SmartDashboard.putNumber("Max Acceleration", 72);
    SmartDashboard.putNumber("Max Jerk", 1800);
    SmartDashboard.putNumber("kP", 0);
    SmartDashboard.putNumber("kI", 0);
    SmartDashboard.putNumber("kD", 0);
    SmartDashboard.putNumber("kA", 0);
    SmartDashboard.putNumber("Turn kP", 0);
    */
    SmartDashboard.putBoolean("Teleop", true);
    SmartDashboard.putBoolean("Targets Seen", false);
    SmartDashboard.putNumber("Angle To Center", 0);
    SmartDashboard.putNumber("Offset Angle", 0);
    SmartDashboard.putNumber("Distance", 0);
    /*
    SmartDashboard.putData("Turn to Target", new TurnToTarget());
    SmartDashboard.putData("Drive to Target", new DriveToTarget());
    
    solenoid1 = new JoystickButton(gamepad, 5);
    solenoid1.whenPressed(new ToggleSolenoid(RobotMap.panelGrip));
    solenoid2 = new JoystickButton(gamepad, 7);
    solenoid2.whenPressed(new ToggleSolenoid(RobotMap.panelRaise)); */
  }
}
