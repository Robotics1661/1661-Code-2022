/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import frc.robot.Robot_Framework;
import frc.robot.subsystems.HorizontalAgitator;

import java.util.ResourceBundle.Control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.AHRS;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot implements Robot_Framework {

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  NetworkTableEntry tv = table.getEntry("tv");

  AHRS ahrs = new AHRS();


  /**
   * This function takes care of updating information in the smartDashboard
   */

  public void updateSmartDashboard() {
    //read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);

    double currentDistanceInches = ultrasonic.getDistIn();
    SmartDashboard.putNumber("Current Distance (inches)", currentDistanceInches);
  }



  /**
   * Eventually run in autonomousPeriodic
   * Take a step in either:
   *  A. Target found - spin back and forth slowly to face as much as possible
   *  B. Target not found - spin somewhat more quickly until target found
   */

  public void findTargetStep() {
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    double valid = tv.getDouble(0.0);

    if (x > 3.0) {
      drive.turnSlightlyRight();
    }
    else if (x < -3.0) {
      drive.turnSlightlyLeft();
    }
    else if (valid == Math.abs(0.0)) {
      drive.turn(0.4);
    }
  }

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */

  @Override
  public void robotInit() {

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    updateSmartDashboard();
  //  System.out.println(ahrs.getAngle());
  }

  
  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    fRight.setSelectedSensorPosition(0);
    bRight.setSelectedSensorPosition(0);
    fLeft.setSelectedSensorPosition(0);
    bLeft.setSelectedSensorPosition(0);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

    auto.driveDistance(.3, 3);
    

  }

  @Override
  public void teleopInit() {

    fRight.setSelectedSensorPosition(0);
    bRight.setSelectedSensorPosition(0);
    fLeft.setSelectedSensorPosition(0);
    bLeft.setSelectedSensorPosition(0);

  }

  @Override
  public void teleopPeriodic() {

    // Drive - driveBox joysticks ONLY
    drive.executeTank();

    // Rotate drivetrain to find target with Limelight - Both
    if (driveBox.getRawButton(a_button) || mechBox.getRawButton(x_button)) {
      findTargetStep();
    }


    // Running intake with mechBox B button, hold left bumper to reverse
    if (mechBox.getLeftBumper() && driveBox.getRawButton(b_button)) {
      intake.spinReverse();
    }
    else if (mechBox.getRawButton(b_button)) {
      intake.spin();
    }
    if (mechBox.getRawButtonReleased(b_button)) {
      intake.stop();
    }

    // Running horizontal agitator with mechBox Y button, hold left bumper to reverse
    if (mechBox.getLeftBumper() && mechBox.getRawButton(y_button)) {
      ha.moveFromVA();
    }
    else if (mechBox.getRawButton(y_button)) {
      ha.moveToVA();
    }
    if (mechBox.getRawButtonReleased(y_button)) {
      ha.stop();
    }

    // Run vertical agitator and shooter with mechBox A button
    if (mechBox.getRawButton(a_button)) {
      va.spinUp();
      shooter.shoot();
    }
    if (mechBox.getRawButtonReleased(a_button)) {
      va.stop();
      shooter.stop();
    }

    // Run horizontal agitator, vertical agitator, and shooter with mechBox right trigger
    if (mechBox.getRightBumper()) {
      ha.moveFromVA();
      va.spinUp();
      intake.spin();
      shooter.shoot();
    }
    if (mechBox.getRightBumperReleased()) {
      ha.stop();
      va.stop();
      intake.stop();
      shooter.stop();
    }
    
  }

  /**
   * This function is called periodically during test mode.
   */

  @Override
  public void testInit() {

    
  }

   
  @Override
  public void testPeriodic() {

    


  }
}

