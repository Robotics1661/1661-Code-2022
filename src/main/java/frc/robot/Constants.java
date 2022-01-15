package frc.robot;

public interface Constants {

    // ID -- Most ID constants need to be changed (imaginary vaules in now)
    
    final static int shooter_L = 13;
    final static int shooter_R = 12;
    final static int kicker_motor = 19;
    
    final static int turret_motor = 14;

    final static int front_right_drive = 5;
    final static int front_left_drive = 6;
    final static int back_right_drive = 7; 
    final static int back_left_drive = 8;

    final static int right_intake = 10; 
    final static int left_intake = 11; 

    final static int horizontal_agitator = 16;
    final static int vertical_agitator = 17;

    final static int climb1 = 24;
    final static int climb2 = 25;

    // Drive

    final static double gain_skim = 0.9;
    final static double gain_turn = 1.0;

    final static double feet_to_meters = 0.3048;

    final static double time_step = 0.05; // s

    final static double max_vel = 1; // m/s

    final static double torque_per_motor = 2.425; // N*m
    final static int num_motors = 4;
    final static double low_gear_ratio = 0.115;
    final static double high_gear_ratio = 0.277;

    final static double torque = torque_per_motor * num_motors * high_gear_ratio; // N*m

    final static double wheel_radius = 0.0762; // m
    final static double robot_weight = 58.0598; // kg
    final static double max_accel = torque / (wheel_radius * robot_weight); // m/s/s

    final static double max_jerk = 60.0; // m/s/s/s

    final static double wheelbase_width = 28.0 / 12 * feet_to_meters;

    final static int continuous_current = 60;
    final static int peak_current = 60;
    final static double open_ramp = 0.2;


    // Intake
    final static int intake_continuous_current = 30;
    final static int intake_peak_current = 30;



    // Control

    final static int left_x_axis = 0;
    final static int left_y_axis = 1;

    final static int right_x_axis = 4;
    final static int right_y_axis = 5;

    final static int left_trigger = 2;
    final static int right_trigger = 3;

    final static int a_button = 1;
    final static int b_button = 2;
    final static int x_button = 3;
    final static int y_button = 4;
    final static int left_bumper = 5;
    final static int right_bumper = 6;
    final static int back_button = 7;
    final static int start_button = 8;
    final static int left_stick_button = 9;
    final static int right_stick_button = 10;



    // PID

    int kTimeoutMs = 10; // Re-evaluate this

    // 1 encouder rotation: 4096 counts = (230/16) * 9
    // 240 counts --> 1 turret rotation
    // 1 degree = 240 counts/360 degrees
    final static double one_turret_degree = (4096.0 * 9.0 * (230.0/16)) / 360.0;
    

    
}