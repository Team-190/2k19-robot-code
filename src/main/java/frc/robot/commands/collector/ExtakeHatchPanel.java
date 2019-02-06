/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.collector;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Collector;

//TODO: do all of this
public class ExtakeHatchPanel extends Command {
    Collector collector = Collector.getInstance();

    public ExtakeHatchPanel() {
        requires(collector);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        setTimeout(0.25);
        collector.setEjector(true);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        //just waits 0.25 seconds
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        collector.setEjector(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        collector.setEjector(false);
    }
}
