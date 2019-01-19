/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.input.AttackThree;

/**
 * Add your docs here.
 */
public class OI {
    private static OI oi = null;

    private final int LEFT_STICK = 0,
        RIGHT_STICK = 1;

    public final AttackThree leftStick, rightStick;

    private OI() {
        leftStick = new AttackThree(LEFT_STICK);
        rightStick = new AttackThree(RIGHT_STICK);
    }

    /**
     * Gets the  Drivetrain instance
     * @return the single instance of the class
     */
    public static OI getInstance() {
        if (oi == null)
            oi = new OI();
        return oi;
    }
}
