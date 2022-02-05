/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.Robot_Framework;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

// import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonFX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.AnalogInput;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot implements Robot_Framework {
  
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  
  int random = (int)((Math.random()*21000)+2000);
  boolean intakeLowered;
  boolean agitatorReverse;

  double startTime;
  boolean safe = false;

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
NetworkTableEntry tx = table.getEntry("tx");
NetworkTableEntry ty = table.getEntry("ty");
NetworkTableEntry ta = table.getEntry("ta");
NetworkTableEntry tcornx = table.getEntry("tcornx");
NetworkTableEntry tcorny = table.getEntry("tcorny");

private final AnalogInput ultrasonic = new AnalogInput(0);
  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */

  @Override
  public void robotInit() {


    // m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    // m_chooser.addOption("My Auto", kCustomAuto);
    // SmartDashboard.putData("Auto choices", m_chooser);
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
    // gearSole.set(DoubleSolenoid.Value.kForward);
    // // m_autoSelected = m_chooser.getSelected();
    // // // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    // // System.out.println("Auto selected: " + m_autoSelected);
    startTime = Timer.getFPGATimestamp();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

    double time = Timer.getFPGATimestamp();

    double diff = time - startTime;

    System.out.println(diff);

    if (diff < 7) {
      drive.moveSpeed(.3);
      // drive.turnSlightlyLeft();
    }

    // // if (diff < 2) {
    // //   bRight.set(ControlMode.PercentOutput, .4);
    // //   fRight.set(ControlMode.PercentOutput, .4);
    // //   bLeft.set(ControlMode.PercentOutput, -.4);
    // //   fLeft.set(ControlMode.PercentOutput, -.4);
    // // auto.execute(.4);
    // // }

    // if (diff < 3) {
    //   shooterRight.set(ControlMode.PercentOutput, 1);
    //   shooterLeft.set(ControlMode.PercentOutput, -1);
    //   kicker.set(ControlMode.PercentOutput, -1);
    // }
    // if (diff > 3 && diff < 8) {
    //   verticalAgitator.set(ControlMode.PercentOutput, .4);
    //   shooterRight.set(ControlMode.PercentOutput, 1);
    //   shooterLeft.set(ControlMode.PercentOutput, -1);
    //   kicker.set(ControlMode.PercentOutput, -1);
  
    // }
    // if (diff > 8 && diff < 10) {
    //   bRight.set(ControlMode.PercentOutput, .4);
    //   fRight.set(ControlMode.PercentOutput, .4);
    //   bLeft.set(ControlMode.PercentOutput, -.4);
    //   fLeft.set(ControlMode.PercentOutput, -.4);
    // }
    // // switch (m_autoSelected) {
    // //   case kCustomAuto:
    // //     // Put custom auto code here
    // //     break;
    // //   case kDefaultAuto:
    // //   default:
    // //     // Put default auto code here
    // //     break;
    // // }
  }

  @Override
  public void teleopInit() {
    
    // intakeLowered = false;
    // agitatorReverse = false;
    // gearSole.set(DoubleSolenoid.Value.kForward);

    // // leftIntake.configContinuousCurrentLimit(intake_continuous_current);
    // // rightIntake.configContinuousCurrentLimit(intake_continuous_current);
    // // leftIntake.configPeakCurrentLimit(intake_peak_current);
    // // rightIntake.configPeakCurrentLimit(intake_peak_current);
    // // kicker.configContinuousCurrentLimit(intake_continuous_current);
    // // kicker.configPeakCurrentLimit(intake_peak_current);

    // // horizontalAgitator.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, intake_peak_current, intake_continuous_current, 0.5));
    // // verticalAgitator.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, intake_peak_current, intake_continuous_current, 0.5));
    // // shooterLeft.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, intake_peak_current, intake_continuous_current, 0.5));
    // // shooterRight.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, intake_peak_current, intake_continuous_current, 0.5));

    // horizontalAgitator.setNeutralMode((NeutralMode.Coast));
    // verticalAgitator.setNeutralMode((NeutralMode.Coast));
    // shooterLeft.setNeutralMode((NeutralMode.Coast));
    // shooterRight.setNeutralMode((NeutralMode.Coast));
    // turret.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10);
    // turret.setSelectedSensorPosition(0,0,10);

    // turret.selectProfileSlot(0, 0);
		// turret.config_kF(0, 0, 10);
		// turret.config_kP(0, 0.1, 10);
		// turret.config_kI(0, 0, 10);
		// turret.config_kD(0, 0, 10);
    
    // turret.configMotionAcceleration(20000);
    // turret.configMotionCruiseVelocity(25000);
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void teleopPeriodic() {

    double raw_value = ultrasonic.getValue();

  //voltage_scale_factor allows us to compensate for differences in supply voltage.

    double voltage_scale_factor = 5/RobotController.getVoltage5V();

    double currentDistanceCentimeters = raw_value * voltage_scale_factor * 0.125;

    double currentDistanceInches = raw_value * voltage_scale_factor * 0.0492;

    SmartDashboard.putNumber("Current Distance (inches)", currentDistanceInches);
double x = tx.getDouble(0.0);
double y = ty.getDouble(0.0);
double area = ta.getDouble(0.0);
double[] xCorners = tcornx.getDoubleArray(new double[] {});
double[] yCorners = tcorny.getDoubleArray(new double[] {});

double topWidth = xCorners[3] - xCorners[0];
double bottomWidth = xCorners[2] - xCorners[1];
double leftHeight = yCorners[1] - yCorners[0];
double rightHeight = yCorners[2] - yCorners[3];

double avgWidth = ((topWidth) + (bottomWidth)) / 2.0;
double avgHeight = ((leftHeight) + (rightHeight)) / 2.0;

//post to smart dashboard periodically
SmartDashboard.putNumber("LimelightX", x);
SmartDashboard.putNumber("LimelightY", y);
SmartDashboard.putNumber("LimelightArea", area);
SmartDashboard.putNumber("AverageWidth", avgWidth);
SmartDashboard.putNumber("AverageHeight", avgHeight);
SmartDashboard.putNumber("TopWidth", topWidth);
SmartDashboard.putNumber("BottomWidth", bottomWidth);
SmartDashboard.putNumber("LeftHeight", leftHeight);
SmartDashboard.putNumber("RightHeight", rightHeight);

for (int i = 0; i < 4; i++) {
  SmartDashboard.putNumber("X (" + (i) + ")", xCorners[i]);
  SmartDashboard.putNumber("Y (" + (i) + ")", yCorners[i]);
}
SmartDashboard.putNumberArray("XCorners", xCorners);
SmartDashboard.putNumberArray("YCorners", yCorners);



    drive.executeTank();

    double Kp = -0.1;
    double min_command = 0.05;

    if (driveBox.getRawButton(a_button)) {
      double heading_error = -x;
      // System.out.println(heading_error);
      double steering_adjust = 0;
      if (x > 3.0) {
        // steering_adjust =  Kp*heading_error - min_command;
        drive.turnSlightlyRight();
      }
      else if (x < -3.0) {
        // steering_adjust = Kp*heading_error + min_command;
        drive.turnSlightlyLeft();
      }
      else if (x == Math.abs(0.0)) {
        drive.turn(0.4);
      }
      if (Math.abs(x) < 1) {
        
      }
    }

    double time = Timer.getFPGATimestamp();
    System.out.println(time);
    System.out.println(startTime);

    double diff = time - startTime;
    // startTime = Timer.getFPGATimestamp();

    if (driveBox.getRawButton(y_button)) {
      
    } 

    // if (diff < 1) {
    //   drive.moveSpeed(.3);
    // }
    
    // if (driveBox.getRawButton(b_button)) {
    //   drive.turnSlightlyRight();
    // }
    // else if (driveBox.getRawButton(x_button)) {
    //   drive.turnSlightlyLeft();
    // }
    // if (diff < 2) {
     
    // }


    // // Shifting Gears --> BUMPERS
    // if (driveBox.getRawButton(left_bumper)) { 
    //   gearSole.set(DoubleSolenoid.Value.kReverse); // High Gear
    // } 
    // else if (driveBox.getRawButton(right_bumper)) {
    //   gearSole.set(DoubleSolenoid.Value.kForward); // Low Gear
    // }

    // // Raising/Lowering Intake --> A BUTTON
    // if (driveBox.getRawButtonPressed(a_button) || mechBox.getRawButtonPressed(a_button)) {
    //   intakeLowered = !intakeLowered;
    // }

    // // Reversing Agitators --> Left Stick BUTTON
    // if (mechBox.getRawButtonPressed(left_stick_button)) {
    //   agitatorReverse = !agitatorReverse;
    // }

    // // int aRev; // Used as a multiplier for agitator reverse

    // // if(agitatorReverse) {
    // //   aRev = -1;
    // // }
    // // else {
    // //   aRev = 1;
    // // }


    // if (agitatorReverse) {
    //   mechBox.setRumble(RumbleType.kRightRumble, 1);
    //   mechBox.setRumble(RumbleType.kLeftRumble, 1);
    // }
    // else {
    //   mechBox.setRumble(RumbleType.kRightRumble, 0);
    //   mechBox.setRumble(RumbleType.kLeftRumble, 0);
    // } 
 
    // if (intakeLowered) {
    //   intakePosition.set(DoubleSolenoid.Value.kForward);
    //   // intake.lower();
    // } 
    // else if (!intakeLowered) {
    //   intakePosition.set(DoubleSolenoid.Value.kReverse);
    //   // intake.raise();
    // }

    // // Intake -- holding B button
    // if (mechBox.getRawButton(b_button)) {
    //   rightIntake.set(ControlMode.PercentOutput, .5);
    //   leftIntake.set(ControlMode.PercentOutput, -.5);
    //   // intake.spin(.5);
    // }
    // else if (mechBox.getRawButton(y_button)) {
    //   // Running Intake in Reverse
    //   rightIntake.set(ControlMode.PercentOutput, -.5);
    //   leftIntake.set(ControlMode.PercentOutput, .5);
    //   // intake.spin(-.5);
    // }
    // else {
    //   rightIntake.set(ControlMode.PercentOutput, 0);
    //   leftIntake.set(ControlMode.PercentOutput, 0);
    //   // intake.spin(0);
    // }

    // // Vertical Agitator

    // double va = mechBox.getRawAxis(right_trigger);
    // if (va > .1 && !agitatorReverse){
    //   verticalAgitator.set(ControlMode.PercentOutput, va *.4);
    //   // vAgitator.spin(va);
    // }
    // else if (va > .1 && agitatorReverse){
    //   verticalAgitator.set(ControlMode.PercentOutput, -va * .4);
    //   // vAgitator.spin(-va);
    // }
    // else {
    //   verticalAgitator.set(ControlMode.PercentOutput, 0);
    //   // vAgitator.spin(0);
    // }

 

    // HA
    // double ha = mechBox.getRawAxis(left_trigger);
    // if (ha > .1 && !agitatorReverse){
    //   horizontalAgitator.set(ControlMode.PercentOutput, -ha * .2);
    //   // hAgitator.spin(ha);
    // }
    // else if (ha > .1 && agitatorReverse){
    //   horizontalAgitator.set(ControlMode.PercentOutput, ha * .2);
    //   // hAgitator.spin(-ha);
    // } 
    // else {
    //   horizontalAgitator.set(ControlMode.PercentOutput, 0);
    //   // hAgitator.spin(0);
    // }

    // // Shooting (very basic) -- calculate later
    // if (mechBox.getXButton() || mechBox.getRawButton(right_bumper)) {
    //   shooterRight.set(ControlMode.PercentOutput, 1);
    //   shooterLeft.set(ControlMode.PercentOutput, -1);
    //   kicker.set(ControlMode.PercentOutput, -1);
    // }
    // else if (mechBox.getRawButton(left_bumper)) {
    //   shooterRight.set(ControlMode.PercentOutput, .5);
    //   shooterLeft.set(ControlMode.PercentOutput, -.5);
    //   kicker.set(ControlMode.PercentOutput, -.5);
    // }
    // else {
    //   shooterRight.set(ControlMode.PercentOutput, 0);
    //   shooterLeft.set(ControlMode.PercentOutput, 0);
    //   kicker.set(ControlMode.PercentOutput, 0);
    // }

   
    // // TURRET

    // // if (driveBox.getRawButton(b_button)) {
    // //   turret.set(ControlMode.PercentOutput, 0.1);
    // // }
    // // else if (driveBox.getRawButton(x_button)) {
    // //   turret.set(ControlMode.PercentOutput, -0.1);
    // // } 
    // // else {
    // //   turret.set(ControlMode.PercentOutput, 0);
    // // }
    



  //   double turretStick = mechBox.getRawAxis(4);

  //   if (Math.abs(turretStick) > .1) {
  //     turret.set(ControlMode.PercentOutput, turretStick * 0.8);
  //   }
  //   else {
  //     turret.set(ControlMode.PercentOutput, 0);
    
  //   System.out.println(turret.getSelectedSensorPosition());
    

    
  //   // //System.out.println("Turret Degree: " + (1.0 * turret.getSelectedSensorPosition() / one_turret_degree));
    
  //   // boolean xButton = mechBox.getRawButton(3);
  //   // if(xButton){
  //   //   random = (int)((Math.random()*21000)+2000);
  //   //   System.out.println("hey I'm here");
  //   // }
  //   // System.out.println("random: " + random);
  //   // turret.setSensorPhase(false);
  //   // turret.set(ControlMode.MotionMagic, random);

  //   // boolean usingLimelight = true;

  //   // if (usingLimelight) { // if using the limelight
  //   //   NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(0);
  //   // }
  //   // else { // in all other cases
  //   //   NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
  //   // }

  //   // // Limelight stuff
  //   // System.out.println("Valid targets?" + (NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0)));
  //   // System.out.println("Horizontal Offset From Crosshair To Target?" + (NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0)));
  //   // System.out.println("Vertical Offset From Crosshair To Target? " + (NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0)));
  //   // System.out.println("Target Area? " + (NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0)));

    
    

    
  // }


    
    

    




    
  }

  /**
   * This function is called periodically during test mode.
   */

  @Override
  public void testInit() {

    // turret.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10);
    // turret.setSelectedSensorPosition(0,0,10);

    // turret.selectProfileSlot(0, 0);
		// turret.config_kF(0, 0, 10);
		// turret.config_kP(0, 0.1, 10);
		// turret.config_kI(0, 0, 10);
		// turret.config_kD(0, 0, 10);
    
    // turret.configMotionAcceleration(20000);
    // turret.configMotionCruiseVelocity(25000);

    // NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);

    
      }

   
  @Override
  public void testPeriodic() {

    // System.out.println("working");

    if (mechBox.getRawButton(b_button)) {
      rightIntake.set(ControlMode.PercentOutput, .5);
      leftIntake.set(ControlMode.PercentOutput, -.5);
      // intake.spin(.5);
    }
    else if (mechBox.getRawButton(y_button)) {
      // Running Intake in Reverse
      rightIntake.set(ControlMode.PercentOutput, -.5);
      leftIntake.set(ControlMode.PercentOutput, .5);
      // intake.spin(-.5);
    }
    else {
      rightIntake.set(ControlMode.PercentOutput, 0);
      leftIntake.set(ControlMode.PercentOutput, 0);
      // intake.spin(0);
    }
    
//     double turretStick = mechBox.getRawAxis(4);

//     if (Math.abs(turretStick) > .1) {
//       turret.set(ControlMode.PercentOutput, turretStick * 0.8);
//     }
//     else {
//       turret.set(ControlMode.PercentOutput, 0);
//     }
//     System.out.println(turret.getSelectedSensorPosition());
//     System.out.println("Turret Degree: " + (10.0 * (1.0 * turret.getSelectedSensorPosition() / one_turret_degree)));

//     boolean usingLimelight = true;

//     if (usingLimelight) { // if using the limelight
//       NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(0);
//     }
//     else { // in all other cases
//       NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
//     }

//     System.out.println("Valid targets?" + (NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0)));
//     System.out.println("Horizontal Offset From Crosshair To Target?" + (NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0)));
//     System.out.println("Vertical Offset From Crosshair To Target? " + (NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0)));
//     System.out.println("Target Area? " + (NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0)));


//     double tx = (NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0));
//     double validTargets = (NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0));
//     double minCommand = 0.05;

    

//     if (mechBox.getRawButton(b_button)) {
// System.out.println("working");

// System.out.println("Current Encoder Value: " + turret.getSelectedSensorPosition());

// if (tx > 4) {
//   turret.set(ControlMode.PercentOutput, 0.2);
//   // turret.setSensorPhase(false); // Might not need this
//   // turret.set(ControlMode.MotionMagic, turret.getSelectedSensorPosition() + 100);
// }
// else if (tx < -4) {
//   turret.set(ControlMode.PercentOutput, -0.2);
//   // turret.setSensorPhase(false); // Might not need this
//   // turret.set(ControlMode.MotionMagic, turret.getSelectedSensorPosition() - 100);
// }
// else if (validTargets == 0) {
//   turret.setSensorPhase(false); // Might not need this
//   turret.set(ControlMode.MotionMagic, 0);
// }
// else {
//   turret.set(ControlMode.PercentOutput, 0);
// }
      
//       // turret.setSensorPhase(false);

//       // turret.set(ControlMode.MotionMagic, 1000);

//     }


  }
}

