package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.*;
import edu.wpi.first.wpilibj.*;

import frc.robot.Robot_Framework;

/*
    Spin horizontal agitator when we shoot and intake
        after we intake let the horizontal agitator spin a bit longer

*/

public class Intake implements Robot_Framework {


    public Intake() {
        // leftIntake.configFactoryDefault();
        // rightIntake.configFactoryDefault();

        // leftIntake.setSensorPhase(false);
        // rightIntake.setSensorPhase(false);
        
        // leftIntake.setInverted(false);
        // rightIntake.setInverted(false);

        leftIntake.configContinuousCurrentLimit(intake_continuous_current);
        rightIntake.configContinuousCurrentLimit(intake_continuous_current);
        leftIntake.configPeakCurrentLimit(intake_peak_current);
        rightIntake.configPeakCurrentLimit(intake_peak_current);
    }

    // Negative values reverse the intake
    public void spin(double speed) {
        rightIntake.set(ControlMode.PercentOutput, speed);
        leftIntake.set(ControlMode.PercentOutput, -speed);
    }

    public void raise() {
        intakePosition.set(DoubleSolenoid.Value.kReverse);
    }

    public void lower() {
        intakePosition.set(DoubleSolenoid.Value.kForward);
    }

}