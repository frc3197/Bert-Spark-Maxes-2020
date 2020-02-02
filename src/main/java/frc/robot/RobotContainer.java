
package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.commands.Drive;
import frc.robot.commands.Feed;
import frc.robot.commands.MoveArms;
import frc.robot.commands.Scrub;
import frc.robot.commands.Shoot;
import frc.robot.commands.TakeIn;
import frc.robot.commands.MoveHood;
import frc.robot.commands.MoveTurret;
import frc.robot.commands.AutoCommands.LimelightTracking.AlignScript;
// TODO: UNCOMMENT FOR DEMO
// import frc.robot.commands.AutoCommands.LimelightTracking.TrackLimelightFollow;

import frc.robot.subsystems.Arms;
import frc.robot.subsystems.ColorSensor;
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

  // TODO: UNCOMMENT FOR DEMO
  // public static JoystickButton driver1A = new JoystickButton(driver1, 1);
  // public static JoystickButton driver1B = new JoystickButton(driver1, 2);
  public static JoystickButton driver1X = new JoystickButton(driver1, 3);
  // public static JoystickButton driver1Y = new JoystickButton(driver1, 4);

  public static JoystickButton driver2A = new JoystickButton(driver1, 1);
  // public static JoystickButton driver2B = new JoystickButton(driver1, 2);
  // public static JoystickButton driver2X = new JoystickButton(driver1, 3);
  // public static JoystickButton driver2Y = new JoystickButton(driver1, 4);

  public final DriveTrain driveTrain = new DriveTrain();
  public final Hopper hopper = new Hopper();
  public final Intake intake = new Intake();
  public final Arms arms = new Arms();
  public final ControlPanel controlPanel = new ControlPanel();
  public final Shooter shooter = new Shooter();
  public final Hood hood = new Hood();
  public final Turret turret = new Turret();
  public final ColorSensor colorSensor = new ColorSensor();

  public static final NetworkTableInstance ntInst = NetworkTableInstance.getDefault();

  // TODO: BELOW IS FOR DEMO PURPOSES ONLY.
  // private final TrackLimelightFollow m_TrackLimelightFollow = new TrackLimelightFollow(shooter, driveTrain);

  public SendableChooser<Command> m_sendableChooser = new SendableChooser<Command>();

  /*
   * Constructor For RobotContainer *DECLARE SUBSYSTEM DEFAULT COMMANDS HERE*
   */
  public RobotContainer() {
    driveTrain.setDefaultCommand(new Drive(driveTrain));
    intake.setDefaultCommand(new TakeIn(intake));
    arms.setDefaultCommand(new MoveArms(arms));
    turret.setDefaultCommand(new MoveTurret(turret));
    hood.setDefaultCommand(new MoveHood(hood));
    controlPanel.setDefaultCommand(new Scrub(controlPanel, colorSensor));
    shooter.setDefaultCommand(new Shoot(shooter, hopper));

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    driver2A.whileHeld(new AlignScript(hood, shooter, driveTrain));
    driver1X.toggleWhenPressed(new Feed(hopper));
    // TODO: UNCOMMENT FOR DEMO
    // driver1A.whileHeld(m_TrackLimelightFollow);
  }

  public static double getShooter() {
    return driver2.getTriggerAxis(Hand.kRight);
  }

  public static double getIntake() {
    return driver1.getTriggerAxis(Hand.kRight);
  }

  public static double getHoodManual() {
    return driver2.getY(Hand.kRight);
  }

  public static double getTurretManual() {
    return driver2.getX(Hand.kLeft);
  }

  public static boolean getFeeder(){
    return driver1.getXButton();
  }

  public static boolean getPositionControl() {
    return driver2.getYButton();
  }

  public static boolean getRotationControl() {
    return driver2.getXButton();
  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_sendableChooser.getSelected();
  }

  public static double hoodMotorManual() {
    return driver2.getY(Hand.kRight);
  }

  public static boolean rotationalControl() {
    return driver2.getXButton();
  }

  public static boolean colorControl() {
    return false;
    // return driver2.getYButton();
  }

  public static double tankDriveRight() {
    SmartDashboard.putNumber("Right Joystick", driver1.getY(Hand.kRight));
    return driver1.getY(Hand.kRight);
  }

  public static double tankDriveLeft() {
    SmartDashboard.putNumber("Left Joystick", driver1.getY(Hand.kLeft));
    return driver1.getY(Hand.kLeft);
  }

  public static double targetVelocity(double source, double RPM) {
    double targetVelocity = source * RPM * 4096 / 600;
    return targetVelocity;
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
    System.out.println("offset : " + ty);
    double limeDistance = 74 / (Math.tan(ty));
    return limeDistance;
    // Will have to integrate a variable a1 value once set up for limelight angle.
  }
  // real total hight is 98.25 lime hight is 22.5 75.75 = h2-h1
}
