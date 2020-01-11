
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
  /*
  public static enum TalonID {
    kLeft1(0, "Left1"), kLeft2(1, "Left2"), kRight1(2, "Right1"), kRight2(3, "Right2");
    
    public final int id;
    public final String name;
    
    private TalonID(int id, String name) {
      this.id = id;
      this.name = name;
    }
  }
  */
   public static enum CANSparkMaxID {
     kLeft1(14, "Left1"), kLeft2(15, "Left2"), kRight1(16, "Right1"), kRight2(1, "Right2");

     public final int id;
     public final String name;

     private CANSparkMaxID(int id, String name) {
       this.id = id;
       this.name = name;
     }
   }
}