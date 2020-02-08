/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

/**
 * Defines a Climb object. Creates values for later parameters.
 */
public class Climb extends CommandBase {
  Climber climber;
  boolean Up;

  /**
   * Creates a new Climb.
   * @param climber Subsytem pulled in to use here.
   * @param Up Boolean relating to going up.
   */
  public Climb(Climber climber, boolean Up) {
    this.Up = Up;
    this.climber = climber;
    addRequirements(climber);
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
   */
  @Override
  public void execute() {
    if (Up) {
      climber.setTelescopeMotor(0.3);
    } else {
      climber.setTelescopeMotor(-0.3);
    }
  }

  /**
   * Called once the command ends or is interrupted.
   */
  @Override
  public void end(boolean interrupted) {
    climber.setTelescopeMotor(0);
  }

  /**
   * Returns true when the command should end.
   */
  @Override
  public boolean isFinished() {
    return false;
  }
}
