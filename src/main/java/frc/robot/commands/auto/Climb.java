/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Climb extends CommandGroup {
    /**
     * Triggered by Climb Button
     */
    public Climb() {
        // collector to starting position
        // deploy arms
        // Parallel
        //      drive left forward until arm left anti-cliff triggered
        //      drive right forward until arm right anti-cliff triggered
        // trigger lifting pneumatics
        // wait until arm limit switch, bottom chassis triggered
        // drive BAG motor on until chassis anti-cliff triggered
        // Parallel
        //      Stop BAG
        //      Retract pneumatics
        // wait until arm limit switch, upper chassis triggered
        // Drive forward 18"
    }
}
