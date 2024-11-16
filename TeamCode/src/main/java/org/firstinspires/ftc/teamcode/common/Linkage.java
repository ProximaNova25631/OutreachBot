package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Linkage {
    private static RobotHardware robot = RobotHardware.getInstance();
    private int linkageCurrentPosition = RobotHardware.TILT_DOWN_POSITION;

    public void moveLinkageUp() {
        linkageCurrentPosition = RobotHardware.TILT_UP_POSITION;
        robot.tiltMotor.setTargetPosition(linkageCurrentPosition);
        robot.tiltMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.tiltMotor.setPower(0.5);
    }

    public void moveLinkageDown() {
        linkageCurrentPosition = RobotHardware.TILT_DOWN_POSITION;
        robot.tiltMotor.setTargetPosition(linkageCurrentPosition);
        robot.tiltMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.tiltMotor.setPower(0.3);
    }
}
