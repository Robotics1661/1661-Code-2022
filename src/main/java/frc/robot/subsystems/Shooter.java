package frc.robot.subsystems;

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
        
    }

}