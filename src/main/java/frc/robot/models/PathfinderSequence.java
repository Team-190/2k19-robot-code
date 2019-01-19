package frc.robot.models;

public enum PathfinderSequence {
    LeftStartCloseRocket,
    CloseRocketLeftLoading,
    LoadingLeftCloseRocket,

    LeftStartMiddleRocket,
    MiddleRocketLeftLoading,
    LoadingLeftMiddleRocket,

    LeftStartFarRocket,
    FarRocketLeftLoading,
    LoadingLeftFarRocket,

    RightStartCloseRocket,
    CloseRocketRightLoading,
    LoadingRightCloseRocket,

    RightStartMiddleRocket,
    MiddleRocketRightLoading,
    LoadingRightMiddleRocket,

    RightStartFarRocket,
    FarRocketRightLoading,
    LoadingRightFarRocket;

    private final String sequenceDirectory = "/home/lvuser/sequences";

    public String getLeftCSVName() {
        return sequenceDirectory + name() + ".left.pf1.csv";
    }

    public String getRightCSVName() {
        return sequenceDirectory + name() + ".right.pf1.csv";
    }
}