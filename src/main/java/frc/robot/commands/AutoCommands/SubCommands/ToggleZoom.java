/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.SubCommands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

/**
 * Defines a ToggleZoom object.
 */
public class ToggleZoom extends CommandBase {
  /**
   * Creates a new ToggleZoom.
   */
  public ToggleZoom() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  /**
   * Called when the command is initially scheduled.
   */
  @Override
  public void initialize() {

  }

  /**
   * Called every time the scheduler runs while the command is scheduled.
   * If getZoom is true, Limelight switches to the no hardware zoom pipeline.
   * If getZoom is false, Limelight switches to the hardware zoom pipeline.
   */
  @Override
  public void execute() {
    boolean getZoom = RobotContainer.getZoom();
    // NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("pipeline").setNumber(1);
    if (getZoom) {
      NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("pipeline").setNumber(0);
      RobotContainer.toggleZoomBoolean();

    } else {
      NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("pipeline").setNumber(1);
      RobotContainer.toggleZoomBoolean();
    }
  }

  /**
   * Called once the command ends or is interrupted.
   */
  @Override
  public void end(boolean interrupted) {
  }

  /**
   * Returns true when the command should end.
   */
  @Override
  public boolean isFinished() {
    return true;
  }
}
