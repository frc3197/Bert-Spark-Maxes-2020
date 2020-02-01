/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  private WPI_TalonSRX TalonShooter1 = new WPI_TalonSRX(Constants.TalonID.kShooter1.id);

  public Shooter() {
    TalonShooter1.configFactoryDefault();
    TalonShooter1.setSafetyEnabled(false);
    TalonShooter1.configOpenloopRamp(.25);
    TalonShooter1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx,
        Constants.kTimeoutMs);
    TalonShooter1.configNominalOutputForward(0, Constants.kTimeoutMs);
    TalonShooter1.configNominalOutputReverse(0, Constants.kTimeoutMs);
    TalonShooter1.configPeakOutputForward(1, Constants.kTimeoutMs);
    TalonShooter1.configPeakOutputReverse(-1, Constants.kTimeoutMs);
    TalonShooter1.config_kF(Constants.kPIDLoopIdx, Constants.PID_Constants.kShooter.F, Constants.kTimeoutMs);
    TalonShooter1.config_kP(Constants.kPIDLoopIdx, Constants.PID_Constants.kShooter.P, Constants.kTimeoutMs);
    TalonShooter1.config_kI(Constants.kPIDLoopIdx, Constants.PID_Constants.kShooter.I, Constants.kTimeoutMs);
    TalonShooter1.config_kD(Constants.kPIDLoopIdx, Constants.PID_Constants.kShooter.D, Constants.kTimeoutMs);

    TalonShooter1.setSafetyEnabled(false);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shooterVelocity(double motorVal) {
    TalonShooter1.set(ControlMode.Velocity, -motorVal);
  }

  public void setShooter(double speed) {
    TalonShooter1.set(speed);
  }

  public double getXOffset() {
    System.out.println(NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("tx").getDouble(0));
    return NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("tx").getDouble(0);
  }

  public double getYOffset() {
    return NetworkTableInstance.getDefault().getTable("limelight-hounds").getEntry("ty").getDouble(0);
  }

}
