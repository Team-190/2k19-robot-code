/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SPI;

import com.kauailabs.navx.frc.AHRS;

import frc.robot.models.PairedTalonSRX;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
    static Drivetrain drivetrain = null;
    
    AHRS navx;
    private final int leftOne = 0,
        leftTwo = 1,
        rightOne = 2,
        rightTwo = 3;
    PairedTalonSRX left, right;
    
    private Drivetrain() {
        navx = new AHRS(SPI.Port.kMXP);
        left = new PairedTalonSRX(leftOne, leftTwo);
        right = new PairedTalonSRX(rightOne, rightTwo);

    }

    public static Drivetrain getInstance() {
        if (drivetrain == null) drivetrain = new Drivetrain();
        return drivetrain;
    }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
