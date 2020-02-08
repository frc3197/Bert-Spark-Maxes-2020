/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Shoot;
import frc.robot.commands.AutoCommands.LimelightTracking.AlignScript;
import frc.robot.commands.AutoCommands.SubCommands.GyroTurn;
import frc.robot.commands.AutoCommands.SubCommands.TakeInBall;
import frc.robot.subsystems.Arms;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
/**
 * Defines a HardRoute object.
 */
public class HardRoute extends SequentialCommandGroup {
  /**
   * Creates a new HardRoute.
   * @param driveTrain DriveTrain subsystem
   * @param arms Arms subsystem
   * @param intake Intake subsystem
   * @param hood Hood subsystem
   * @param shooter Shooter subsystem
   * @param hopper Hopper subsystem
   * Runs selected commands sequentially.
   */
  public HardRoute(DriveTrain driveTrain, Arms arms, Intake intake, Hood hood, Shooter shooter, Hopper hopper) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new TakeInBall(arms, intake, driveTrain, 0), new GyroTurn(driveTrain, 0), 
          new AlignScript(hood, shooter, driveTrain), new Shoot(shooter, hopper));
  }
}
