/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.RamseteCommands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.AutoConstants;
import frc.robot.subsystems.DriveTrain;



public class Ramsete extends RamseteCommand{
DriveTrain driveTrain;
public Ramsete(DriveTrain driveTrain, Trajectory trajectory){
  
  super(trajectory,
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
this.driveTrain = driveTrain;
}
}

