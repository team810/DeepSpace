/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class PortNumbers {

    //USB
    public static final int LEFT_JOYSTICK = 0;
    public static final int RIGHT_JOYSTICK = 1;
    public static final int GAMEPAD = 2;

    //PWM
    public static final int FRONT_LEFT = 0;
    public static final int FRONT_RIGHT = 1;
    public static final int BACK_LEFT = 2;
    public static final int BACK_RIGHT = 3;
    public static final int MINI_DRIVE = 4;

    //CAN
    public static final int MAIN_PCM = 0;
    public static final int CLIMB_PCM = 1;

    //Climbing PCM
    public static final int CLIMB_AIR_CONTROL = 0;
    public static final int CLIMB_FRONT_FORWARD = 1;
    public static final int CLIMB_FRONT_REVERSE = 2;
    public static final int CLIMB_BACK_FORWARD = 3;
    public static final int CLIMB_BACK_REVERSE = 4;

    //Main PCM
    public static final int CARGO_RAMP = 0;
    public static final int CARGO_HOLD = 1;
    public static final int PANEL_RAISE = 2;
    public static final int PANEL_EXTEND = 3;
    public static final int PANEL_GRAB = 4;

    //DIO
    public static final int LEFT_ENCODER_A = 0;
    public static final int LEFT_ENCODER_B = 1;
    public static final int RIGHT_ENCODER_A = 2;
    public static final int RIGHT_ENCODER_B = 3;

}
