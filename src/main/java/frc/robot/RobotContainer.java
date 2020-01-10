
package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.XboxController;

 public class RobotContainer {
  private static XboxController driver = new XboxController(0);
  private final ExampleSubsystem m_autoSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_autoSubsystem);

 /*
   private void configureButtonBindings() {




  }
*/

public Command getAutonomousCommand() {
  // An ExampleCommand will run in autonomous
  return m_autoCommand;
}


  public static double tankDriveRight() {
    return driver.getY(Hand.kRight);
  }

  public static double tankDriveLeft() {
    return driver.getY(Hand.kLeft);
  }
}