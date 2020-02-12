/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;

/**
 * Defines a TakeIn object. Creates an intake parameter to be used later.
 */
public class TakeIn extends CommandBase {
  Intake intake;

  /**
   * Creates a new TakeIn.
   * @param intake Intake subsystem
   */
  public TakeIn(Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intake = intake;
    addRequirements(intake);
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
    double speed = 1;
    if(RobotContainer.getReverse()){
    intake.reverseTakeIn(speed);}
      else{intake.takeIn(speed);}
    // intake.takeIn(RobotContainer.getIntake());
  }

  /**
   * Called once the command ends or is interrupted.
   */
  @Override
  public void end(boolean interrupted) {
    intake.takeIn(0);
  }

  /**
   * Returns true when the command should end.
   */
  @Override
  public boolean isFinished() {
    return false;
  }
}
