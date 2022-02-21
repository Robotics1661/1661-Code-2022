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

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

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
    
    // Testing Code
    // if (driveBox.getRawButton(b_button)) {
    //   testTalon.set(ControlMode.PercentOutput, .2);
    //   System.out.println(testTalon.getMotorOutputPercent());
    // }


    // Rotate drivetrain to find target with Limelight
    if (driveBox.getRawButton(a_button)) {
      findTargetStep();
    }

    // Shifting gears with driveBox bumbers

    // Running intake with mechBox buttons
    if (driveBox.getLeftBumper() && driveBox.getRawButton(x_button)) {
      intake.spinReverse();
    }
    else if (driveBox.getRawButton(x_button)) {
      intake.spin();
    }
    else {
      intake.stop();
    }

    // Running horizontal agitator with mechBox buttons
    if (driveBox.getLeftBumper() && driveBox.getRawButton(b_button)) {
      ha.moveFromVA();
    }
    else if (driveBox.getRawButton(b_button)) {
      ha.moveToVA();
    }
    else {
      ha.stop();
    }
    

    // Running vertical agitator with mechBox buttons

    // Shooting with mechBox triggers

    // Climbing with driveBox buttons
    
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

