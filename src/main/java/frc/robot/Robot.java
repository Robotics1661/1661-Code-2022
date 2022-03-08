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
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.util.net.PortForwarder;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
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

  double startTime;
  double timeReachedSetpoint = 0;
  double shooterTicker = 0;

  


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

    if (x > 5.0) {
      drive.turnSlightlyRight();
    }
    else if (x < -5.0) {
      drive.turnSlightlyLeft();
    }
    else if (valid == Math.abs(0.0)) {
      drive.turn(0.6);
    }
  }

  public void fullShoot() {
      ha.moveToVA();
      va.spinUp();
      shooter.shoot();
  }

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */

  @Override
  public void robotInit() {
    PortForwarder.add(5800, "limelight.local", 5800);
    PortForwarder.add(5809, "limelight.local", 5801);
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

    startTime = Timer.getFPGATimestamp();

    fRight.setSelectedSensorPosition(0);
    bRight.setSelectedSensorPosition(0);
    fLeft.setSelectedSensorPosition(0);
    bLeft.setSelectedSensorPosition(0);

  }

  /**
   * This function is called periodically during autonomous.
   * Current 
   */
  @Override
  public void autonomousPeriodic() {

    double time = Timer.getFPGATimestamp();
    shooter.shoot();
    va.spinUp();
    if (auto.driveDistance(.2, 15)) {
      if (timeReachedSetpoint == 0) {
        timeReachedSetpoint = Timer.getFPGATimestamp();
      }
      if (timeReachedSetpoint != 0) {
        if (time < timeReachedSetpoint + 7) {
          fullShoot();
        }
        else {
          shooter.stop();
          ha.stop();
          va.stop();
        }
        
      }
      
    }
    

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
 
    
    // Drive - driveBox joysticks ONLY
    if (mechBox.getRightTriggerAxis() > .3) {
      ha.moveToVA();
      va.spinUp();
      shooter.shootSmall();
    }
    // else if (mechBox.getLeftTriggerAxis() > .3 {
    //   ha.moveToVA();
    //   va.spinUp();
    //   shooter.shoot();
    // }
    else {
      ha.stop();
      va.stop();
      shooter.stop();
    }
    // if (mechBox.getRawButton(8)) {
    //   va.spinUp();
    //   shooter.shoot();
    // }
    // if (mechBox.getRawButtonReleased(8)) {
    //   va.stop();
    //   shooter.stop();
    // }
    if (mechBox.getRawButton(right_bumper)) {
      va.spinUp();
      shooter.shoot();
      ha.moveToVA();
    }
    if (mechBox.getRawButtonReleased(right_bumper)) {
      va.stop();
      shooter.stop();
      ha.stop();
    }

    // Rotate drivetrain to find target with Limelight - Both
    if (driveBox.getRawButton(a_button) || mechBox.getRawButton(x_button)) {
      findTargetStep();
    }
    else { 
      drive.executeTank();
    }


    // Running intake with mechBox B button, hold left bumper to reverse
    if (mechBox.getLeftBumper() && mechBox.getRawButton(b_button)) {
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




    
  }

  /**
   * This function is called periodically during test mode.
   */

  @Override
  public void testInit() {


    startTime = Timer.getFPGATimestamp();
    
  }

   
  @Override
  public void testPeriodic() {

    // auto.turnAngle(.5, 90);
    // if (Timer.getFPGATimestamp() - startTime < 2) {
    //   shooter.shoot();
    //   va.spinUp();
    // }
    // else if (Timer.getFPGATimestamp() - startTime < 5) {
    //   intake.spin();
    //   fullShoot();
    // }

    // shooter.shoot();
    // va.spinUp();
    // Timer.delay(2);
    // intake.spin();
    // fullShoot();
    
    drive.moveSpeed(.3);


  }
}

