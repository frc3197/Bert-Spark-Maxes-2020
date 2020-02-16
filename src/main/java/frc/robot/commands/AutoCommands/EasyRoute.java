/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoCommands.LimelightTracking.TrackLimelightTurn;
import frc.robot.commands.AutoCommands.SubCommands.DriveDistanceSimple;
import frc.robot.commands.AutoCommands.SubCommands.ElevateAuto;
import frc.robot.commands.AutoCommands.SubCommands.ShootAuto;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
/**
 * Defines an EasyRoute object.
 */
public class EasyRoute extends SequentialCommandGroup {

  /**
   * Creates a new EasyRoute.
   * @param hood Hood subsystem
   * @param shooter Shooter subsystem
   * @param driveTrain DriveTrain subsystem
   * @param hopper Hopper subsystem
   * Runs selected commands sequentially.
   */
  public EasyRoute(Hood hood, Shooter shooter, DriveTrain driveTrain, Hopper hopper, Turret turret, Elevator elevator) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    // super(new DriveDistanceSimple(driveTrain));
    super(new TrackLimelightTurn(turret), new ShootAuto(shooter, hopper),new ElevateAuto(elevator,hopper,shooter), new DriveDistanceSimple(driveTrain));
  }
}
