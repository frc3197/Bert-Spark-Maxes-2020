/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.SubCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Shooter;

public class ElevateAuto extends CommandBase {
  Elevator elevator;
  Hopper hopper;
  Shooter shooter;
  /**
   * Creates a new Elevate.
   */
  public ElevateAuto(Elevator elevator, Hopper hopper,Shooter shooter) {
    this.elevator = elevator;
    this.hopper  = hopper;
    this.shooter = shooter;
    addRequirements(elevator,hopper,shooter);
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
    shooter.shooterVelocity(RobotContainer.targetVelocity(1, 5500));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hopper.hopperFeeder(0);
    elevator.hopperElevator(0);
    shooter.shooterVelocity(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    Timer.delay(6);
    return true;
  }
}
