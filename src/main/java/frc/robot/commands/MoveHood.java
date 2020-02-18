/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Hood;

/**
 * Defines a MoveHood object. Creates Hood parameter to be used later.
 */
public class MoveHood extends CommandBase {
  Hood hood;

  /**
   * Creates a new MoveHood.
   * 
   * @param hood Hood subsystem
   */
  public MoveHood(Hood hood) {
    this.hood = hood;
    addRequirements(hood);
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
   */
  @Override
  public void execute() {
    int hoodFwd = hood.hoodMotor.isFwdLimitSwitchClosed();
    // String angle = ""
    // SmartDashboard.putNumber("Angle", value)
    System.out.println("hoodFWD" + hoodFwd);
    hood.moveHood(RobotContainer.getHoodManual() * .5);
  }

  /**
   * Called once the command ends or is interrupted.
   */
  @Override
  public void end(boolean interrupted) {
    hood.moveHood(0);
  }

  /**
   * Returns true when the command should end.
   */
  @Override
  public boolean isFinished() {
    return false;
  }
}
