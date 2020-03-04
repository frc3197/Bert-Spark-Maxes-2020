/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.ControlPanel;

/**
 * Defines a Scrub object. Code inside creates parameters, variables for the state of 
 * rotation control and position control, and variables for which color to sense for.
 */
public class Scrub extends CommandBase {
  
  private ColorSensor colorSensor;
  private ControlPanel controlPanel;
  private boolean position;
  private boolean finished = false;

  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  /**
   * Creates a new Scrub.
   * @param controlPanel ControlPanel subsystem
   * @param colorSensor ColorSensor subsystem
   */
  public Scrub(ControlPanel controlPanel, ColorSensor colorSensor, boolean position) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controlPanel = controlPanel;
    this.colorSensor = colorSensor;
    this.position = position;
    addRequirements(controlPanel);
    getColorString();
  }

  /**
   * Called when the command is initially scheduled.
   */
  @Override
  public void initialize() {
    finished = false;
    controlPanel.resetEncoderPosition();
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
    SmartDashboard.putBoolean("Position Control", false);
    SmartDashboard.putBoolean("Rotation Control", false);
    //TODO: Toggle On Color Sensor Light
  }

  /**
   * Called every time the scheduler runs while the command is scheduled.
   * Runs either rotation control or position control based on two buttons.
   * Rotation Control: Rotating Control Panel 3-5 times.
   * Position Control: Rotating Control Panel to a specific color given by FMS during the game.
   * Loop determines actions taken based on color given by FMS.
   */
  @Override
  public void execute() {
    String gameData = DriverStation.getInstance().getGameSpecificMessage();
    String colorString = getColorString();
    SmartDashboard.putString("Game Data", gameData);
    // rotationControl = RobotContainer.getRotationControl();

    if (position) {
      SmartDashboard.putBoolean("Position Control", true);
      switch (gameData.charAt(0)) {
      case 'B':
      case 'b':
        if (colorString != "Yellow") {
          controlPanel.panelSpin(0.6);
        }else{
          finished = true;
        }
        break;
      case 'G':
      case 'g':       
        if (colorString != "Blue") {
          controlPanel.panelSpin(0.6);
        }else{
          finished = true;
        }
        break;
      case 'R':
      case 'r':
        if (colorString != "Green") {
          controlPanel.panelSpin(0.6);
        }else{
          finished = true;
        }
        break;
      case 'Y':
      case 'y':
        if (colorString != "Red") {
          controlPanel.panelSpin(0.6);
        }else{
          finished = true;
        }
        break;
      }
    } else {
      SmartDashboard.putBoolean("Rotation Control", true);
      if(controlPanel.getEncoderPosition() < 800){
        //TODO: Calculation when we put a NEO on there.
        controlPanel.panelSpin(0.6);
      }else{
        finished = true;
        controlPanel.panelSpin(0);
      }
    }
  }

  /**
   * Called once the command ends or is interrupted.
   */
  @Override
  public void end(boolean interrupted) {
    controlPanel.panelSpin(0);
    SmartDashboard.putBoolean("Position Control", false);
    SmartDashboard.putBoolean("Rotation Control", false);
    //TODO: Toggle Off Color Sensor light
  }

  /**
   * Returns true when the command should end.
   */
  @Override
  public boolean isFinished() {
    return finished;
  }

  /**
   * Converts detected color values to a string.
   * @return String name of color detected
   */
  public String getColorString() {
    Color detectedColor = colorSensor.getColor();
    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Wot in Tarnation"; //no
    }
    SmartDashboard.putString("Detected Color", colorString);
    return colorString;
  }
}
