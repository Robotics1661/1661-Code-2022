package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import frc.robot.Robot_Framework;

public class Intake implements Robot_Framework {

    private static final int kPIDLoopIdx = 0;
    private static final int kSlotIdx = 0;

    public Intake() {

        intakeMotor.configFactoryDefault();
        intakeMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
        intakeMotor.setSensorPhase(false);
        intakeMotor.setInverted(false);
        intakeMotor.config_kF(kSlotIdx, intake_f, kTimeoutMs);
        
    }

    public void spin() {
        intakeMotor.set(ControlMode.PercentOutput, .5);
    }

    public void spinReverse() {
        intakeMotor.set(ControlMode.PercentOutput, -.5);
    }

    public void spinVariable(double v) {
        intakeMotor.set(ControlMode.PercentOutput, v);
    }

    public void spinVariableReverse(double v) {
        intakeMotor.set(ControlMode.PercentOutput, -v);
    }

    public void stop() {
        intakeMotor.set(ControlMode.PercentOutput, 0);
    }

}