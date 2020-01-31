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

public class Scrub extends CommandBase {
  /**
   * Creates a new ControlPanelTurn.
   */
  private ColorSensor colorSensor;
  private ControlPanel controlPanel;
  private boolean rotationControl = false;
  private boolean colorControl = false;

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
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // rotationControl = RobotContainer.rotationalControl();
    // colorControl = RobotContainer.colorControl();
    // double[] targetColor = { 0, 0, 0 };
    // double redVal = colorSensor.red();
    // double blueVal = colorSensor.blue();
    // double greenVal = colorSensor.green();
    // double proximity = colorSensor.proximity();
    // double[] rgb = { redVal, greenVal, blueVal };
    // double[] blue = { 0, 255, 255 };
    // double[] green = { 0, 255, 0 };
    // double[] red = { 255, 0, 0 };
    // double[] yellow = { 255, 255, 0 };

    // String gameData;
    // gameData = DriverStation.getInstance().getGameSpecificMessage();

    // if (rotationControl) {
    // controlPanel.panelSpin(0.3);
    // }
    // if (gameData.length() > 0 && colorControl) {
    // switch (gameData.charAt(0)) {
    // case 'B':
    // while (rgb[0] != red[0] && rgb[1] != red[1] && rgb[2] != red[2]) {
    // controlPanel.panelSpin(0.2);
    // }
    // break;
    // case 'G':
    // while (rgb[0] != yellow[0] && rgb[1] != yellow[1] && rgb[2] != yellow[2])
    // controlPanel.panelSpin(0.2);
    // break;
    // case 'R':
    // while (rgb[0] != blue[0] && rgb[1] != blue[1] && rgb[2] != blue[2])
    // controlPanel.panelSpin(0.2);
    // break;
    // case 'Y':
    // while (rgb[0] != green[0] && rgb[1] != green[1] && rgb[2] != green[2])
    // controlPanel.panelSpin(0.2);
    // break;
    // default:
    // break;
    // }
    // } else {
    // //TODO: Figure out why Color Sensor isn't returning anything.
    // controlPanel.panelSpin(0);
    // SmartDashboard.putBoolean("Scrub Running", true);
    // SmartDashboard.putNumber("Red", rgb[0]);
    // SmartDashboard.putNumber("Green", rgb[1]);
    // SmartDashboard.putNumber("Blue", rgb[2]);
    // // SmartDashboard.putNumberArray("Color Sensor RGB Values", rgb);
    // // System.out.println("R: " + rgb[0] + "G: " + rgb[1] + "B: " + rgb[2]);
    // }
    SmartDashboard.putNumber("Red", colorSensor.getColor().red);
    SmartDashboard.putNumber("Green", colorSensor.getColor().green);
    SmartDashboard.putNumber("Blue", colorSensor.getColor().blue);

    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    String colorString = getColorString();
    rotationControl = false;
    colorControl = false;

    if (colorControl) {
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
      while (currentColorCount <= 4) {
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
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
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
      colorString = "Wot in Tarnation";
    }
    SmartDashboard.putString("Detected Color", colorString);
    return colorString;
  }
}
