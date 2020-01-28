
package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Drive;
import frc.robot.commands.Feed;
// import frc.robot.commands.DriveButton;
// import frc.robot.commands.Running;
import frc.robot.commands.Scrub;
import frc.robot.commands.Shoot;
import frc.robot.commands.AutoCommands.TrackLimelightFollow;
import frc.robot.commands.AutoCommands.TrackLimelightX;
import frc.robot.commands.AutoCommands.TrackLimelightY;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Shooter;

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

  // public final Shooter shooter = new Shooter();
  public final DriveTrain drivetrain = new DriveTrain();
  public final Hopper hopper = new Hopper();
  public final ControlPanel controlPanel = new ControlPanel();
  public final Shooter shooter = new Shooter();
  public final ColorSensor ColorSensor = new ColorSensor();
  // private final Command m_DriveButton = new DriveButton(drivetrain);
  // private final Command m_Running = new Running();
  public static final NetworkTableInstance ntInst = NetworkTableInstance.getDefault();
  private final Command m_Scrub = new Scrub(controlPanel);
  private final Command m_Shoot = new Shoot(shooter);
  private final TrackLimelightX m_TrackLimelightX = new TrackLimelightX(shooter, drivetrain);
  private final TrackLimelightY m_TrackLimelightY = new TrackLimelightY(shooter, drivetrain);
  private final Command m_TrackLimelightFollow = new TrackLimelightFollow(drivetrain,m_TrackLimelightX,m_TrackLimelightY);
  
  /*
   * Constructor For RobotContainer *DECLARE SUBSYSTEM DEFAULT COMMANDS HERE*
   */
  public RobotContainer() {
    drivetrain.setDefaultCommand(new Drive(drivetrain));
    shooter.setDefaultCommand(new Shoot(shooter));
    hopper.setDefaultCommand(new Feed(hopper));
    controlPanel.setDefaultCommand(new Scrub(controlPanel));

    configureButtonBindings();
  }

  private void configureButtonBindings() {

    driverA.whileHeld(m_TrackLimelightFollow);
    driverX.whenPressed(m_Scrub);
    driverY.whenPressed(m_Shoot);

  }

  public static boolean getHopper() {
    return driver2.getBumper(Hand.kRight);
  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_TrackLimelightFollow;
  }

  public static boolean rotationalControl(){
    return driver2.getXButton();
  }
  public static boolean colorControl(){
    return driver2.getYButton();
  }


  public static double tankDriveRight() {
    SmartDashboard.putNumber("Right Joystick", driver1.getY(Hand.kRight));
    return driver1.getY(Hand.kRight);
  }

  public static double tankDriveLeft() {
    SmartDashboard.putNumber("Left Joystick", driver1.getY(Hand.kLeft));
    return driver1.getY(Hand.kLeft);
  }

  public static void pullNetworkTables() {
    double tv = NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("tv").getDouble(0);
    double tx = NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("tx").getDouble(0);
    double ty = NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ty").getDouble(0);
    double ta = NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ta").getDouble(0);
    System.out.println(tv);
    System.out.println(tx);
    System.out.println(ty);
    System.out.println(ta);
  }

  public static double getDistanceFromTarget() {
    double ty = NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ty").getDouble(0);
    ty = Math.toRadians(ty);
    double limeDistance = 74 / (Math.tan(ty));
    return limeDistance;
  }
  // real total hight is 98.25 lime hight is 22.5 75.75 = h2-h1
}
