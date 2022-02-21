package frc.robot;

import frc.robot.Robot_Framework;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive implements Robot_Framework{
    

    double x, y, throttle, turn, speedL, speedR, t_left, t_right;

    private static final int kPIDLoopIdx = 0;
    private static final int kSlotIdx = 0;

    public Drive() { 

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

    public void executeTank() {
        
        // FIX THISSSSSSSSSS ASAP THE CURRENT FIX IS RIDICULOUS
        y = driveBox.getRawAxis(right_x_axis); // left_y_axis
        x = driveBox.getRawAxis(left_y_axis); // right_x_axis

        

        if (Math.abs(y) > 0.1)
            throttle = y;
        else
            throttle = 0.0;

        if (Math.abs(x) > 0.2)
            turn = x;
        else
            turn = 0.0;

            

        t_left = throttle + turn;
        t_right = throttle - turn;

        //TEMP: Slow it down while we don't have pneumatic gear switching yet
        t_left *= 0.5;
        t_right *= 0.5;

        

        speedL = t_left + skim(t_right);
        speedR = t_right + skim(t_left);

        tank.tankDrive(speedL * .75, speedR * .75);

    }

    public void turn(double v) {
        // System.out.println("Workinggggggg TURN RIGHT");
        t_left = v;
        t_right = v;
        speedL = t_left + skim(t_right);
        speedR = t_right + skim(t_left);
        tank.tankDrive(speedL, speedR);
    }

    public void moveSpeed(double v) {
        t_left = -v;
        t_right = v;
        speedL = t_left + skim(t_right);
        speedR = t_right + skim(t_left);


        SmartDashboard.putNumber("speedL", speedL);
        SmartDashboard.putNumber("speedR", speedR);

        tank.tankDrive(speedL, speedR);
    }

    public void autoMove(double v) {
        t_left = -v;
        t_right = v;
        speedL = t_left + skim(t_right);
        speedR = t_right + skim(t_left);
    }

    public void moveSetPos(double p) {
        System.out.println("In the zone");
        fRight.set(ControlMode.MotionMagic, p);
        bRight.set(ControlMode.MotionMagic, p);
        fLeft.set(ControlMode.MotionMagic, -p);
        bLeft.set(ControlMode.MotionMagic, -p);
    }

    public void turnSlightlyRight() {
        t_left = 0.3;
        t_right = 0.3;
        speedL = t_left + skim(t_right);
        speedR = t_right + skim(t_left);
        tank.tankDrive(speedL, speedR);
    }


    public void turnSlightlyLeft() {
        t_left = -0.3;
        t_right = -0.3;
        speedL = t_left + skim(t_right);
        speedR = t_right + skim(t_left);
        tank.tankDrive(speedL, speedR);
    }


    private double skim(double v) {
        if (v > 1.0)
            return -((v - 1.0) * gain_skim);        
        else if (v < -1.0)
            return -((v + 1.0) * gain_skim);
        return 0;
    }
}