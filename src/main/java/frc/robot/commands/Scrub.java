/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.ControlPanel;
import edu.wpi.first.wpilibj.DriverStation;

public class Scrub extends CommandBase {
  /**
   * Creates a new ControlPanelTurn.
   */
  ColorSensor colorSensor;
  ControlPanel controlPanel;
  boolean rotationControl = false;
  boolean colorControl = false;

  public Scrub(ControlPanel controlPanel, ColorSensor colorSensor) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.controlPanel = controlPanel;
    this.colorSensor = colorSensor;
    addRequirements(controlPanel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    rotationControl = RobotContainer.rotationalControl();
    colorControl = RobotContainer.colorControl();
    double[] targetColor = { 0, 0, 0 };
    double redVal = colorSensor.red();
    double blueVal = colorSensor.blue();
    double greenVal = colorSensor.green();
    double proximity = colorSensor.proximity();
    double[] rgb = { redVal, greenVal, blueVal };
    double[] blue = { 0, 255, 255 };
    double[] green = { 0, 255, 0 };
    double[] red = { 255, 0, 0 };
    double[] yellow = { 255, 255, 0 };

    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();

    if (rotationControl = true) {
      controlPanel.panelSpin(0.3);
    }
    if (gameData.length() > 0) {
      switch (gameData.charAt(0)) {
      case 'B':
        while (rgb != red) {
          controlPanel.panelSpin(0.2);
        }
        break;
      case 'G':
        while (rgb != yellow)
          controlPanel.panelSpin(0.2);
        break;
      case 'R':
        while (rgb != blue)
          controlPanel.panelSpin(0.2);
        break;
      case 'Y':
        while (rgb != green)
          controlPanel.panelSpin(0.2);
        break;
      default:
        break;
      }
    } else {
      controlPanel.panelSpin(0);
    }

    if (colorControl = true) {

    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
