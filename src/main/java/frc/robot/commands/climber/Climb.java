/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.climber.DeployArms;
import frc.robot.commands.climber.DeployLifters;
import frc.robot.commands.climber.RetractLifters;
import frc.robot.commands.climber.RollToHAB;
import frc.robot.commands.collector.OpenCollector;
import frc.robot.commands.drivetrain.DriveForward;
import frc.robot.commands.drivetrain.DriveToACS;
import frc.robot.commands.elevator.ElevateToHeight;
import frc.robot.subsystems.Elevator;


public class Climb extends CommandGroup {
    /**
     * Triggered by Climb Button
     */
    public Climb() {
        // deploy arms
        addParallel(new DeployArms(), 3);
        // move elevator to ground
        addParallel(new ElevateToHeight(Elevator.Position.Ground));
        // collector to starting position
        addSequential(new OpenCollector(), 3);
        // Parallel
        //      drive left forward until arm left anti-cliff triggered
        //      drive right forward until arm right anti-cliff triggered
        addSequential(new DriveToACS(), 5);
        // trigger lifting pneumatics
        addSequential(new DeployLifters());
        // drive BAG motor on until chassis anti-cliff triggered
        addSequential(new RollToHAB());
        //      Retract pneumatics
        addSequential(new RetractLifters());
        // Drive forward 18"
        addSequential(new DriveForward(), 2);
    }
}
