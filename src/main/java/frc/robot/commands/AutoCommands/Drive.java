package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;
/**
 * An example command that uses an example subsystem.
 */
public class Drive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain driveTrain;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Drive(DriveTrain driveTrain) {
    this.driveTrain = driveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
  /*
  * Pulls the inputs from the controller and assigns them to the variables for use in the tankDrive function. 
  */
    double tankR = RobotContainer.tankDriveRight();
    double l = RobotContainer.tankDriveLeft();
  /*
  * Executes the tankDrive function with the variables we assigned.
  */
    driveTrain.tankDrive(tankR, l);
  /*
  * Puts the inputs of the Controller onto ShuffleBoard.  
  */
    SmartDashboard.putNumber("Left Motor Input", l);
    SmartDashboard.putNumber("Right Motor Input", tankR);
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.tankDrive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}