package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import frc.robot.Robot_Framework;

public class Shooter implements Robot_Framework {

    private static final int kPIDLoopIdx = 0;
    private static final int kSlotIdx = 0;

    public Shooter() {

        shooterMotor.configFactoryDefault();
        shooterMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
        shooterMotor.setSensorPhase(false);
        shooterMotor.setInverted(false);
        shooterMotor.config_kF(kSlotIdx, shooter_f, kTimeoutMs);
        // fLeft.config_kP(kSlotIdx, .3, kTimeoutMs);
        
    }

    public void shoot() {
        shooterMotor.set(ControlMode.PercentOutput, .99);
    }

    public void shootVariable(double v) {
        shooterMotor.set(ControlMode.PercentOutput, v);
    }

    public void shootSmall() {
        shooterMotor.set(ControlMode.PercentOutput, .45);
    }

    public void stop() {
        shooterMotor.set(ControlMode.PercentOutput, 0);
    }

}