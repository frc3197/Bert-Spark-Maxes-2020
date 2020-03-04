/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoCommands.SubCommands.AlignShoot;
import frc.robot.commands.AutoCommands.SubCommands.ElevateAuto;
import frc.robot.commands.AutoCommands.SubCommands.ShootAuto;
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
public class FiveBallRamsete extends SequentialCommandGroup {
  /**
   * Creates a new FiveBallRamsete.
   */
  public FiveBallRamsete(Elevator elevator, Shooter shooter,Hood hood,Turret turret,Hopper hopper, DriveTrain driveTrain, Trajectory trajectory51, Trajectory trajectory52, Trajectory trajectory53, Trajectory trajectory54) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new AlignShoot(shooter, hood, turret), new ElevateAuto(elevator, hopper, shooter),
    new Ramsete(driveTrain, trajectory51).andThen(() -> driveTrain.tankDriveVolts(0, 0)),
    new Ramsete(driveTrain, trajectory52).andThen(() -> {
                                                        driveTrain.tankDriveVolts(0, 0);
                                                        Timer.delay(1);
                                                        }),
    new Ramsete(driveTrain, trajectory53).andThen(() -> driveTrain.tankDriveVolts(0, 0)),
    new Ramsete(driveTrain, trajectory54).andThen(() -> driveTrain.tankDriveVolts(0, 0)),
    new AlignShoot(shooter, hood, turret),new ElevateAuto(elevator, hopper, shooter));
  }
}
