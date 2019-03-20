/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;

import java.util.Arrays;
import java.util.Collections;

public class ApproachTape extends PIDCommand {
    Drivetrain drive = Drivetrain.getInstance();
    Vision vision = Vision.getInstance();
    double[] distances, ports;
    public ApproachTape() {
        super(.1, 0, 0);
        requires(drive);
        setInputRange(-1, 1);
        getPIDController().setOutputRange(-1, 1);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
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

    @Override
    protected double returnPIDInput() {
        distances = vision.getDistances();
        ports = vision.getXCoordinates();
        int closestIndex = -1;
        double largestDist = -1;
        for (int i = 0; i< distances.length; i++) {
            if (largestDist < distances[i]) {
                closestIndex = i;
                largestDist = distances[i];
            }
        }
        double port = Math.max(ports[closestIndex]/80 - 1.5, -1);
        
        return port;
    }

    @Override
    protected void usePIDOutput(double output) {
        drive.arcadeDrive(.5, output);
    }
}
