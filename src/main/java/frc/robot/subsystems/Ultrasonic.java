package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotController;

public class Ultrasonic {
    
    private final AnalogInput ultrasonicSensor = new AnalogInput(0);

    public Ultrasonic() {
        
    }

    // GET DISTANCES FROM ULTRASONIC SENSOR
    /**
     *  <p>Gets the distance from the ultrasonic sensor in centimeters.</p>
    */
    
    public double getDistCm() {
        double raw_value = ultrasonicSensor.getValue();
        
        //voltage_scale_factor allows us to compensate for differences in supply voltage.
        double voltage_scale_factor = 5/RobotController.getVoltage5V();
        double currentDistanceCentimeters = raw_value * voltage_scale_factor * 0.125;
        return currentDistanceCentimeters;
    }
    // GET DISTANCES FROM ULTRASONIC SENSOR
    /**
     *  <p>Gets the distance from the ultrasonic sensor in inches.</p>
    */
    public double getDistIn() {
        double raw_value = ultrasonicSensor.getValue();

        //voltage_scale_factor allows us to compensate for differences in supply voltage.
        double voltage_scale_factor = 5/RobotController.getVoltage5V();
        double currentDistanceInches = raw_value * voltage_scale_factor * 0.0492;
        return currentDistanceInches;
    }
}
