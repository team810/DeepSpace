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
public class ToggleSolenoid extends InstantCommand {
  /**
   * Add your docs here.
   */
  private DoubleSolenoid dSolenoid;
  private Solenoid solenoid;
  private boolean isDouble;

  public ToggleSolenoid(DoubleSolenoid dSolenoid) {
    super();
    this.dSolenoid = dSolenoid;
    isDouble = true;
  }

  public ToggleSolenoid(Solenoid solenoid) {
    super();
    this.solenoid = solenoid;
    isDouble = false;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    if (!isDouble)
      solenoid.set(!solenoid.get());
    else
      dSolenoid.set((dSolenoid.get() == Value.kForward) ? Value.kReverse : Value.kForward);
  }

}
