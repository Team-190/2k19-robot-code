/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.collector.CollectHatch;
import frc.robot.commands.collector.ReleaseHatch;
import frc.robot.commands.drivetrain.DriveSequence;
import frc.robot.commands.drivetrain.TurnToHeading;
import frc.robot.models.PathfinderSequence;

public class RightRocketAuto extends CommandGroup {
    /**
     * Right start auto
     */
    public RightRocketAuto() {
        addSequential(new DriveSequence(PathfinderSequence.RightStartFarRocket, true));
        addSequential(new ReleaseHatch());
        addSequential(new TurnToHeading(150));

        addParallel(new CollectHatch());
        addSequential(new DriveSequence(PathfinderSequence.RightFarLoading, false));
        
        // //TODO: wack into wall and get hatch panel, trial and error for that

        // addSequential(new DriveSequence(PathfinderSequence.LoadingRightCloseRocket, false));
        // addSequential(new ExtakeHatchPanel());

        // //TODO: add to this if somehow under 15 seconds
    }
}
