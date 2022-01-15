package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;

import frc.robot.Robot_Framework;

public class Horizontal_Agitator implements Robot_Framework {

    public Horizontal_Agitator() {
        horizontalAgitator.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, intake_peak_current, intake_continuous_current, 0.5));
        horizontalAgitator.setNeutralMode((NeutralMode.Coast));
    }

    public void spin(double speed) {
        horizontalAgitator.set(ControlMode.PercentOutput, speed * -.2);
    }

}