/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.climber.DeployArms;
import frc.robot.commands.climber.DeployLifters;
import frc.robot.commands.collector.OpenCollector;
import frc.robot.commands.drivetrain.DriveToACS;


public class Climb extends CommandGroup {
    /**
     * Triggered by Climb Button
     */
    public Climb() {
        // deploy arms
        addParallel(new DeployArms(), 3);
        // collector to starting position
        addSequential(new OpenCollector(), 3);
        // Parallel
        //      drive left forward until arm left anti-cliff triggered
        //      drive right forward until arm right anti-cliff triggered
        addSequential(new DriveToACS(), 3);
        // trigger lifting pneumatics
        addSequential(new DeployLifters(), 10);
        // drive BAG motor on until chassis anti-cliff triggered
        // Parallel
        //      Stop BAG
        //      Retract pneumatics
        // wait until arm limit switch, upper chassis triggered
        // Drive forward 18"
    }
}
