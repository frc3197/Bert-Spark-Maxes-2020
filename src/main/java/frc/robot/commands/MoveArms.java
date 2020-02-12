/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arms;

/**
 * Defines a MoveArms object. Code inside creates parameters to be used later.
 */
public class MoveArms extends CommandBase {
  Arms arms;

   /**
   * Creates a new MoveArms.
   */
  public MoveArms(Arms arms) {
    this.arms = arms;
    addRequirements(arms);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  /**
   * Called when the command is initially scheduled.
   */
  @Override
  public void initialize() {
  }

  /**
   * Called every time the scheduler runs while the command is scheduled.
   * Implements limit switches for preventing overextension and toggling between up and down movement.
   */
  @Override
  public void execute() {
    double getArms = RobotContainer.getArm();
    arms.moveArms(getArms);
 
  }

  /**
   * Called once the command ends or is interrupted.
   */
  @Override
  public void end(boolean interrupted) {
    arms.moveArms(0);
  }

  /**
   * Returns true when the command should end.
   */
  @Override
  public boolean isFinished() {
    return false;
  }
}
