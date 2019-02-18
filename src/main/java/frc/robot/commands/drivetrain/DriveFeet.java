/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;

public class DriveFeet extends PIDCommand {
    Drivetrain drive = Drivetrain.getInstance();
    static final double P = 0.0004, I = 0.0, D = 0.0; // TODO: tune PID
    double feet;

    public DriveFeet(double feet) {
        super(P, I, D);
        requires(drive);
        this.feet = feet;
        setSetpoint(drive.feetToEncoder(feet));
        getPIDController().setAbsoluteTolerance(50);
        
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        drive.setBrake();
        drive.resetEncoders();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        // System.out.println(getSetpoint());
        return getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        drive.drive(ControlMode.PercentOutput, 0, 0);
        // drive.setCoast();
    }

    @Override
    protected double returnPIDInput() {
        return (drive.getLeftPosition() + drive.getRightPosition()) / 2.00;
    }

    @Override
    protected void usePIDOutput(double output) {
        // System.out.println(output);
        // drive.setBrake();
        drive.drive(ControlMode.PercentOutput, output, output);
    }
}
