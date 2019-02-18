/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.collector;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.subsystems.Collector;

public class ExtakeHatchPanel extends TimedCommand {
    Collector collector = Collector.getInstance();

    public ExtakeHatchPanel() {
        super(0.25);
        requires(collector);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        collector.setEjector(true);
        // System.out.println("Eject");
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        collector.setEjector(false);
    }
}
