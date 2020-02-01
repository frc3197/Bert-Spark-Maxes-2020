/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Hood;

public class MoveHood extends CommandBase {
  /**
   * Creates a new moveHood.
   */
  boolean limitSwitchPressed = false;
  Hood hood;

  double yOffset = 0;

  public MoveHood(Hood hood) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.hood = hood;
    addRequirements(hood);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    hood.resetHoodEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double source = hood.getEncoderPosition();
    System.out.println("Source: " + source);
    hood.moveHoodTicks(source, -.03, 100);
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
