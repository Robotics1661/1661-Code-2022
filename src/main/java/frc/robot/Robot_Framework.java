package frc.robot;

import frc.robot.*;
import frc.robot.subsystems.*;
import frc.robot.subsystems.Ultrasonic;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public interface Robot_Framework extends Constants {

    // Xbox Controllers
    XboxController driveBox = new XboxController(0);
    XboxController mechBox = new XboxController(1);

    // Ultrasonic Sensor

    Ultrasonic ultrasonic = new Ultrasonic();

    // Compressor
    // Compressor compressor = new Compressor(0, PneumaticsModuleType.CTREPCM);

    // Drive
    WPI_TalonFX fLeft = new WPI_TalonFX(front_left_drive);
    WPI_TalonFX fRight = new WPI_TalonFX(front_right_drive);
    WPI_TalonFX bLeft = new WPI_TalonFX(back_left_drive);
    WPI_TalonFX bRight = new WPI_TalonFX(back_right_drive);
    // DoubleSolenoid gearSole = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);

    MotorControllerGroup left = new MotorControllerGroup(fLeft, bLeft);
    MotorControllerGroup right = new MotorControllerGroup(fRight, bRight);

    DifferentialDrive tank = new DifferentialDrive(left, right);

    Drive drive = new Drive();
    AutoDrive auto = new AutoDrive();

    // Intake
    TalonSRX intakeMotor = new TalonSRX(intake_motor);
    Intake intake = new Intake();

    // Shooter
    WPI_TalonFX shooterMotor = new  WPI_TalonFX(shooter_motor);
    Shooter shooter = new Shooter();

    // Vertical Agitator
    WPI_TalonFX verticalAgitatorRight = new WPI_TalonFX(vertical_agitator_right);
    WPI_TalonFX verticalAgitatorLeft = new WPI_TalonFX(vertical_agitator_left);
    WPI_TalonFX verticalAgitatorFront = new WPI_TalonFX(vertical_agitator_front);
    VerticalAgitator va = new VerticalAgitator();

    // Horizontal Agitator
    WPI_TalonFX horizontalAgitatorRight = new WPI_TalonFX(horizontal_agitator_right);
    WPI_TalonFX horizontalAgitatorLeft = new WPI_TalonFX(horizontal_agitator_left);
    HorizontalAgitator ha = new HorizontalAgitator();

    // Climb
    WPI_TalonFX climbRight = new WPI_TalonFX(climb_right);
    WPI_TalonFX climbLeft = new WPI_TalonFX(climb_left);
    Climb climb = new Climb();

}