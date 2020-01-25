
package frc.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Running;
import frc.robot.commands.Drive;
import frc.robot.commands.DriveButton;
import frc.robot.commands.Scrub;
import frc.robot.commands.Shoot;
import frc.robot.commands.TakeIn;
import frc.robot.commands.moveArms;
import frc.robot.commands.moveHood;
import frc.robot.commands.moveTurret;
import frc.robot.subsystems.Arms;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

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
  public static JoystickButton driverA = new JoystickButton(driver1, 1);
  public static JoystickButton driverX = new JoystickButton(driver1, 3);
  public static JoystickButton driverY = new JoystickButton(driver1, 4);
  public static JoystickButton driverB = new JoystickButton(driver1, 2);
  /**
   * An example Subsystem. [DEPRECATED]
   */

  /**
   * An example Command [DEPRECATED]
   */

  // public final Shooter shooter = new Shooter();
  public final DriveTrain drivetrain = new DriveTrain();
  public final Hopper hopper = new Hopper();
  public final Intake intake = new Intake();
  public final Arms arms = new Arms();
  public final ControlPanel controlPanel = new ControlPanel();
  public final Shooter shooter = new Shooter();
  public final Hood hood = new Hood ();
  public final Turret turret = new Turret();
  private final Command m_TakeIn = new TakeIn(intake);
  private final Command m_moveArms = new moveArms(arms);
  private final Command m_moveHood = new moveHood(hood);
  private final Command m_moveTurret = new moveTurret(turret);
  private final Command m_DriveButton = new DriveButton(drivetrain);
  private final Command m_Running = new Running();
  private final Command m_Scrub = new Scrub(controlPanel);
  private final Command m_Shoot = new Shoot(shooter);
  // public static final NetworkTableInstance ntInst =
  // NetworkTableInstance.getDefault();

  /*
   * Constructor For RobotContainer *DECLARE SUBSYSTEM DEFAULT COMMANDS HERE*
   */
  public RobotContainer() {
    drivetrain.setDefaultCommand(new Drive(drivetrain));
    shooter.setDefaultCommand(new Shoot(shooter));
    configureButtonBindings();
  }

  private void configureButtonBindings() {

    driverA.whenPressed(m_moveArms);
    driverX.whenPressed(m_Scrub);
    
  }
  public static double getShot() {
    return driver1.getTriggerAxis(Hand.kRight);
  }
  public static boolean getHopper() {
    return driver2.getBumper(Hand.kRight);
  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_Scrub;
  }

  public static double tankDriveRight() {
    SmartDashboard.putNumber("Right Joystick", driver1.getY(Hand.kRight));
    return driver1.getY(Hand.kRight);
  }

  public static double tankDriveLeft() {
    SmartDashboard.putNumber("Left Joystick", driver1.getY(Hand.kLeft));
    return driver1.getY(Hand.kLeft);
  }
  public static void pullNetworkTables(){
    double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    System.out.println(tv);
    System.out.println(tx);
    System.out.println(ty);
    System.out.println(ta);
  }
}
