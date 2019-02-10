/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class ButtonBox extends Joystick {

    public class Rocker {
        private JoystickButton forward, backward;
        public Rocker(Joystick joystick, int forwardChannel, int backwardChannel) {
            forward = new JoystickButton(joystick, forwardChannel);
            backward = new JoystickButton(joystick, backwardChannel);
        }
        
        public JoystickButton getForward() {
            return forward;
        }

        public JoystickButton getBackward() {
            return backward;
        }

    }

    public JoystickButton
        upperRocketCargo,
        middleRocketCargo,
        lowRocketCargo,
        cargoShipCargo,

        upperRocketPanel,
        middleRocketPanel,
        lowRocketPanel,
        cargoShipPanel,
        // End elevator heights

        intakeCargo,
        intakePanel,
        extakeCargo,
        extakePanel,
        // End collector

        cLimb,
        firePiston;
    
    public Rocker
        manualElevator,
        manualCollector, // pistons
        manualRoller, 
        manualClimber; // cylinders
        

    public ButtonBox(int channel) {
        super(channel);
        //TODO: fix button values for real button box
        upperRocketCargo = new JoystickButton(this, 1);
        middleRocketCargo = new JoystickButton(this, 1);
        lowRocketCargo = new JoystickButton(this, 2);
        cargoShipCargo = new JoystickButton(this, 3);

        upperRocketPanel = new JoystickButton(this, 4);
        middleRocketPanel = new JoystickButton(this, 5);
        lowRocketPanel = new JoystickButton(this, 6);
        cargoShipPanel = new JoystickButton(this, 7);
        // End elevator heights

        intakeCargo = new JoystickButton(this, 8);
        intakePanel = new JoystickButton(this, 9);
        extakeCargo = new JoystickButton(this, 10);
        extakePanel = new JoystickButton(this, 11);
        // End collector

        cLimb = new JoystickButton(this, 12);
        firePiston = new JoystickButton(this, 13);
        manualElevator = new Rocker(this, 14, 15);
        manualCollector = new Rocker(this, 16, 17);
        manualRoller = new Rocker(this, 18, 19);
        manualClimber = new Rocker(this, 20, 21);

    }

    
}
