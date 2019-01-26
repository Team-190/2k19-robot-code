/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem which controls the Collector
 */
public class Collector extends Subsystem {
    private static Collector collector;
    Solenoid openCloseSolenoid, ejectHatchSolenoid;
    private final int SOL_OPEN_PORT = 0, SOL_HATCH_PORT = 1;
    WPI_TalonSRX intake;
    private final int INTAKE_PORT = 0;
    DigitalInput ballInSwitch, collectorOpenSwitch;
    private final int BALL_SWITCH_PORT = 0, OPEN_SWITCH_PORT = 1;

    /**
     * Constructs all hardware objects
     */
    private Collector() {
        openCloseSolenoid = new Solenoid(SOL_OPEN_PORT);
        ejectHatchSolenoid = new Solenoid(SOL_HATCH_PORT);
        intake = new WPI_TalonSRX(INTAKE_PORT);
        ballInSwitch = new DigitalInput(BALL_SWITCH_PORT);
        collectorOpenSwitch = new DigitalInput(OPEN_SWITCH_PORT);
    }

    /**
     * Gets the singular instancee of the Collector subsystem
     * @return the Collector instance
     */
    public static Collector getInstance() {
        if (collector == null)
            collector = new Collector();
        return collector;
    }

    /**
     * Sets the state of the "claw"
     * @param state open (true) or closed (false)
     */
    public void setCollector(boolean state) {
        openCloseSolenoid.set(state);
    }

    /**
     * Sets the state of ejector cylinders
     * @param state pushed (true) or retracted (false)
     */
    public void setEjector(boolean state) {
        ejectHatchSolenoid.set(state);
    }

    /**
     * Sets the speed of the intake roller
     * @param speed speed of roller, -1 to 1
     */
    public void setIntakeSpeed(double speed) {
        intake.set(ControlMode.PercentOutput, speed);
    }

    /**
     * Gets the state of limit switch detecting ball in collector
     * @return true if ball in, false if not
     */
    public boolean isBallIn() {
        return ballInSwitch.get();
    }

    /**
     * Gets the state of limit switch detecting open collector
     * @return true if open, false if false
     */
    public boolean isOpen() {
        return collectorOpenSwitch.get();
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}
