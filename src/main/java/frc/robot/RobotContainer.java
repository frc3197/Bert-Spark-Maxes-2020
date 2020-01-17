
package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutoCommands.Drive;
import frc.robot.commands.AutoCommands.DriveForwardGyro;
import frc.robot.subsystems.DriveTrain;

/**
 * RobotContainer is the place where Subsystems and Commands are declared. It's
 * also where buttons are mapped to the controller.
 * 
 * @author FRC3197
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  /**
   * The XboxController for the driver.
   */
  private static XboxController driver = new XboxController(0);
  JoystickButton driverA = new JoystickButton(driver, 1);
  JoystickButton driverB = new JoystickButton(driver, 2);


  //private final Command m_DriveFowardGroup = new DriveForwardGyro(angle, distance, drivetrain);
  private final ParallelCommandGroup m_DriveForwardGyro = new DriveForwardGyro(drivetrain, drivetrain2electricboogaloo);

  public final static DriveTrain drivetrain = new DriveTrain();
  public final static DriveTrain drivetrain2electricboogaloo = new DriveTrain();
  /*
   * Constructor For RobotContainer *DECLARE SUBSYSTEM DEFAULT COMMANDS HERE*
   */
  public RobotContainer() {
    drivetrain.setDefaultCommand(new Drive(drivetrain));
    drivetrain2electricboogaloo.setDefaultCommand(new Drive(drivetrain2electricboogaloo));
    SmartDashboard.putData("Gyro", (Sendable) drivetrain.gyro);
    SmartDashboard.putData("Drive Train", (Sendable) drivetrain.drive);

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    driverA.whenPressed(m_DriveForwardGyro);
  }

  /**
   * Gets the Command that will be used for Autonomous TODO: Add in the
   * SendableChooser part with ShuffleBoard.
   * 
   * @return Command used for Autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous

    return m_DriveForwardGyro;
  }

  public static double tankDriveRight() {
    SmartDashboard.putNumber("Right Joystick", driver.getY(Hand.kRight));
    return driver.getY(Hand.kRight) * Constants.motor_P;
  }

  public static double tankDriveLeft() {
    SmartDashboard.putNumber("Left Joystick", driver.getY(Hand.kLeft));
    return driver.getY(Hand.kLeft) * Constants.motor_P;
  }
}
