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

import frc.robot.commands.drive.DefaultDrive;
import frc.robot.models.PairedTalonSRX;

/**
 * Subsystem controls drivetrain and associated sensors
 */
public class Drivetrain extends Subsystem {
    private static Drivetrain drivetrain = null;

    AHRS navx;
    // Speed controller ports
    private final int LEFT_ONE = 0,
        LEFT_TWO = 1,
        RIGHT_ONE = 2,
        RIGHT_TWO = 3;
    
    // Encoder config values
    private final int PID_X = 0,
        TIMEOUT_MS = 0;

    PairedTalonSRX leftPair, rightPair;

    private Drivetrain() {
        navx = new AHRS(SPI.Port.kMXP);
        leftPair = new PairedTalonSRX(LEFT_ONE, LEFT_TWO);
        rightPair = new PairedTalonSRX(RIGHT_ONE, RIGHT_TWO);
        leftPair.configSelectedFeedbackSensor(FeedbackDevice.Analog, PID_X, TIMEOUT_MS);
        rightPair.configSelectedFeedbackSensor(FeedbackDevice.Analog, PID_X, TIMEOUT_MS);
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
    public void drive(double left, double right) {
        leftPair.set(ControlMode.PercentOutput, left);
        rightPair.set(ControlMode.PercentOutput, right);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DefaultDrive());
    }
}
