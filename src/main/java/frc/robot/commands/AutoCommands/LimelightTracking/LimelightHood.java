/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.LimelightTracking;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Hood;

/**
 * Defines a LimelightHood object.
 */
public class LimelightHood extends CommandBase {
  Hood hood;

  /**
   * Creates a new LimelightHood.
   * @param hood Hood subsystem
   */
  public LimelightHood(Hood hood) {
    this.hood = hood;
    addRequirements(hood);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  /**
   * Called when the command is initially scheduled.
   * Calibrates encoder and forces Limelight to use the no hardware zoom pipeline(for accurate distance calculation).
   */
  @Override
  public void initialize() {
    hood.encoderCalibrate();
    // Forces LimelightHood to use no hardware zoom pipeline
    NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("pipeline").setNumber(0);
  }

  /**
   * Called every time the scheduler runs while the command is scheduled.
   * Runs moveHoodtoAngle()
   */
  @Override
  public void execute() {
    

    // hood.moveHoodtoAngle();
    double d = RobotContainer.getDistanceFromTarget();
    double thetaL = Math.atan(80.25/d);
    double thetaI = 90 - thetaL;
    double currentTicks = hood.getEncoderPosition();
    double idealTicks = 10240 * thetaI;
    SmartDashboard.putNumber("Target Ticks", idealTicks);
    int hoodFwd = hood.hoodMotor.isFwdLimitSwitchClosed();
 

    while(idealTicks - currentTicks > 0 && hoodFwd == 0){ 
      hood.moveHood(0.4);
      SmartDashboard.putNumber("Current Ticks", currentTicks);
      SmartDashboard.putNumber("Ticks Error", idealTicks - currentTicks);
      currentTicks = hood.getEncoderPosition();

    }
  }

  /**
   * Called once the command ends or is interrupted.
   * Switches Limelight to previous pipeline.
   */
  @Override
  public void end(boolean interrupted) {
    // Changes back to hardware zoom pipeline if it was there
    // if (RobotContainer.getZoom()) {
      // NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("pipeline").setNumber(0);
    }
  

  /**
   * Returns true when the command should end.
   */
  @Override
  public boolean isFinished() {
    if(hood.hoodMotor.isFwdLimitSwitchClosed() == 1){return true;}
    return false;
  }
}
