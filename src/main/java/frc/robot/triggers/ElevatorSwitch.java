/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.subsystems.Elevator;

/**
 * Triggered when elevator resetting switch is hit.
 */
public class ElevatorSwitch extends Trigger {
    @Override
    public boolean get() {
        return Elevator.getInstance().getSwitch();
    }
}
