/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.autonomous.LeftRocketAuto;
import frc.robot.commands.autonomous.RightRocketAuto;
import frc.robot.subsystems.BlinkinPark;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Vision;

public class Robot extends TimedRobot {
    // TODO: map out ports
    private static final String MANUAL = "Teleop";
    private static final String LEFT_ROCKET = "Left rocket";
    private static final String RIGHT_ROCKET = "Right rocket";
    private static Command autoCommand = null;
    private String autoSelected;
    private final SendableChooser<String> autoChooser = new SendableChooser<>();
    ShuffleboardTab tab;
    NetworkTableEntry elevHeight, zeroSwitch, elevSetpoint;
    NetworkTableEntry hasCargo, angle;
    NetworkTableEntry encoderLeft, encoderRight;
    public static SendableChooser<Double> headingChooser = new SendableChooser<Double>();

    @Override
    public void robotInit() {
        OI.getInstance();
        Collector.getInstance();
        Drivetrain.getInstance();
        Elevator.getInstance();
        BlinkinPark.getInstance();
        // Climber.getInstance();
        Vision.getInstance();
        autoChooser.setDefaultOption(MANUAL, MANUAL);
        autoChooser.addOption(LEFT_ROCKET, LEFT_ROCKET);
        autoChooser.addOption(RIGHT_ROCKET, RIGHT_ROCKET);
        SmartDashboard.putData("Autos", autoChooser);
        tab = Shuffleboard.getTab("Sensors");
        elevHeight = tab.add("Elevator Height", 0).getEntry();
        zeroSwitch = tab.add("Elevator Switch", false).getEntry();
        elevSetpoint = tab.add("Elevator Setpoint", 0).getEntry();
        hasCargo = tab.add("Has Cargo", false).getEntry();
        angle = tab.add("Drivetrain Angle", 0).getEntry();
        encoderLeft = tab.add("Left encoder", 0).getEntry();
        encoderRight = tab.add("Right encoder", 0).getEntry();
        headingChooser.setDefaultOption("0", 0.0);
        headingChooser.addOption("90", 90.0);
        headingChooser.addOption("-90", -90.0);
        // tab.add("Chooser", headingChooser);
    }

    @Override
    public void robotPeriodic() {
        // tab.add("Elevator subsystem", Elevator.getInstance());
        elevHeight.setDouble(Elevator.getInstance().getPosition());
        zeroSwitch.setBoolean(Elevator.getInstance().getSwitch());
        elevSetpoint.setDouble(Elevator.getInstance().getSetpoint());
        hasCargo.setBoolean(Collector.getInstance().hasCargo());
        angle.setDouble(Drivetrain.getInstance().getAngle());
        encoderLeft.setNumber(Drivetrain.getInstance().getLeftPosition());
        encoderRight.setNumber(Drivetrain.getInstance().getRightPosition());
    }

    @Override
    public void autonomousInit() {
        autoSelected = autoChooser.getSelected();
        // System.out.println("Auto selected: " + m_autoSelected);
        switch (autoSelected) {
        case LEFT_ROCKET:
            autoCommand = new LeftRocketAuto();
            break;
        case RIGHT_ROCKET:
            autoCommand = new RightRocketAuto();
            break;
        default:
            // manual, just use joy sticks
            break;
        }
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();

    }

    @Override
    public void teleopInit() {
        if (autoCommand != null)
            autoCommand.cancel();
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
        Scheduler.getInstance().run();
    }
}
