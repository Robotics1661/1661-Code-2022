package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
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

    /**
     *  <p>Moves the ball in the vertical agitator towards the shooter.</p>
    */
    
    public void spinUp() {
        verticalAgitatorRight.set(ControlMode.PercentOutput, 1);
        verticalAgitatorLeft.set(ControlMode.PercentOutput, -1);
        verticalAgitatorFront.set(ControlMode.PercentOutput, 1);
    }
    
    /**
     *  <p>Moves the ball in the vertical agitator away from the shooter.</p>
    */
    public void spinDown() {
        verticalAgitatorRight.set(ControlMode.PercentOutput, -.5);
        verticalAgitatorLeft.set(ControlMode.PercentOutput, .5);
        verticalAgitatorFront.set(ControlMode.PercentOutput, -.5);
    }

    /**
     *  <p>Stops spinning the vertical agitator.</p>
    */
    public void stop() {
        verticalAgitatorRight.set(ControlMode.PercentOutput, 0);
        verticalAgitatorLeft.set(ControlMode.PercentOutput, 0);
        verticalAgitatorFront.set(ControlMode.PercentOutput, 0);
    }

}