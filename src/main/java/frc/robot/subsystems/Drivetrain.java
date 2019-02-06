/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SPI;

import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import frc.robot.commands.drivetrain.DefaultDrive;
import frc.robot.models.PairedTalonSRX;

/**
 * Subsystem controls drivetrain and associated sensors
 */
public class Drivetrain extends Subsystem {
    private static Drivetrain drivetrain = null;

    final double ENCODER_TO_FEET = 3000;

    AHRS navx;
    // Speed controller ports
    private final int LEFT_FRONT = 0,
        LEFT_REAR = 1,
        RIGHT_FRONT = 2,
        RIGHT_REAR = 3;
    
    // Encoder config values
    private final int PID_X = 0,
        TIMEOUT_MS = 0;


    PairedTalonSRX leftPair, rightPair;

    private Drivetrain() {
        navx = new AHRS(SPI.Port.kMXP);
        leftPair = new PairedTalonSRX(LEFT_FRONT, LEFT_REAR);
        rightPair = new PairedTalonSRX(RIGHT_FRONT, RIGHT_REAR);
        leftPair.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, PID_X, TIMEOUT_MS);
        rightPair.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, PID_X, TIMEOUT_MS);
    }

    /**
     * Gets the Drivetrain instance
     * @return the single instance of the class
     */
    public static Drivetrain getInstance() {
        if (drivetrain == null) drivetrain = new Drivetrain();
        return drivetrain;
    }

    /**
     * Set motor values of drive speed controllers
     * @param left left motor value
     * @param right right motor value
     */
    public void drive(ControlMode mode, double left, double right) {
        leftPair.set(mode, left);
        rightPair.set(mode, right);
    }

    public AHRS getNavX() {
        return navx;
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
    }

    public double encoderToFeet(double feet) {
        return feet * ENCODER_TO_FEET;
    }

    public double feetToEncoder(double encoder) {
        return encoder / ENCODER_TO_FEET;
    }
}
