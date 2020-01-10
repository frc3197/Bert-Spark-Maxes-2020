
package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.XboxController;

/**
 * RobotContainer is the place where Subsystems and Commands are declared. It's also where buttons are mapped to the controller.
 * 
 * @author Jordan Limonov
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  
  /**
   * The XboxController for the driver.
   */
  private static XboxController driver = new XboxController(0);
  /**
   * An example Subsystem. [DEPRECATED]
   */
  private final ExampleSubsystem m_autoSubsystem = new ExampleSubsystem();
  /**
   * An example Command [DEPRECATED]
   */
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_autoSubsystem);
  private final Drivetrain drivetrain = new Drivetrain();
 /*
   private void configureButtonBindings() {




  }
*/
/**
 * Gets the Command that will be used for Autonomous
 * TODO: Add in the SendableChooser part with ShuffleBoard.
 * @return Command used for Autonomous
 */
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
}