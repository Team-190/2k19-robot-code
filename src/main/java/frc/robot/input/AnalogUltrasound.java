/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.input;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * Add your docs here.
 */
public class AnalogUltrasound extends AnalogInput{
    final double VOLTS_PER_INCH = 6;
    public AnalogUltrasound(int channel) {
        super(channel);
    }

    public double getInches() {
        return getVoltage() / VOLTS_PER_INCH;
    }
}
