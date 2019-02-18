/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort.Port;

import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import frc.robot.commands.drivetrain.DefaultDrive;
import frc.robot.commands.drivetrain.TurnToHeading;
import frc.robot.models.PairedTalonSRX;

/**
 * Subsystem controls drivetrain and associated sensors
 */
public class Drivetrain extends Subsystem {
    private static Drivetrain drivetrain = null;

    final double TICKS_PER_REV = 1024;
    final double WHEEL_DIAMETER = .5; // feet
    final double ENCODER_TO_FEET = WHEEL_DIAMETER * TICKS_PER_REV; // TODO: configure this

    AHRS navx;
    // Speed controller ports
    private final int LEFT_FRONT = 8, LEFT_REAR = 6, RIGHT_FRONT = 7, RIGHT_REAR = 5;

    // Encoder config values
    private final int PID_X = 0, TIMEOUT_MS = 0;

    PairedTalonSRX leftPair, rightPair;

    private Drivetrain() {
        navx = new AHRS(Port.kUSB);
        leftPair = new PairedTalonSRX(LEFT_FRONT, LEFT_REAR);
        rightPair = new PairedTalonSRX(RIGHT_FRONT, RIGHT_REAR);
        leftPair.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, PID_X, TIMEOUT_MS);
        rightPair.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, PID_X, TIMEOUT_MS);
        leftPair.setSensorPhase(true);
        rightPair.setSensorPhase(true);
        leftPair.setSelectedSensorPosition(0);
        rightPair.setSelectedSensorPosition(0);
        rightPair.setInverted(true);
        addChild(leftPair);
        addChild(rightPair);
        addChild(leftPair.getFollower());
        addChild(rightPair.getFollower());
        // setBrake();
    }

    /**
     * Gets the Drivetrain instance
     * 
     * @return the single instance of the class
     */
    public static Drivetrain getInstance() {
        if (drivetrain == null)
            drivetrain = new Drivetrain();
        return drivetrain;
    }

    public void setCoast() {
        System.out.println("Coast");
        leftPair.setNeutralMode(NeutralMode.Coast);
        rightPair.setNeutralMode(NeutralMode.Coast);
    }

    public void setBrake() {
        System.out.println("Brake");
        leftPair.setNeutralMode(NeutralMode.Brake);
        rightPair.setNeutralMode(NeutralMode.Brake);
    }

    /**
     * Set motor values of drive speed controllers
     * 
     * @param left  left motor value
     * @param right right motor value
     */
    public void drive(ControlMode mode, double left, double right) {
        leftPair.set(mode, left);
        rightPair.set(mode, right);
    }

    public double getYaw() {
        return (double) navx.getYaw();
    }

    public AHRS getNavX() {
        return navx;
    }

    public void resetNavX() {
        navx.reset();
    }

    public int getLeftPosition() {
        return leftPair.getSelectedSensorPosition(PID_X);
    }

    public int getRightPosition() {
        return rightPair.getSelectedSensorPosition(PID_X);
    }

    public void resetEncoders() {
        leftPair.setSelectedSensorPosition(0, PID_X, TIMEOUT_MS);
        rightPair.setSelectedSensorPosition(0, PID_X, TIMEOUT_MS);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DefaultDrive());
        // setDefaultCommand(new TurnToHeading(0));
    }

    public double feetToEncoder(double feet) {
        return feet * ENCODER_TO_FEET;
    }

    public double encoderToFeet(double encoder) {
        return encoder / ENCODER_TO_FEET;
    }
}
