
package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController;

 public class RobotContainer {



  private static XboxController driver = new XboxController(0);


 
 
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