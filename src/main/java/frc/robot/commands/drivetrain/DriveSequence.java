/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.models.PathfinderSequence;
import frc.robot.subsystems.Drivetrain;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.followers.EncoderFollower;

public class DriveSequence extends Command {
    Drivetrain drivetrain = Drivetrain.getInstance();
    PathfinderSequence sequence;
    boolean resetSensors;
    private EncoderFollower leftFollower, rightFollower;

    public DriveSequence(PathfinderSequence sequence, boolean resetSensors) {
        requires(drivetrain);
        this.sequence = sequence;
        this.resetSensors = resetSensors;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        if (resetSensors) {
            drivetrain.resetNavX();
            drivetrain.resetEncoders();
        }

        leftFollower = new EncoderFollower(sequence.getLeft());
        rightFollower = new EncoderFollower(sequence.getRight());
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        double leftSpeed = leftFollower.calculate(drivetrain.getLeftPosition());
        double rightSpeed = rightFollower.calculate(drivetrain.getRightPosition());

        double gyroHeading = -drivetrain.getAngle(); // TODO: adjust get_____ for real robot
        double desiredHeading = Pathfinder.r2d(leftFollower.getHeading());
        double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
        double turn = -angleDifference / 100; // magic number from 254

        drivetrain.drive(ControlMode.PercentOutput, leftSpeed + turn, rightSpeed - turn);

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return leftFollower.isFinished() && rightFollower.isFinished();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        drivetrain.drive(ControlMode.PercentOutput, 0, 0);
    }
}
