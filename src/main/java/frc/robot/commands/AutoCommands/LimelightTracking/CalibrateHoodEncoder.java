/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutoCommands.LimelightTracking;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hood;

public class CalibrateHoodEncoder extends CommandBase {
  Hood hood;
  private int reverseLimit, on;
  /**
   * Creates a new CalibrateHoodEncoder.
   */
  public CalibrateHoodEncoder(Hood hood) {
    this.hood = hood;
    addRequirements(hood);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //TODO: Rewrite Encoder Calibrate
    reverseLimit = hood.hoodMotor.isRevLimitSwitchClosed();
    on = 1;
    if(reverseLimit == on){
      hood.moveHood(0);
    }
    else{
      hood.moveHood(-0.9);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hood.moveHood(0);
    hood.resetEncoderPosition();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return reverseLimit == on;
  }
}
