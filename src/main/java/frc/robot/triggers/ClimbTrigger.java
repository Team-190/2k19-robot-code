/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.triggers;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.OI;
import frc.robot.subsystems.Climber;

/**
 * Add your docs here.
 */
public class ClimbTrigger extends Trigger {
    @Override
    public boolean get() {
        boolean preClimbPressed = Climber.getInstance().getClimbPressed();
        boolean buttonPressed = OI.getInstance().buttonBox.climb.get();
        return preClimbPressed && buttonPressed;
    }
}
