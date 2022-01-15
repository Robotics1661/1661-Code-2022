package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.Robot_Framework;

public class Shooter implements Robot_Framework {
    
    public Shooter() {
        // shooterLeft.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, intake_peak_current, intake_continuous_current, 0.5));
        // shooterRight.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, intake_peak_current, intake_continuous_current, 0.5));
        shooterLeft.setNeutralMode((NeutralMode.Coast));
        shooterRight.setNeutralMode((NeutralMode.Coast));
    }

    public void shoot(double shotSpeed)  {
        shooterRight.set(ControlMode.PercentOutput, shotSpeed);
        shooterLeft.set(ControlMode.PercentOutput, -shotSpeed);
        kicker.set(ControlMode.PercentOutput, -shotSpeed);
        horizontalAgitator.set(ControlMode.PercentOutput, -.2);
    }

    public void shoot(double shotSpeed, double kickerSpeed)  {
        shooterRight.set(ControlMode.PercentOutput, shotSpeed);
        shooterLeft.set(ControlMode.PercentOutput, -shotSpeed);
        kicker.set(ControlMode.PercentOutput, -kickerSpeed);
        horizontalAgitator.set(ControlMode.PercentOutput, -.2);
    }

    public void shoot(double shotSpeed, double kickerSpeed, int aRev)  {
        shooterRight.set(ControlMode.PercentOutput, shotSpeed);
        shooterLeft.set(ControlMode.PercentOutput, -shotSpeed);
        kicker.set(ControlMode.PercentOutput, -kickerSpeed);
        horizontalAgitator.set(ControlMode.PercentOutput, -.2 * aRev);
    }

}