/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  private static Climber climber;

  private AnalogInput leftACS;
  private AnalogInput rightACS;
  private AnalogInput chassisACS;

  private DigitalInput trolleyUp;
  private DigitalInput armsDown;

  private WPI_TalonSRX motor;

  private DoubleSolenoid solenoid;


  private Climber() {
    leftACS = new AnalogInput(0);
    rightACS = new AnalogInput(1);
    chassisACS = new AnalogInput(2);

    trolleyUp = new DigitalInput(0);
    armsDown = new DigitalInput(1);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public static Climber getInstance() {
    if (climber == null) {
      climber = new Climber();
    }
    return climber;
  }
}
