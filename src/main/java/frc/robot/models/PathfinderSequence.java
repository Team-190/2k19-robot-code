package frc.robot.models;

public enum PathfinderSequence {

    LeftStartFarRocket,
    LeftFarLoading,
    LoadingLeftCloseRocket,

    RightStartFarRocket,
    RightFarLoading,
    LoadingRightCloseRocket;

    private final String sequenceDirectory = "/home/lvuser/sequences";

    public String getLeftCSVName() {
        return sequenceDirectory + name() + ".left.pf1.csv";
    }

    public String getRightCSVName() {
        return sequenceDirectory + name() + ".right.pf1.csv";
    }
}