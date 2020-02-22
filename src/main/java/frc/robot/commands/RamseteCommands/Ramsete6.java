/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.RamseteCommands;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.AutoConstants;
import frc.robot.subsystems.DriveTrain;



public class Ramsete6{
DriveTrain driveTrain;
String trajectoryJSON;
Path trajectoryPath;
Trajectory trajectory;

public Ramsete6(DriveTrain driveTrain){
this.driveTrain = driveTrain;
trajectoryJSON = "6.wpilib.json";
try {
  trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
  trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
} catch (IOException ex) {
  DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
}

}

public RamseteCommand Ramsete1 = new RamseteCommand(
  trajectory,
  driveTrain::getPose,
  new RamseteController(),
  new SimpleMotorFeedforward(AutoConstants.kS,
                             AutoConstants.kV,
                             AutoConstants.kA),
  driveTrain.kinematics,
  driveTrain::getWheelSpeeds,
  new PIDController(AutoConstants.kP, 0, 0),
  new PIDController(AutoConstants.kP, 0, 0),
  // RamseteCommand passes volts to the callback
  driveTrain::tankDriveVolts,
  driveTrain);

  
  




}

