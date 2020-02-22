package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

/**
 * Defines a Drive object. Creates a DriveTrain parameter to be used later.
 */
public class Drive extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  DriveTrain driveTrain;

  // double[] yValues = new double[] { driveTrain.CalcFPS(),
  // RobotContainer.tankDriveLeft() };
  /**
   * Creates a new Drive.
   * @param driveTrain The Drivetrain subsystem used by this command.
   */
  public Drive(DriveTrain driveTrain) {
    this.driveTrain = driveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);

  }

  /**
   * Called when the command is initially scheduled.
   */
  @Override
  public void initialize() {
    driveTrain.resetEncoder();
    driveTrain.resetGyro();

  }
 
  /**
   * Called every time the scheduler runs while the command is scheduled.
   */
  @Override
  public void execute() {

    /*
     * Pulls the inputs from the controller and assigns them to the variables for
     * use in the tankDrive function.
     */
    double tankR = RobotContainer.tankDriveRight();
    double tankL = RobotContainer.tankDriveLeft();

    double arcadeR = RobotContainer.arcadeDriveRotation();
    double arcadeS = RobotContainer.arcadeDriveSpeed();
    RobotContainer.pullNetworkTables();
    System.out.println("Distance From Target: " + RobotContainer.getDistanceFromTarget());

    /*
     * Executes the ltankDrive function with the variables we assigned.
     */
    //driveTrain.tankDrive(tankR * .8, tankL * .8);
     driveTrain.arcadeDrive(arcadeS * -.8, arcadeR * .8);
    
    /*
     * Puts the inputs of the Controller onto ShuffleBoard.
     */
    // System.out.println("Velocity Value " + driveTrain.getVelocity());
    // System.out.println(driveTrain.CalcFPS() + " feet per second.");\

    SmartDashboard.putNumber("Left Motor Input", tankL);
    SmartDashboard.putNumber("Right Motor Input", tankR);
    // SmartDashboard.putNumber("VelocityValue", driveTrain.CalcFPS());
    SmartDashboard.putNumber("placeholder", RobotContainer.tankDriveLeft());
    // SmartDashboard.putNumber("Velocity SetPoint", (driveTrain.getVelocityPID()));

  }

  /**
   * Called once the command ends or is interrupted.
   */
  @Override
  public void end(boolean interrupted) {
    driveTrain.resetGyro();
    driveTrain.resetEncoder();
    // driveTrain.tankDrive(0, 0);
  }

  /**
   * Returns true when the command should end.
   */
  @Override
  public boolean isFinished() {
    return false;
  }
}