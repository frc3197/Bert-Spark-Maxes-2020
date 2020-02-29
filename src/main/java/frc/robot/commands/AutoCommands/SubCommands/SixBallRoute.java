/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.SubCommands;

import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.RamseteCommands.Ramsete;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SixBallRoute extends SequentialCommandGroup {
  /**
   * Creates a new SixBallRoute.
   */
  public SixBallRoute(DriveTrain driveTrain,Trajectory trajectory61,Trajectory trajectory62, Elevator elevator, Hopper hopper,Shooter shooter, Hood hood, Turret turret) {
    super(new AlignShoot(shooter, hood, turret), new ElevateAuto(elevator, hopper, shooter),
        new Ramsete(driveTrain, trajectory61).andThen(() -> driveTrain.tankDriveVolts(0, 0)),
    new Ramsete(driveTrain, trajectory62).andThen(() -> driveTrain.tankDriveVolts(0, 0)),
    new AlignShoot(shooter, hood, turret), new ElevateAuto(elevator, hopper, shooter)
    );
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
  }
}
