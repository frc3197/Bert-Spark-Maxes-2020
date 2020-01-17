/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveForwardGyro extends ParallelCommandGroup {
  /**
   * Creates a new DriveForwardGyro.
   */
  public DriveForwardGyro(DriveTrain drivetrain,DriveTrain drivetrain2electricboogaloo) {
    addCommands(new TurnToAngle(0, drivetrain), new DriveForward(24, drivetrain2electricboogaloo));
  }
}
