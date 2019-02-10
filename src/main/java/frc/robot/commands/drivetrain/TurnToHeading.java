/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;

public class TurnToHeading extends PIDCommand {
    Drivetrain drive = Drivetrain.getInstance();
    PIDController controller;
    double heading;
    int countOnTarget = 0;

    public TurnToHeading(double heading) {
        super(0, 0, 0); // TODO: tune PID
        this.heading = heading;
        controller = getPIDController();
        controller.setContinuous();
        controller.setPercentTolerance(1);
        setInputRange(-180, 180);
        setSetpoint(heading);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        // if (controller.onTarget()) {
        //     if (countOnTarget == 3)
        //         return true;
        //     countOnTarget++;
        // } else
        //     countOnTarget = 0;
        // return false;
        return controller.onTarget();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        drive.drive(ControlMode.PercentOutput, 0, 0);
    }

    @Override
    protected double returnPIDInput() {
        return drive.getAngle();
    }

    @Override
    protected void usePIDOutput(double output) {
        drive.drive(ControlMode.PercentOutput, output, -output); //TODO: fix signs?
    }
}
