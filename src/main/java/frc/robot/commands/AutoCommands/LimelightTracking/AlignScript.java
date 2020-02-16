/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.LimelightTracking;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoCommands.SubCommands.ToggleOn;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
/**
 * Defines an AlignScript object.
 */
public class AlignScript extends SequentialCommandGroup {
  /**
   * Creates a new AlignScript.
   * 
   * @param hood       Hood subsystem
   * @param shooter    Shooter subsystem
   * @param turret     Turret subsystem
   */
  public AlignScript(Hood hood, Shooter shooter, Turret turret) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new ToggleOn(true), new Align(hood, shooter, turret), new ToggleOn(false));
  }
}
