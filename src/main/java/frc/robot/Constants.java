
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

  public static enum PID_Constants {
    kDVelocity(0.001, 0, .009, 0), kTurn(.1, .000125, .02175, 0), kHood(0.75, 0, 0.2025, 0), kShooter(.1, 0, 0, 1),
    kGyro(0, 0, 0, 0), kDrive(0, 0, 0, 0);

    public final double P;
    public final double I;
    public final double D;
    public final double F;

    private PID_Constants(double P, double I, double D, double F) {
      this.P = P;
      this.I = I;
      this.D = D;
      this.F = F;
    }
  }

  public static enum TalonID {
    kLeft1(1, "Left1"), kLeft2(3, "Left2"), kRight1(0, "Right1"), kRight2(2, "Right2"), kShooter1(10, "Shooter1"),
    kCPScrub(6, "CPScrub"), kCPArm(7, "CPArm"), kIntake(7, "Intake"), kHop1(8, "Hop1"), kHop2(9, "Hop2"),
    kHop3(123123123, "Hop3"), kArms1(11, "Arms1"), kArms2(12, "Arms2"), kHood(15, "Hood"), kTurret(16, "Turret"),
    kclimberWinch(17, "climberWinch"), kclimberTelescope(18, "climberTelescope");

    public final int id;
    public final String name;

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
  // }

  public static final int kPIDLoopIdx = 0;
  public static final int kTimeoutMs = 30;
}
