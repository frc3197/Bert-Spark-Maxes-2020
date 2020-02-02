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
import frc.robot.RobotContainer;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.ControlPanel;

public class Scrub extends CommandBase {
  /**
   * Creates a new ControlPanelTurn.
   */
  private ColorSensor colorSensor;
  private ControlPanel controlPanel;
  private boolean rotationControl = false;
  private boolean positionControl = false;

  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  public Scrub(ControlPanel controlPanel, ColorSensor colorSensor) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controlPanel = controlPanel;
    this.colorSensor = colorSensor;
    addRequirements(controlPanel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
    //TODO: Toggle On Color Sensor Light
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Red", colorSensor.getColor().red);
    SmartDashboard.putNumber("Green", colorSensor.getColor().green);
    SmartDashboard.putNumber("Blue", colorSensor.getColor().blue);

    String gameData = DriverStation.getInstance().getGameSpecificMessage();
    String colorString = getColorString();
    rotationControl = RobotContainer.getRotationControl();
    positionControl = RobotContainer.getPositionControl();

    if (positionControl) {
      switch (gameData.charAt(0)) {
      case 'B':
        while (colorString != "Red") {
          controlPanel.panelSpin(0.2);
          colorString = getColorString();
        }
        break;
      case 'G':
        while (colorString != "Yellow") {
          controlPanel.panelSpin(0.2);
          colorString = getColorString();
        }
        break;
      case 'R':
        while (colorString != "Blue") {
          controlPanel.panelSpin(0.2);
          colorString = getColorString();
        }
        break;
      case 'Y':
        while (colorString != "Green") {
          controlPanel.panelSpin(0.2);
          colorString = getColorString();
        }
      default:
        break;
      }
    } else if (rotationControl) {
      // May work. Controls based on current color and rotates until it's seen that
      // color three times.
      String currentColor = getColorString();
      int currentColorCount = 0;
      boolean currentColorSeen = false;
      while (currentColorCount <= 8) {
        controlPanel.panelSpin(0.2);
        if (getColorString() == currentColor && !currentColorSeen) {
          currentColorCount++;
          currentColorSeen = true;
        } else if (getColorString() != currentColor) {
          currentColorSeen = false;
        }
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("Scrub Running", false);
    //TODO: Toggle Off Color Sensor light
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //TODO: add a finished conditional.
    return false;
  }

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
