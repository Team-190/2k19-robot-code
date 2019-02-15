/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Climber;

//TODO: deploy arms with the servo
/**
 * Deploys climbing arms
 */
public class DeployArms extends Command {
    Climber climber = Climber.getInstance();

    /**
     * Requires climber subsystem
     */
    public DeployArms() {
        requires(climber);
    }

    /**
     * TODO: deploy arms
     */
    @Override
    protected void initialize() {
    }

    /**
     * null
     */
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
}
