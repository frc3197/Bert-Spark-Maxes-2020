
package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Running;
import frc.robot.commands.AutoCommands.Drive;
import frc.robot.commands.AutoCommands.DriveButton;
import frc.robot.commands.AutoCommands.DriveVelocity;
import frc.robot.subsystems.Climber;
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
  private static XboxController driver1 = new XboxController(0);
  private static XboxController driver2 = new XboxController(1);
  public static JoystickButton driver1A = new JoystickButton(driver1, 1);

  // public final Shooter shooter = new Shooter();
  public final Climber climber = new Climber();
  public final DriveTrain drivetrain = new DriveTrain();
  private final Command m_DriveButton = new DriveButton(drivetrain);
  private final Command m_Running = new Running();
  public final PIDCommand m_DriveSetVelocity = new DriveVelocity(drivetrain, 4);

  /*
   * Constructor For RobotContainer *DECLARE SUBSYSTEM DEFAULT COMMANDS HERE*
   */
  public RobotContainer() {
    drivetrain.setDefaultCommand(new Drive(drivetrain));

    // SmartDashboard.putData("Gyro", (Sendable) drivetrain.gyro);
    SmartDashboard.putData("Drive Train", (Sendable) drivetrain.drive);

    configureButtonBindings();
  }

  private void configureButtonBindings() {
  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous

    return m_DriveButton;
  }

  // ** DriveTrain */

  public PIDCommand getDriveSetVelocity() {
    return m_DriveSetVelocity;
  }

  public static double tankDriveRight() {
    SmartDashboard.putNumber("Right Joystick", driver1.getY(Hand.kRight));
    return driver1.getY(Hand.kRight);
  }

  public static double tankDriveLeft() {
    SmartDashboard.putNumber("Left Joystick", driver1.getY(Hand.kLeft));
    return driver1.getY(Hand.kLeft);
  }

  // ** Shooter */

  public static double shooterTest() {
    return driver1.getTriggerAxis(Hand.kRight);
  }

  // ** Hood */
  public static boolean moveClimberDown() {
    return driver2.getBumper(Hand.kLeft);
  }

  public static boolean moveClimberUp() {
    return driver2.getBumper(Hand.kRight);
  }

}
