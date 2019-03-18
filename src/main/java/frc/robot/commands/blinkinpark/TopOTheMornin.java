/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.blinkinpark;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.BlinkinPark;
import frc.robot.subsystems.BlinkinPark.Song;

public class TopOTheMornin extends Command {
  public TopOTheMornin() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(BlinkinPark.getInstance());
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    BlinkinPark.getInstance().playSong(Song.Color1LightChase);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (DriverStation.getInstance().getMatchTime() < 30) {
      BlinkinPark.getInstance().playSong(Song.Color1HeartbeatMedium);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }
}
