/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.AutoCommands.EasyRoute;
import frc.robot.commands.AutoCommands.HardRoute;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_easyRoute;
  private Command m_hardRoute;
  private Command m_autonomousCommand;
  public DigitalInput hoodCounter = new DigitalInput(6);
  int count = 0;
  boolean LS = true;
  boolean hopperFull = false;
  private RobotContainer m_robotContainer;
  
  // private String ll1 = "limelight-hounds";
  // private String ll2 = "limelight-"

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ledMode").setNumber(0);
    NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("camMode").setNumber(0);
    m_robotContainer = new RobotContainer();
    m_robotContainer.driveTrain.calibrateGyro();
    m_easyRoute = new EasyRoute(m_robotContainer.hood, m_robotContainer.shooter, 
                                m_robotContainer.driveTrain, m_robotContainer.hopper,
                                m_robotContainer.turret, m_robotContainer.elevator);
    m_hardRoute = new HardRoute(m_robotContainer.driveTrain, m_robotContainer.arms, 
                                m_robotContainer.intake, m_robotContainer.hood, 
                                m_robotContainer.shooter, m_robotContainer.hopper,
                                m_robotContainer.turret);
    m_robotContainer.m_sendableChooserAuto.addOption("Easy Route", m_easyRoute);
    m_robotContainer.m_sendableChooserAuto.addOption("Hard Route", m_hardRoute);

    // m_robotContainer.m_sendableChooserLL.addOption("", object);




    // m_robotContainer.hood.encoderCalibrate();
    // usb0 camera object can be tweaked to change brightness/whatever for usb
    // camera
    CameraServer.getInstance().startAutomaticCapture(0);
    // VideoSource usb0 = CameraServer.getInstance().startAutomaticCapture(0);

    // m_robotContainer.hood.calibrateHoodEncoder();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want run during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("pose x",m_robotContainer.driveTrain.m_odometry.getPoseMeters().getTranslation().getX());
    SmartDashboard.putNumber("pose y",m_robotContainer.driveTrain.m_odometry.getPoseMeters().getTranslation().getY());
    SmartDashboard.putNumber("pose rotation",m_robotContainer.driveTrain.m_odometry.getPoseMeters().getRotation().getDegrees());
    SmartDashboard.putNumber("Distance Meters Left", m_robotContainer.driveTrain.getDistanceMetersLeft());
    SmartDashboard.putNumber("Distance Meters Right", m_robotContainer.driveTrain.getDistanceMetersRight());

    SmartDashboard.putNumber("Velocity Meters Right", m_robotContainer.driveTrain.getRightEncoderVelocityMeters());
    SmartDashboard.putNumber("Velocity Meters Left", m_robotContainer.driveTrain.getLeftEncoderVelocityMeters());
    SmartDashboard.putNumber("Right Encoder Value", m_robotContainer.driveTrain.r1TalonFX.getSelectedSensorPosition());
    SmartDashboard.putNumber("Left Encoder Value", -m_robotContainer.driveTrain.l1TalonFX.getSelectedSensorPosition());
    SmartDashboard.putNumber("Gyro" , m_robotContainer.driveTrain.getHeading());
    SmartDashboard.putBoolean("Shooter is Running", m_robotContainer.shooter.getMotor());
    SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() m
    boolean pressed = hoodCounter.get();
    CommandScheduler.getInstance().run();
    if (pressed == true) {
      if (pressed && LS) {
        count++;
        LS = false;
      }
    }
    if (pressed == false) {
      LS = true;
    }
    if (count == 3) {
      hopperFull = true;
      count = 0;
    }
    if (count > 0) {
      hopperFull = false;
    }
    SmartDashboard.putNumber("Hood Count", count);
    SmartDashboard.putBoolean("Hopper is Full", hopperFull);
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

/**
 * This function is called periodically during Disabled mode.
 */
  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_robotContainer.driveTrain.zeroHeading();
    m_robotContainer.driveTrain.resetOdometry(m_robotContainer.driveTrain.m_pose);
    // m_robotContainer.hood.encoderCalibrate();
    NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ledMode").setNumber(0);
    NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("camMode").setNumber(0);
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    // m_autonomousCommand.schedule();
    // m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  /**
   * This makes sure that the autonomous stops running when teleop starts running. 
   * If you want the autonomous to continue until interrupted by another command, remove this line or comment it out.
   */
  @Override
  public void teleopInit() {
    // m_robotContainer.hood.encoderCalibrate();
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ledMode").setNumber(0);
    NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("camMode").setNumber(0);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

  }

  /**
   * This function is called once when entering test mode.
   */
  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
