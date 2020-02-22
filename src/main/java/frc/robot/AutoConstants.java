/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.util.Units;

/**
 * Add your docs here.
 */
public class AutoConstants {
    public final static double  kP = 4;
    public final static double  kI = 0;
    public final static double  kD = 0;     

    public final static double  kS = 0.238;
    public final static double  kV = 0.231;
    public final static double  kA = 0.0472;


    public final static double maxSpeedMetersPerSecond = 1;
    public final static double maxAccelerationMetersPerSecondSquared = 1.2;  
    // public final static double trackWidth = 4.545290231051496;  
    public final static double trackWidth = Units.inchesToMeters(20);

    public static final double ramseteB = 2;
    //  B is similar to kP where it will make convergence more aggressive 
    public static final double ramseteZeta = .7;
    // Zeta is most comparable to kD and provides some damping
    // public static final Pose2d poseRef = new Pose2d(2,0, new Rotation2d());
	public static final double linearVelocityRefMeters = 1;
    public static final double angularVelocityRefRadiansPerSecond = 1;

}
