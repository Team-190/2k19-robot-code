/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.collector;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.Collector.Speed;

public class TimedRollIntake extends CommandGroup {
    public TimedRollIntake(Speed speed, double time) {
        addSequential(new RollIntake(speed), time);
    }
}
