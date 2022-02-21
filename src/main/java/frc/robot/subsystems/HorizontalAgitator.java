package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import frc.robot.Robot_Framework;

public class HorizontalAgitator implements Robot_Framework {

    private static final int kPIDLoopIdx = 0;
    private static final int kSlotIdx = 0;

    public HorizontalAgitator() {

        horizontalAgitatorRight.configFactoryDefault();
        horizontalAgitatorLeft.configFactoryDefault();

        horizontalAgitatorRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
        horizontalAgitatorLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
        
        horizontalAgitatorRight.setSensorPhase(false);
        horizontalAgitatorLeft.setSensorPhase(false);
        
        horizontalAgitatorRight.setInverted(false);
        horizontalAgitatorLeft.setInverted(false);
        
        horizontalAgitatorRight.config_kF(kSlotIdx, ha_f, kTimeoutMs);
        horizontalAgitatorLeft.config_kF(kSlotIdx, ha_f, kTimeoutMs);
        
    }

    public void moveToVA() {
        horizontalAgitatorRight.set(ControlMode.PercentOutput, .5);
        horizontalAgitatorLeft.set(ControlMode.PercentOutput, -.5);
    }
    public void moveFromVA() {
        horizontalAgitatorRight.set(ControlMode.PercentOutput, -.5);
        horizontalAgitatorLeft.set(ControlMode.PercentOutput, .5);
    }
    public void stop() {
        horizontalAgitatorRight.set(ControlMode.PercentOutput, 0);
        horizontalAgitatorLeft.set(ControlMode.PercentOutput, 0);
    }

}