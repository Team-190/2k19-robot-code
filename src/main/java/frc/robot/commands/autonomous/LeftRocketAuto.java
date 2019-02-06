/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.collector.ExtakeHatchPanel;
import frc.robot.commands.drivetrain.DriveSequence;
import frc.robot.models.PathfinderSequence;

public class LeftRocketAuto extends CommandGroup {
    /**
     * Add your docs here.
     */
    public LeftRocketAuto() {
        addSequential(new DriveSequence(PathfinderSequence.LeftStartFarRocket));
        addSequential(new ExtakeHatchPanel());

        addSequential(new DriveSequence(PathfinderSequence.LeftFarLoading));
        //TODO: wack into wall and get hatch panel, trial and error for that

        addSequential(new DriveSequence(PathfinderSequence.LoadingLeftCloseRocket));
        addSequential(new ExtakeHatchPanel());

        //TODO: add to this if somehow under 15 seconds
    }
}
