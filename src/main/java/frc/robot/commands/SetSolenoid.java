/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Add your docs here.
 */
public class SetSolenoid extends InstantCommand {
  /**
   * Add your docs here.
   */

  boolean isDouble, value;
  Solenoid solenoid;
  DoubleSolenoid dSolenoid;

  public SetSolenoid(Solenoid solenoid, boolean value) {
    super();
    isDouble = false;
    this.solenoid = solenoid;
    this.value = value;
  }

  public SetSolenoid(DoubleSolenoid solenoid, boolean value) {
    super();
    isDouble = true;
    this.dSolenoid = solenoid;
    this.value = value;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    if (isDouble)
      dSolenoid.set((value) ? Value.kForward : Value.kReverse);
    else
      solenoid.set(value);
  }

}
