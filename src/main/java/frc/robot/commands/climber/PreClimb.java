/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.robot.subsystems.Elevator;
import frc.robot.commands.collector.OpenCollector;
import frc.robot.commands.elevator.ElevateToHeight;

/**
 * Does all the work that needs to be done before robot is lined up
 */
public class PreClimb extends CommandGroup {
    /**
     * Add your docs here.
     */
    public PreClimb() {
        addParallel(new TimeArmSpin(2));
        // move elevator to ground
        addParallel(new ElevateToHeight(Elevator.Position.Ground));
        // collector to starting position
        addSequential(new OpenCollector(), 3);
    }
}
