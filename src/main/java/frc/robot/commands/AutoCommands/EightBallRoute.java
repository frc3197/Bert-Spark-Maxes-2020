/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.AutoCommands.SubCommands.IntakeHopper;
import frc.robot.commands.AutoCommands.SubCommands.ShootAuto;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class EightBallRoute extends ParallelCommandGroup {
  /**
   * Creates a new EightBallRoute.
   */
  public EightBallRoute(DriveTrain driveTrain, Elevator elevator, Hopper hopper, Shooter shooter, Intake intake,
                        Trajectory trajectory1, Trajectory trajectory2, Trajectory trajectory3, 
                        Trajectory trajectory4, Trajectory trajectory5, Trajectory trajectory6) {
    super(new ShootAuto(shooter),
          new IntakeHopper(intake, hopper),
          new EightBallRamsete(driveTrain, elevator, 
                               trajectory1, trajectory2, trajectory3, 
                               trajectory4, trajectory5, trajectory6));
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
  }
}
