/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arms;

public class MoveArms extends CommandBase {
  /**
   * Creates a new moveArms.
   */
  Arms arms;
  double gyroValueExample = 0;
  public MoveArms(Arms arms) {
    this.arms = arms;
    addRequirements(arms);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled. It's a toggle.
  @Override
  public void execute() {
    if (gyroValueExample < 50){
      arms.moveArms(.5);
    }
      else{
      arms.moveArms(0);}
    }
  
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
