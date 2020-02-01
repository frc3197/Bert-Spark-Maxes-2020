/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hood;

public class MoveHood extends CommandBase {
Hood hood;
double initial = hood.getEncoderPosition();
  /**
   * Creates a new moveHood.
   */
  public MoveHood(Hood hood) {
    this.hood = hood;
    addRequirements(hood);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initial = hood.getEncoderPosition();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double source = hood.getEncoderPosition();
    while(source < initial + 100){
      hood.moveHood(.3);
  }}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hood.moveHood(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(hood.getEncoderPosition() > initial + 100)
    {
      return true;
    }
    else{
    return false;
  }}}

