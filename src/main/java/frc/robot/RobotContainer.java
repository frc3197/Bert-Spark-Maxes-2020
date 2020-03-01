
package frc.robot;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Climb;
import frc.robot.commands.Drive;
import frc.robot.commands.Elevate;
import frc.robot.commands.MoveArmCP;
import frc.robot.commands.MoveArms;
import frc.robot.commands.MoveHood;
import frc.robot.commands.MoveTurret;
import frc.robot.commands.Reverse;
import frc.robot.commands.Scrub;
import frc.robot.commands.Shoot;
import frc.robot.commands.Winch;
import frc.robot.commands.AutoCommands.EasyRoute;
import frc.robot.commands.AutoCommands.LimelightTracking.AlignScript;
import frc.robot.commands.AutoCommands.LimelightTracking.LimelightHood;
import frc.robot.commands.AutoCommands.LimelightTracking.TrackLimelightTurn;
import frc.robot.commands.AutoCommands.SubCommands.DriveDistance;
import frc.robot.commands.AutoCommands.SubCommands.DriveDistanceSimple;
import frc.robot.commands.AutoCommands.SubCommands.IntakeHopper;
import frc.robot.commands.AutoCommands.SubCommands.ToggleZoom;
// TODO: UNCOMMENT FOR DEMO
// import frc.robot.commands.AutoCommands.LimelightTracking.TrackLimelightFollow;
import frc.robot.subsystems.Arms;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
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
  private static boolean zoomOn = false;

  /**
   * The XboxController for driver 1.
   */
  private static XboxController driver1 = new XboxController(0);
  /**
   * The XboxController for driver 2.
   */
  private static XboxController driver2 = new XboxController(1);

  /**
   * The buttons for driver 1.
   */
  public static JoystickButton driver1A = new JoystickButton(driver1, 1);
  public static JoystickButton driver1B = new JoystickButton(driver1, 2);
  public static JoystickButton driver1X = new JoystickButton(driver1, 3);
  public static JoystickButton driver1Y = new JoystickButton(driver1, 4);
  public static JoystickButton driver1RS = new JoystickButton(driver1, 8);
  public static JoystickButton driver1RB = new JoystickButton(driver1, 6);
  public static JoystickButton driver1LS = new JoystickButton(driver1, 7);
  /**
   * The buttons for driver 2.
   */
  public static JoystickButton driver2A = new JoystickButton(driver2, 1);
  // TODO: UNCOMMENT FOR DEMO
  public static JoystickButton driver2B = new JoystickButton(driver2, 2);
  public static JoystickButton driver2X = new JoystickButton(driver2, 3);
  public static JoystickButton driver2Y = new JoystickButton(driver2, 4);
  public static JoystickButton driver2RB = new JoystickButton(driver2, 6);
  public static JoystickButton driver2RS = new JoystickButton(driver2, 8);
  public static JoystickButton driver2LS = new JoystickButton(driver2, 7);

  public final DriveTrain driveTrain = new DriveTrain();
  public final Hopper hopper = new Hopper();
  public final Intake intake = new Intake();
  public final Arms arms = new Arms();
  public final ControlPanel controlPanel = new ControlPanel();
  public final Shooter shooter = new Shooter();
  public final Hood hood = new Hood();
  public final Turret turret = new Turret();
  public final Climber climber = new Climber();
  public final ColorSensor colorSensor = new ColorSensor();
  public final Elevator elevator = new Elevator(hopper);
  public final Command easyRoute = new EasyRoute(hood, shooter, driveTrain, hopper, turret, elevator);
  public final Command driveDistance = new DriveDistance(driveTrain, 50);
  public final Command driveDistanceSimple = new DriveDistanceSimple(driveTrain, 30);
  Trajectory trajectory1;
  Trajectory trajectory2;
  Trajectory trajectory3;
  Trajectory trajectory4;
  Trajectory trajectory5;
  Trajectory trajectory6;
  Trajectory trajectory61;
  Trajectory trajectory62;
  /**
   * Table of values for limelight and receiving FMS data.
   */
  public static final NetworkTableInstance ntInst = NetworkTableInstance.getDefault();

  // TODO: BELOW IS FOR DEMO PURPOSES ONLY.
  // private final TrackLimelightFollow m_TrackLimelightFollow = new
  // TrackLimelightFollow(shooter, driveTrain);

  public SendableChooser<Command> m_sendableChooserAuto = new SendableChooser<Command>();
  public SendableChooser<String> m_sendableChooserLL = new SendableChooser<String>();
 

  public final String trajectoryJSON1 = "1.wpilib.json";
  public final String trajectoryJSON2 = "2.wpilib.json";
  public final String trajectoryJSON3 = "3.wpilib.json";
  public final String trajectoryJSON4 = "4.wpilib.json";
  public final String trajectoryJSON5 = "5.wpilib.json";
  public final String trajectoryJSON6 = "6.wpilib.json";
  public final String trajectoryJSON61 = "61-1.wpilib.json";  
  public final String trajectoryJSON62 = "61-2.wpilib.json";


  /*
   * Constructor For RobotContainer *DECLARE SUBSYSTEM DEFAULT COMMANDS HERE*
   */
  public RobotContainer() {
    try {
      Path trajectoryPath2 = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON2);
      trajectory2 = TrajectoryUtil.fromPathweaverJson(trajectoryPath2);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON2, ex.getStackTrace());
    }

    try {
      Path trajectoryPath1 = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON1);
      trajectory1 = TrajectoryUtil.fromPathweaverJson(trajectoryPath1);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON1, ex.getStackTrace());
    }

    try {
      Path trajectoryPath3 = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON3);
      trajectory3 = TrajectoryUtil.fromPathweaverJson(trajectoryPath3);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON3, ex.getStackTrace());
    }

    try {
      Path trajectoryPath4 = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON4);
      trajectory4 = TrajectoryUtil.fromPathweaverJson(trajectoryPath4);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON4, ex.getStackTrace());
    }

    try {
      Path trajectoryPath5 = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON5);
      trajectory5 = TrajectoryUtil.fromPathweaverJson(trajectoryPath5);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON5, ex.getStackTrace());
    }

    try {
      Path trajectoryPath6 = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON6);
      trajectory6 = TrajectoryUtil.fromPathweaverJson(trajectoryPath6);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON6, ex.getStackTrace());
    }

    try {
      Path trajectoryPath61 = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON61);
      trajectory61 = TrajectoryUtil.fromPathweaverJson(trajectoryPath61);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON61, ex.getStackTrace());
    }

    try {
      Path trajectoryPath62 = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON62);
      trajectory62 = TrajectoryUtil.fromPathweaverJson(trajectoryPath62);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON62, ex.getStackTrace());
    }
    driveTrain.setDefaultCommand(new Drive(driveTrain));
  //  intake.setDefaultCommand(new TakeIn(intake));
    arms.setDefaultCommand(new MoveArms(arms));
    controlPanel.setDefaultCommand(new MoveArmCP(controlPanel));
    turret.setDefaultCommand(new MoveTurret(turret));
    hood.setDefaultCommand(new MoveHood(hood));
    shooter.setDefaultCommand(new Shoot(shooter, hopper));

    configureButtonBindings();
  }

  /**
   * Attaches robot commands to buttons.
   */
  private void configureButtonBindings() {
    // driver1X.toggleWhenPressed(new Winch(climber, true));

    driver1A.toggleWhenPressed(new IntakeHopper(intake, hopper));
    driver1B.whileHeld(new Climb(climber, false));
    driver1Y.whileHeld(new Climb(climber, true));
    driver1X.whileHeld(new Winch(climber, true));
    driver1RS.whileHeld(new Winch(climber, false));

    // driver2A.whileHeld(new LimelightAdjustHood(hood));
    // driver2X.toggleWhenPressed(new BackupScrubRot(controlPanel));
    // driver2Y.whileHeld(driveDistanceSimple);
    driver2X.whenPressed(new Scrub(controlPanel, colorSensor, false));
    driver2Y.whenPressed(new Scrub(controlPanel, colorSensor, true));
    // driver2A.whileHeld(new LimelightHood(hood));
    driver2B.whileHeld(new TrackLimelightTurn(turret));
    driver2A.whenPressed(new LimelightHood(hood));
    driver2RB.whileHeld(new Elevate(elevator,hopper));
    driver2RS.whenPressed(new ToggleZoom());
    driver2LS.whileHeld(new Reverse(elevator,hopper));

    // TODO: UNCOMMENT FOR DEMO
    // driver2B.whileHeld(m_TrackLimelightFollow);
  }

  /**
   * Runs Shooter off of driver 2's Right Trigger value.
   * 
   * @return Driver 2's Right Trigger Value
   */
  public static double getShooter() {
    return driver2.getTriggerAxis(Hand.kRight);
  }

  /**
   * Runs Intake off of driver 1's RIght Trigger value.
   * 
   * @return Driver 1's Right Trigger Value
   */
  public static double getIntake() {
    return driver1.getTriggerAxis(Hand.kRight);
  }

  /**
   * Runs Hood manually from Driver 2's Right Joystick's vertical motion.
   * 
   * @return Vertical motion of Driver 2's Right Joystick
   */
  public static double getHoodManual() {
    return driver2.getY(Hand.kRight);
  }
  public static boolean getReverse(){
    return driver1.getBumper(Hand.kRight);
  }
  /**
   * Runs Turret manually from Driver 2's Left Joystick's horizontal motion.
   * 
   * @return Horizontal motion of Driver 2's Left Joystick
   */
  public static double getTurretManual() {
    return driver2.getX(Hand.kLeft);
  }

  /**
   * Runs Feeder on push of Driver 1's X Button.
   * 
   * @return State of Driver 1's X Button
   */
  public static boolean getFeeder() {
    return driver1.getXButton();
  }





  public static boolean getLeftBumper(){
    return driver1.getBumper(Hand.kLeft);
  }

  public static boolean getLeftTrigger(){
    double leftTrigger = driver1.getTriggerAxis(Hand.kLeft); 
    boolean down = (leftTrigger > .7);
    return down;
  }
  public static double getArm(){
    if(getLeftBumper())
    {
      return .2;
    }
    else if(getLeftTrigger())
    {
      return -.2;
    }
    else{
     return 0;
    }
  }



  /**
   * Runs Position Control on push of Driver 2's Y Button.
   * 
   * @return State of Driver 2's Y Button
   */
  public static boolean getPositionControl() {
    return driver2.getYButton();
  }



  public static boolean getLeftBumperCP(){
    return driver2.getBumper(Hand.kLeft);
  }

  public static boolean getLeftTriggerCP(){
    double leftTrigger = driver2.getTriggerAxis(Hand.kLeft); 
    boolean down = (leftTrigger > .7);
    return down;
  }
  public static double getArmCP(){
    if(getLeftBumperCP())
    {
      return .5;
    }
    else if(getLeftTriggerCP())
    {
      return -.5;
    }
    else{
     return 0;
    }
  }

  /**
   * Runs Rotation Control on push of Driver 2's X Button.
   * 
   * @return State of Driver 2's X Button
   */

  // public static boolean getRotationControl() {
  //   return driver2.getXButton();
  // }

  /**
   * Runs Tank Drive on the right side based on vertical motion of Driver 1's
   * Right Joystick.
   * 
   * @return Vertical motion of Driver 1's Right Joystick
   */
  public static double tankDriveRight() {
    SmartDashboard.putNumber("Right Joystick", driver1.getY(Hand.kRight));
    return driver1.getY(Hand.kRight);
  }

  /**
   * Runs Tank Drive on the left side based on vertical motion of Driver 1's Left
   * Joystick.
   * 
   * @return Vertical motion of Driver 1's Left Joystick
   */
  public static double tankDriveLeft() {
    SmartDashboard.putNumber("Left Joystick", driver1.getY(Hand.kLeft));
    return driver1.getY(Hand.kLeft);
  }

  /**
   * Puts the Autonomous Command on a schedule of commands and runs it.
   * 
   * @return The selection of Autonomous Command
   */
  public Command getAutonomousCommand() {

    //TODO: Add a command
    return m_sendableChooserAuto.getSelected();
    
 }
  

  /**
   * Calculates target velocity for Motor(s) based on inputs and RPM.
   * 
   * @param source Input from source of choosing
   * @param RPM    RPM of Motor(s)
   * @return Target Velocity value
   */
  public static double targetVelocity(double source, double RPM) {
    double targetVelocity = source * (RPM * 2048 / 600);
    return targetVelocity;
  }

  /**
   * Pulls Limelight values and prints them: tv: Whether Limelight detects a valid
   * target tx: X-position of Limelight in relation to target ty: Y-position of
   * Limelight in relation to target ta: Area of screen target occupies
   */
  public static void pullNetworkTables() {
    double tv = NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("tv").getDouble(0);
    double tx = NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("tx").getDouble(0);
    double ty = NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ty").getDouble(0);
    double ta = NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ta").getDouble(0);
    SmartDashboard.putNumber("tv", tv);
    SmartDashboard.putNumber("tx", tx);
    SmartDashboard.putNumber("ty", ty);
    SmartDashboard.putNumber("ta", ta);
  }

  /**
   * Calculates Limelight's distance from target based on Limelight's Y-position.
   * 
   * @return Calculated Distance
   */
  public static double getDistanceFromTarget() {
    double ty = NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ty").getDouble(0);
    double offset = Units.degreesToRadians(11.911213439);
    ty = Units.degreesToRadians(ty);
    double limeDistance = Math.abs(80.25 / (Math.tan(ty + offset)));
    SmartDashboard.putNumber("Distance from Target", limeDistance);
    if(limeDistance < 280 && limeDistance > 80){
      SmartDashboard.putBoolean("Inside Shooting Range", true);
    }else{
      SmartDashboard.putBoolean("Inside Shooting Range", false);
    }
    return limeDistance;
    // Will have to integrate a variable a1 value once set up for limelight angle.
  }

  /**
   * Pulls state of zoomOn to be used in toggleZoomBoolean.
   * 
   * @return State of zoomOn
   */
  public static boolean getZoom() {
    return zoomOn;

  }

  /**
   * Uses state of boolean zoomOn to toggle between normal zoom and hardware zoom.
   */
  public static void toggleZoomBoolean() {
    if (zoomOn) {
      zoomOn = false;
    } else {
      zoomOn = true;
    }
  }
  // real total hight is 98.25 lime hight is 22.5 75.75 = h2-h1

public static double arcadeDriveSpeed() {
	return driver1.getY(Hand.kLeft);
}
public static double arcadeDriveRotation(){
  return driver1.getX(Hand.kRight);
}
}
