/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
    private static Climber climber;

    private AnalogInput leftACS, rightACS, chassisACS;
    private final int LEFT_ACS_PORT = 0, RIGHT_ACS_PORT = 1, CHASSIS_ACS_PORT = 2;

    private DigitalInput trolleyUp, armsDown;
    private final int TROLLEY_PORT = 0, ARMS_PORT = 1;

    private WPI_TalonSRX motor;
    private final int MOTOR_PORT = 0;

    private DoubleSolenoid solenoid;
    private final int SOLENOID_PORT = 0, SOLENOID_FORWARD_PORT = 1, SOLENOID_REVERSE_PORT = 2;

    private Climber() {
        leftACS = new AnalogInput(LEFT_ACS_PORT);
        rightACS = new AnalogInput(RIGHT_ACS_PORT);
        chassisACS = new AnalogInput(CHASSIS_ACS_PORT);

        trolleyUp = new DigitalInput(TROLLEY_PORT);
        armsDown = new DigitalInput(ARMS_PORT);

        motor = new WPI_TalonSRX(MOTOR_PORT);

        solenoid = new DoubleSolenoid(SOLENOID_PORT, SOLENOID_FORWARD_PORT, SOLENOID_REVERSE_PORT);
    }

    // TODO: find the real threshold
    //TODO: create constants
    public boolean isLeftACSTriggered() {
        return leftACS.getVoltage() > 1.5;
    }

    // TODO: find the real threshold
    public boolean isRightACSTriggered() {
        return rightACS.getVoltage() > 1.5;
    }

    // TODO: find the real threshold
    public boolean isChassisACSTriggered() {
        return chassisACS.getVoltage() > 1.5;
    }

    public boolean isTrolleyUp() {
        return trolleyUp.get();
    }

    public boolean isArmsDown() {
        return armsDown.get();
    }

    public void setSpeed(ControlMode mode, double speed) {
        motor.set(mode, speed);
    }

    public void setSolenoid(boolean state) {
        DoubleSolenoid.Value value;
        if (state)
            value = Value.kForward;
        else
            value = Value.kReverse;

        solenoid.set(value);
    }

    @Override
    public void initDefaultCommand() {
    }

    public static Climber getInstance() {
        if (climber == null)
            climber = new Climber();
        return climber;
    }

    public enum Direction {
        FORWARD(1),
        BACKWARD(-1);

        private final int value;
        private Direction(int value) {
            this.value = value;
        }

        public int get() {
            return value;
        }
    }
}
