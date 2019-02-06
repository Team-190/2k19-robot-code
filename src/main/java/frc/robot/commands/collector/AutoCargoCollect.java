/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.collector;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.elevator.ElevateToHeight;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Elevator;

public class AutoCargoCollect extends CommandGroup {
    Collector collector = Collector.getInstance();
    /**
     * Add your docs here
     */
    public AutoCargoCollect() {
        // Move elevator to height 0
        addSequential(new ElevateToHeight(Elevator.Position.Ground));
        // Roll roller until limit switch
        addSequential(new RollIntakeForCargo(Collector.Speed.IN));
        // Move elevator to height 1
        addSequential(new ElevateToHeight(Elevator.Position.CargoShipCargo));
    }
}
