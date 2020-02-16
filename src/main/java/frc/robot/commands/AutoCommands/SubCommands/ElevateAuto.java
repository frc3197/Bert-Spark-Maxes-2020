/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.SubCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.Feed;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Hopper;

public class ElevateAuto extends CommandBase {
  Elevator elevator;
  Hopper hopper;
  /**
   * Creates a new Elevate.
   */
  public ElevateAuto(Elevator elevator, Hopper hopper) {
    this.elevator = elevator;
    this.hopper  = hopper;
    addRequirements(elevator,hopper);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    hopper.hopperFeeder(.4);
    elevator.hopperElevator(0.4);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    Timer.delay(5);
    return true;
  }
}
