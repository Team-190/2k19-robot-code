/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.collector;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Collector;

/**
 * Closes the collector
 */
public class CloseCollector extends Command {
    Collector collector;
    /**
     * Requires Collector subsystem
     */
    public CloseCollector() {
        requires(collector = Collector.getInstance());
    }

    /**
     * Sets collector to close
     */
    @Override
    protected void initialize() {
        collector.setCollector(false);
    }

    /**
     * Waits
     */
    @Override
    protected void execute() {
    }

    /**
     * Gets the state of the collector as the state of the command
     * @return state of collector (open or closed)
     */
    @Override
    protected boolean isFinished() {
        return !collector.isOpen();
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
