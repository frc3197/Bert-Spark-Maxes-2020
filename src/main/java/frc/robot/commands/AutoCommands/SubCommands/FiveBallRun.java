/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.SubCommands;

import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.TakeIn;
import frc.robot.commands.AutoCommands.FiveBallRamsete;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class FiveBallRun extends ParallelCommandGroup {
  /**
   * Creates a new FiveBallRun.
   */
  public FiveBallRun(Hood hood, Hopper hopper,Elevator elevator, Intake intake, Turret turret, DriveTrain driveTrain, Trajectory trajectory51, Trajectory trajectory52, Trajectory trajectory53, Trajectory trajectory54, Shooter shooter) {
    super(new FiveBallRamsete(elevator, shooter, hood, turret, hopper, driveTrain, trajectory51, trajectory52, trajectory53, trajectory54), new TakeIn(intake));
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
  }
}
