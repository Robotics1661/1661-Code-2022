package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import frc.robot.Robot_Framework;

public class Climb implements Robot_Framework {

    private static final int kPIDLoopIdx = 0;
    private static final int kSlotIdx = 0;

    public Climb() {

        climbRight.configFactoryDefault();
        climbLeft.configFactoryDefault();

        climbRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
        climbLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
        
        climbRight.setSensorPhase(false);
        climbLeft.setSensorPhase(false);
        
        climbRight.setInverted(false);
        climbLeft.setInverted(false);
        
        climbRight.config_kF(kSlotIdx, climb_f, kTimeoutMs);
        climbLeft.config_kF(kSlotIdx, climb_f, kTimeoutMs);
        
    }

}