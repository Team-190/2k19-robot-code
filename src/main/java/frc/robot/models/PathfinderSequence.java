package frc.robot.models;

import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;

public enum PathfinderSequence {

    LeftStartFarRocket,
    LeftFarLoading,
    LoadingLeftCloseRocket,

    RightStartFarRocket,
    RightFarLoading,
    LoadingRightCloseRocket;


    public Trajectory getLeft() {
        return PathfinderFRC.getTrajectory(name() + ".left");
        // return sequenceDirectory + name() + ".left.pf1.csv";
    }

    public Trajectory getRight() {
        return PathfinderFRC.getTrajectory(name() + ".right");
    }
}