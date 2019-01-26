/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.models.PairedTalonSRX;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
    private static Elevator elevator;

    public static Elevator getInstance() {
        if (elevator == null)
            elevator = new Elevator();
        return elevator;
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private PairedTalonSRX motor;

    private static final int LEFT = 4,
        RIGHT = 5;

    private static final int PID_X = 0;

    private final static double POT_BOTTOM = 0; // Pot Value
    private final static double POT_TOP_OFFSET = 1024; // Pot Value
    private final static double ERROR_TOLERANCE = 10;

    private double motorSetpoint;
    
    public Elevator() {
        motor = new PairedTalonSRX(LEFT, RIGHT);
        motor.configSelectedFeedbackSensor(FeedbackDevice.Analog);
        motor.configPIDF(PID_X, 0, 0, 0, 0);
        motor.setInverted(InvertType.None);
        motor.setSensorPhase(false);
        motorSetpoint = Position.Ground.getPosition();
    }

    public int getPosition() {
        return motor.getSelectedSensorPosition();
    }

    private double heightToPot(double inches) {
        double heightScale = inches / Position.MaximumHeight.getPosition();
        return (POT_TOP_OFFSET * heightScale) + POT_BOTTOM;
    }

    private double potToInches(double potValue) {
        return ((potValue - POT_BOTTOM) / POT_TOP_OFFSET) *
            Position.MaximumHeight.getPosition();
    }

    public void setHeight(Position position) {
        motorSetpoint = heightToPot(position.getPosition());
        motor.set(ControlMode.Position, motorSetpoint);
    }

    public void stop() {
        motor.set(ControlMode.PercentOutput, 0);
    }

    public void manualControl(double speed) {
        motor.set(ControlMode.PercentOutput, speed);
    }

    public boolean inPosition() {
        double thisError = motorSetpoint - getPosition();
        return Math.abs(thisError) <= ERROR_TOLERANCE;
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    public enum Position {
        Ground(0),
        CollectHatch(10),
        PlaceCargo(10),
        RocketHatchTwo(10),
        RocketHatchThree(10),
        RocketCargoTwo(10),
        RocketCargoThree(10),
        MaximumHeight(70);

        private int inches;

        private Position(int inches) {
            this.inches = inches;
        }

        public int getPosition() {
            return inches;
        }

    }
}
