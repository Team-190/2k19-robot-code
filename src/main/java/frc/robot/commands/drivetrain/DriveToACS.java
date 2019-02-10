/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;

public class DriveToACS extends Command {
    Drivetrain drive = Drivetrain.getInstance();
    Climber climber = Climber.getInstance();
    double leftSpeed, rightSpeed;
    public DriveToACS() {
        requires(drive);
        requires(climber); // only reads, doesn't write
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        leftSpeed = climber.isLeftACSTriggered() ? 0.0 : 0.5;
        rightSpeed = climber.isRightACSTriggered() ? 0.0 : 0.5;
        drive.drive(ControlMode.PercentOutput, leftSpeed, rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return climber.isLeftACSTriggered() && climber.isRightACSTriggered();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        drive.drive(ControlMode.PercentOutput, 0, 0);
    }
}
