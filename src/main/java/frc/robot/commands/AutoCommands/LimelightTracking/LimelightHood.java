/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.LimelightTracking;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Hood;

/**
 * Defines a LimelightHood object.
 */
public class LimelightHood extends CommandBase {
  Hood hood;
  private final double TICKS_TO_DEG = 51982336/5040;

  /**
   * Creates a new LimelightHood.
   * 
   * @param hood Hood subsystem
   */
  public LimelightHood(Hood hood) {
    this.hood = hood;
    addRequirements(hood);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  /**
   * Called when the command is initially scheduled. Calibrates encoder and forces
   * Limelight to use the no hardware zoom pipeline(for accurate distance
   * calculation).
   */
  @Override
  public void initialize() {
    hood.encoderCalibrate();
    // Forces LimelightHood to use no hardware zoom pipeline
    NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("pipeline").setNumber(0);
  }

  /**
   * Called every time the scheduler runs while the command is scheduled. Runs
   * moveHoodtoAngle()
   */
  @Override
  public void execute() {
    /* Math explain time y'all
     * thetaI is calculated through trigonometry (arctan(opposite/adjacent))
     * thetaE is calculated as the error between the ideal angle (thetaI) and the starting angle (40)
     * ticksE converts degrees to ticks. Should be the ticks the encoder is at to be at thetaE. Full FLM below
     * (n ticks)(1 motorRotation)(1 gearboxesRotation) (1 bigRotation)           (360 degrees)
     *          (2048 ticks)     (343 motorRotations)  (74/14 gearboxesRotations)(1 bigRotation)
     * the while loop compares the error between ticksE and the current encoder position, and stops when
     *     the encoder position is at ticksE.
     */
    double d = RobotContainer.getDistanceFromTarget();
    double thetaI = 90 - Math.atan(80.25/d); //80.25 is the height of the shooter from the center of the power port
    double thetaE = thetaI - 40; //40 is the angle of the shooter when hitting the back limit
    double ticksE = TICKS_TO_DEG * thetaE;
    while(ticksE - hood.getEncoderPosition() > 0){ //We can assume that this number will always be positive (encoderCalibrate())
      hood.moveHood(0.5);
    }
    hood.moveHood(0);

    // TODO: Remove all this old stuff
    // hood.moveHoodtoAngle();
    // double gearMultiplier = 1 / 7 / 7 / 7 * (14 / 72) * (360);
    // double d = RobotContainer.getDistanceFromTarget();
    // double thetaL = Math.atan(80.25 / d);
    // double thetaI = 90 - thetaL;
    // thetaI -= 40;
    // double currentTicks = hood.getEncoderPosition();
    // double idealTicks = 10240 * thetaI;
    // SmartDashboard.putNumber("Target Ticks", idealTicks);
    // int hoodFwd = hood.hoodMotor.isFwdLimitSwitchClosed();

    // while (idealTicks - currentTicks > 0 && hoodFwd == 0) {
    //   hood.moveHood(0.4);
    //   SmartDashboard.putNumber("Current Ticks", currentTicks);
    //   SmartDashboard.putNumber("Ticks Error", idealTicks - currentTicks);
    //   currentTicks = hood.getEncoderPosition();

    // }
  }

  /**
   * Called once the command ends or is interrupted. Switches Limelight to
   * previous pipeline.
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
    //This should technically never happen if the math is right.
    if (hood.hoodMotor.isFwdLimitSwitchClosed() == 1) {
      return true;
    }
    return false;
  }
}
