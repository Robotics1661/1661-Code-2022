package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;

import frc.robot.Robot_Framework;

public class Vertical_Agitator implements Robot_Framework {

    public Vertical_Agitator() {
        verticalAgitator.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, intake_peak_current, intake_continuous_current, 0.5));
        verticalAgitator.setNeutralMode((NeutralMode.Coast));
    }

    public void spin(double speed) {
        verticalAgitator.set(ControlMode.PercentOutput, speed * .4);
    }

}