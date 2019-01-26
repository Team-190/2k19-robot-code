/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import java.io.File;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.models.PathfinderSequence;
import frc.robot.subsystems.Drivetrain;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class DriveSequence extends Command {
    Drivetrain drivetrain;
    PathfinderSequence sequence;
    boolean resetSensors, isFinished;
    private EncoderFollower leftFollower, rightFollower;

    public DriveSequence(PathfinderSequence sequence) {
        this(sequence, true);
    }

    public DriveSequence(PathfinderSequence sequence, boolean resetSensors) {
        drivetrain = Drivetrain.getInstance();
        requires(drivetrain);
        this.sequence = sequence;
        this.resetSensors = resetSensors;
        isFinished = false;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        if (resetSensors) {
            drivetrain.getNavX().reset();
            drivetrain.resetEncoders();
        }

        File leftFile = new File(sequence.getLeftCSVName());
        File rightFile = new File(sequence.getRightCSVName());

        if (fileExists(leftFile) && fileExists(rightFile)) { // both files found, both methods called always
            Trajectory leftTrajectory = Pathfinder.readFromCSV(leftFile);
            leftFollower = new EncoderFollower(leftTrajectory);
            // TODO: configure left encoder values
            Trajectory rightTrajectory = Pathfinder.readFromCSV(rightFile);
            rightFollower = new EncoderFollower(rightTrajectory);
            // TODO: configure right encoder values
        }
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        // in case a file wasn't found, don't want to run
        if (!isFinished) {
            double leftSpeed = leftFollower.calculate(drivetrain.getLeftPosition());
            double rightSpeed = rightFollower.calculate(drivetrain.getRightPosition());

            double gyroHeading = -drivetrain.getNavX().getAngle(); // TODO: adjust get_____ for real robot
            double desiredHeading = Pathfinder.r2d(leftFollower.getHeading());
            double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
            double turn = -angleDifference / 100; // magic number from 254

            drivetrain.drive(leftSpeed + turn, rightSpeed - turn);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return isFinished || (leftFollower.isFinished() && rightFollower.isFinished());
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        // TODO: stop driving at end of path?
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }

    public boolean fileExists(File file) {
        if (!file.exists()) {
            DriverStation.reportWarning(file.getPath() + " not found. DriveSequence.java", false);
            return isFinished = false;
        }
        return true;
    }
}
