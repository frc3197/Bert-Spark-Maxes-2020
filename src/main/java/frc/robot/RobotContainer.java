
package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Running;
import frc.robot.commands.AutoCommands.Drive;
import frc.robot.commands.AutoCommands.DriveButton;
import frc.robot.commands.AutoCommands.Scrub;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;

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
  public static JoystickButton driverA = new JoystickButton(driver, 1);
  public static JoystickButton driverX = new JoystickButton(driver, 3);
  /**
   * An example Subsystem. [DEPRECATED]
   */
  private final ExampleSubsystem m_autoSubsystem = new ExampleSubsystem();

  /**
   * An example Command [DEPRECATED]
   */

  // public final Shooter shooter = new Shooter();
  public final DriveTrain drivetrain = new DriveTrain();
  public final ControlPanel controlPanel = new ControlPanel();
  private final Command m_DriveButton = new DriveButton(drivetrain);
  private final Command m_Running = new Running();
  private final Command m_Scrub = new Scrub(controlPanel);
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_autoSubsystem);
  // public static final NetworkTableInstance ntInst =
  // NetworkTableInstance.getDefault();

  /*
   * Constructor For RobotContainer *DECLARE SUBSYSTEM DEFAULT COMMANDS HERE*
   */
  public RobotContainer() {
    drivetrain.setDefaultCommand(new Drive(drivetrain));
    // shooter.setDefaultCommand(new ShooterTest(shooter));
    configureButtonBindings();
  }

  private void configureButtonBindings() {

    driverA.whenPressed(m_Running);
    driverX.whenPressed(m_Scrub);
  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }

  public static double tankDriveRight() {
    SmartDashboard.putNumber("Right Joystick", driver.getY(Hand.kRight));
    return driver.getY(Hand.kRight);
  }

  public static double tankDriveLeft() {
    SmartDashboard.putNumber("Left Joystick", driver.getY(Hand.kLeft));
    return driver.getY(Hand.kLeft);
  }

  public static double shooterTest() {
    return driver.getTriggerAxis(Hand.kRight);
  }
}
