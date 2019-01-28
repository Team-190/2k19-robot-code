/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Climber;

public class RollToHAB extends Command {
    Climber climber = Climber.getInstance();

    public RollToHAB() {
        requires(climber);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        climber.setSpeed(ControlMode.PercentOutput, 1);
    }

    @Override
    protected boolean isFinished() {
        return climber.isChassisACSTriggered();
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
