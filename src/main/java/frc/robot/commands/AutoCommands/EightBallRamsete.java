/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoCommands.SubCommands.RamseteElevateAuto;
import frc.robot.commands.RamseteCommands.Ramsete;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class EightBallRamsete extends SequentialCommandGroup {
  /**
   * Creates a new EightBallRoute.
   */
  public EightBallRamsete(DriveTrain driveTrain, Elevator elevator, Trajectory trajectory1, Trajectory trajectory2,
   Trajectory trajectory3, Trajectory trajectory4,Trajectory trajectory5, Trajectory trajectory6) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
    
    new Ramsete(driveTrain,trajectory1).andThen(() -> driveTrain.tankDriveVolts(0, 0)),
    new Ramsete(driveTrain, trajectory2).andThen(() -> driveTrain.tankDriveVolts(0, 0)),
    new Ramsete(driveTrain, trajectory3).andThen(() -> driveTrain.tankDriveVolts(0, 0)), 
    new RamseteElevateAuto(elevator),
    new Ramsete(driveTrain, trajectory4).andThen(() -> driveTrain.tankDriveVolts(0, 0)),
    new Ramsete(driveTrain, trajectory5).andThen(() -> driveTrain.tankDriveVolts(0, 0)),
    new Ramsete(driveTrain, trajectory6).andThen(() -> driveTrain.tankDriveVolts(0, 0)),
    new RamseteElevateAuto(elevator));
  }
}
