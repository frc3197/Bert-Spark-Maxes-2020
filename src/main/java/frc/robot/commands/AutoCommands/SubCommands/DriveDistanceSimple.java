/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.SubCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hopper;

public class DriveDistanceSimple extends CommandBase {
  DriveTrain driveTrain;
private double distance;
private double distanceGone;
Hopper hopper;
  /**
   * Creates a new DriveDistanceSimple.
   */
  public DriveDistanceSimple(DriveTrain driveTrain, double distance, Hopper hopper) {
    addRequirements(driveTrain, hopper);
    this.hopper = hopper;
    this.driveTrain = driveTrain;
    this.distance = distance;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTrain.resetEncoder();
    distanceGone = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    distanceGone = (driveTrain.l1TalonFX.getSelectedSensorPosition() / 2048) *7* (6*Math.PI);
    hopper.hopperFeeder(.4);
    if(distanceGone - distance != 0){
      if(distance > 0){
        driveTrain.tankDrive(0.5, 0.5);
      }else{
        driveTrain.tankDrive(-0.5, -0.5);
      }
    }else{
      driveTrain.tankDrive(0, 0);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.tankDrive(0, 0);
    hopper.hopperFeeder(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished(){
    return Math.abs(distanceGone - distance) < 10;
  }
}
