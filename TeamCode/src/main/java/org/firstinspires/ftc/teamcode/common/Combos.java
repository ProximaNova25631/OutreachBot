package org.firstinspires.ftc.teamcode.common;

public class Combos {
    private static RobotHardware robot = RobotHardware.getInstance();

    public void tiltDownCombo() {
        robot.elbow.setPosition(robot.ELBOW_DOWN_POSITION);
        robot.claw.setPosition(robot.CLAW_OPEN_POSITION);
        robot.leftArm.setPosition(robot.ARM_DOWN_POSITION);
    }

    public void grabAndTiltUpCombo() throws InterruptedException {
        robot.leftArm.setPosition(robot.ARM_DOWN_POSITION + .05);
        Thread.sleep(500);
        robot.claw.setPosition(robot.CLAW_CLOSED_POSITION);
        Thread.sleep(500);
        robot.elbow.setPosition(robot.ELBOW_UP_POSITION);
        robot.leftArm.setPosition(robot.ARM_UP_POSITION);
    }
}
