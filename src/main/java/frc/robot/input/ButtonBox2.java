/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.input;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Contains elevator buttons, collecting
 */
public class ButtonBox2 extends Joystick {

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

    public JoystickButton firePiston, climbPiston, openCollector;

    public Rocker manualElevator, manualRoller;

    public ButtonBox2(int channel) {
        super(channel);
        firePiston = new JoystickButton(this, 1);
        climbPiston = new JoystickButton(this, 2);
        openCollector = new JoystickButton(this, 3);

        manualElevator = new Rocker(this, 5, 4);
        manualRoller = new Rocker(this, 7, 6);

    }

}
