package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import frc.robot.Robot_Framework;

public class VerticalAgitator implements Robot_Framework {

    private static final int kPIDLoopIdx = 0;
    private static final int kSlotIdx = 0;

    public VerticalAgitator() {

        verticalAgitatorRight.configFactoryDefault();
        verticalAgitatorLeft.configFactoryDefault();
        verticalAgitatorFront.configFactoryDefault();

        verticalAgitatorRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
        verticalAgitatorLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
        verticalAgitatorFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
        
        verticalAgitatorRight.setSensorPhase(false);
        verticalAgitatorLeft.setSensorPhase(false);
        verticalAgitatorFront.setSensorPhase(false);
        
        verticalAgitatorRight.setInverted(false);
        verticalAgitatorLeft.setInverted(false);
        verticalAgitatorFront.setInverted(false);
        
        verticalAgitatorRight.config_kF(kSlotIdx, va_f, kTimeoutMs);
        verticalAgitatorLeft.config_kF(kSlotIdx, va_f, kTimeoutMs);
        verticalAgitatorFront.config_kF(kSlotIdx, va_f, kTimeoutMs);
        
    }

}