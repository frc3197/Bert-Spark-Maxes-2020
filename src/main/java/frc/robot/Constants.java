
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */

public final class Constants {

  /**
   * PID(Proportion, Integral, Derivative) Loop Constants: kTurn: Turning the
   * robot from Limelight's X-position(Demo Only) kMaintainDistance: Moves the
   * robot based on calculated distance from Limelight's Y-Position(Demo Only)
   * kShooter: Tuning the speed of the shooter wheels kGyro: Turns based off of
   * angle from Gyro Sensor kDrive: Drives based on encoder values
   */
  public static enum PID_Constants {
    kTurn(.3,0, 0, 0), kMaintainDistance(0.75, 0, 0.2025, 0), kShooter(.2, 0, 0, 1), kGyro(0, 0, 0, 0),
    kDrive(.5, 0, 0, 0),kHood(.4,0,0,0);

    public final double P;
    public final double I;
    public final double D;
    public final double F;

    /**
     * Uses variables as parameters.
     * 
     * @param P P variable
     * @param I I Variable
     * @param D D Variable
     * @param F F Variable
     */
    private PID_Constants(double P, double I, double D, double F) {
      this.P = P;
      this.I = I;
      this.D = D;
      this.F = F;
    }
  }

  /**
   * Motor ID numbers and what they correspond to.
   */
  public static enum TalonID {
    kLeft1(1, "Left1"), kLeft2(3, "Left2"), kRight1(0, "Right1"), kRight2(2, "Right2"), kShooter1(15, "Shooter1"),
    kCPScrub(4, "CPScrub"), kCPArm(5, "CPArm"), kIntake(6, "Intake"), kElevator(7, "elevator"), kHopper(13, "hopper"),
    kArms2(8, "Arms2"), kHood(10, "Hood"), kTurret(11, "Turret"), kclimberWinch(14, "climberWinch"),
    kclimberTelescope(12, "climberTelescope");

    public final int id;
    public final String name;

    /**
     * Creates Motor COntroller information objects named their respective names
     * above.
     * 
     * @param id   ID number(Number)
     * @param name Motor name(String)
     */
    private TalonID(int id, String name) {
      this.id = id;
      this.name = name;
    }
  }


  /*
   * public static enum CANSparkMaxID { kLeft1(14, "Left1"), kLeft2(15, "Left2"),
   * kRight1(16, "Right1"), kRight2(1, "Right2");
   * 
   * public final int id; public final String name;
   * 
   * // private CANSparkMaxID(int id, String name) { // this.id = id; // this.name
   * = name; // }
   */
  // 
  public static final double gear_ratio = 7.6;
  public static final int kPIDLoopIdx = 0;
  public static final int kTimeoutMs = 30;
}
