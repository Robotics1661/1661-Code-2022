package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;

import frc.robot.Robot_Framework;

public class AutoDrive implements Robot_Framework {

    /*
    Note: For now we will be writing the auto code in the Robot.java file.
    This can be later changed when we do PID but I think it is
    best to do it in there first for testing purposes. If you are reading
    this and we are ready to tune the robot, feel free to migrate the auto
    code into here -- or just keep it in Robot.java. It doesn't matter all
    that much.
    */

    private static final int kPIDLoopIdx = 0;
    private static final int kSlotIdx = 0;

    public AutoDrive() {
        fLeft.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, peak_current, continuous_current, 0.5));
        fRight.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, peak_current, continuous_current, 0.5));
        bLeft.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, peak_current, continuous_current, 0.5));
        bRight.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, peak_current, continuous_current, 0.5));
        
        fLeft.configOpenloopRamp(open_ramp);
        fRight.configOpenloopRamp(open_ramp);
        bLeft.configOpenloopRamp(open_ramp);
        bRight.configOpenloopRamp(open_ramp);

        fLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, kPIDLoopIdx, kTimeoutMs);
        fRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, kPIDLoopIdx, kTimeoutMs);
        bLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, kPIDLoopIdx, kTimeoutMs);
        bRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, kPIDLoopIdx, kTimeoutMs);
        
        fRight.setSelectedSensorPosition(0);
        bRight.setSelectedSensorPosition(0);
        fLeft.setSelectedSensorPosition(0);
        bLeft.setSelectedSensorPosition(0);

        fLeft.setSensorPhase(false);
        fRight.setSensorPhase(false);
        bLeft.setSensorPhase(false);
        bRight.setSensorPhase(false);

        fLeft.setInverted(false);
        fRight.setInverted(false);
        bLeft.setInverted(false);
        bRight.setInverted(false);

        fLeft.config_kP(kSlotIdx, drive_p, kTimeoutMs);
        fRight.config_kP(kSlotIdx, drive_p, kTimeoutMs);
        bLeft.config_kP(kSlotIdx, drive_p, kTimeoutMs);
        bRight.config_kP(kSlotIdx, drive_p, kTimeoutMs);

        fLeft.config_kI(kSlotIdx, drive_i, kTimeoutMs);
        fRight.config_kI(kSlotIdx, drive_i, kTimeoutMs);
        bLeft.config_kI(kSlotIdx, drive_i, kTimeoutMs);
        bRight.config_kI(kSlotIdx, drive_i, kTimeoutMs);

        fLeft.config_kD(kSlotIdx, drive_d, kTimeoutMs);
        fRight.config_kD(kSlotIdx, drive_d, kTimeoutMs);
        bLeft.config_kD(kSlotIdx, drive_d, kTimeoutMs);
        bRight.config_kD(kSlotIdx, drive_d, kTimeoutMs);

        // fLeft.configMotionCruiseVelocity(sensorUnitsPer100ms);
        // fLeft.configMotionAcceleration(sensorUnitsPer100msPerSec);

        // NOTE: Not sure if we need this line; ask/research
        // compressor.setClosedLoopControl(true);

        fLeft.setNeutralMode((NeutralMode.Coast));
        fRight.setNeutralMode((NeutralMode.Coast));
        bLeft.setNeutralMode((NeutralMode.Coast));
        bRight.setNeutralMode((NeutralMode.Coast));
    }

    /**
     *  <p>Drive a fixed speed.</p>
     *  @param speed Percent output 0-1
    */
    public void execute(double speed) {
        bRight.set(ControlMode.PercentOutput, speed);
        fRight.set(ControlMode.PercentOutput, speed);
        bLeft.set(ControlMode.PercentOutput, -speed);
        fLeft.set(ControlMode.PercentOutput, -speed);
    }
    
    /**
     *  <p>Drive a fixed distance autonomously.</p>
     *  @param speed Percent output 0-1
     *  @param distance Distance to drive in meters
    */
    public void driveDistance(double speed, double distance) {
        double numRotations = distance / (2 * Math.PI * wheel_radius);
        int encoderUnits = (int)(numRotations * encoder_units_per_rotation);
        if (fRight.getSelectedSensorPosition() < encoderUnits) {
            execute(speed);
        }
    }

}