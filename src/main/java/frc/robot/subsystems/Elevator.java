/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
 
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
   private WPI_TalonSRX elevatorMotor;
   private AnalogInput pot;
    private static Elevator elevator;
    private final int ELEVATOR_PORT = 0,
    POT_PORT = 0;
    private Elevator() {
        elevatorMotor = new WPI_TalonSRX(ELEVATOR_PORT);
        pot = new AnalogInput(POT_PORT);
    }

    public static Elevator getInstance() {
        if (elevator == null)
            elevator = new Elevator();
        return elevator;
    }
    public void set(double speed){
        elevatorMotor.set(ControlMode.PercentOutput, speed);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}
