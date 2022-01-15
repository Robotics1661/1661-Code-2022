package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;

import frc.robot.Robot_Framework;

public class AutoDrive implements Robot_Framework {

    /*
    Note: For now we will be writing the auto code in the Robot.java file.
    This can be later changed when we do PID but I think it is
    best to do it in there first for testing purposes. If you are reading
    this and we are ready to tune the robot, feel free to migrate the auto
    code into here -- or just keep it in Robot.java. It doesn't matter all
    that much.
    */

    public AutoDrive() {
        fLeft.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, peak_current, continuous_current, 0.5));

        fRight.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, peak_current, continuous_current, 0.5));

        fLeft.configOpenloopRamp(open_ramp);

        fRight.configOpenloopRamp(open_ramp);

        // NOTE: Not sure if we need this line; ask/research
        // compressor.setClosedLoopControl(true);

        fLeft.setNeutralMode((NeutralMode.Coast));
        fRight.setNeutralMode((NeutralMode.Coast));
        bLeft.setNeutralMode((NeutralMode.Coast));
        bRight.setNeutralMode((NeutralMode.Coast));
    }

    public void execute(double speed) {
        bRight.set(ControlMode.PercentOutput, speed);
        fRight.set(ControlMode.PercentOutput, speed);
        bLeft.set(ControlMode.PercentOutput, -speed);
        fLeft.set(ControlMode.PercentOutput, -speed);
    }

}