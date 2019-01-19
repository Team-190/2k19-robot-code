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
    Drivetrain drive;
    PathfinderSequence sequence;
    boolean resetSensors, isFinished;
    private EncoderFollower leftFollower, rightFollower;

    public DriveSequence(PathfinderSequence sequence) {
        this(sequence, true);
    }

    public DriveSequence(PathfinderSequence sequence, boolean resetSensors) {
        drive = Drivetrain.getInstance();
        requires(drive);
        this.sequence = sequence;
        this.resetSensors = resetSensors;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        if (resetSensors) {
            drive.resetNavX();
            drive.resetEncoders();
        }
        isFinished = false;

        File leftFile = new File(sequence.getLeftCSVName());
        File rightFile = new File(sequence.getRightCSVName());

        if (!(fileNotFound(leftFile) || fileNotFound(rightFile))) { // both files found, both methods called always
            Trajectory leftTrajectory = Pathfinder.readFromCSV(leftFile);
            leftFollower = new EncoderFollower(leftTrajectory);
            //TODO: configure left encoder values
            Trajectory rightTrajectory = Pathfinder.readFromCSV(rightFile);
            rightFollower = new EncoderFollower(rightTrajectory);
        }
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }

    public boolean fileNotFound(File file) {
        if (!file.exists()) {
            DriverStation.reportWarning(file.getPath() + " not found. DriveSequence.java", false);
            return isFinished = true;
        }
        return false;
    }
}
