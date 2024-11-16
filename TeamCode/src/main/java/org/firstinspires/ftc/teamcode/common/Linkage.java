package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Linkage {
    private static final String TILT_MOTOR = "TiltMotor";
    public DcMotor tiltMotor;

    public static int TILT_UP_POSITION = 600;
    public static int TILT_DOWN_POSITION = 0;

    private int linkageCurrentPosition = TILT_DOWN_POSITION;

    public void init(HardwareMap hardwareMap) {
        tiltMotor = hardwareMap.dcMotor.get(TILT_MOTOR);
        tiltMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        tiltMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void moveLinkageUp() {
        linkageCurrentPosition = TILT_UP_POSITION;
        tiltMotor.setTargetPosition(linkageCurrentPosition);
        tiltMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        tiltMotor.setPower(0.5);
    }

    public void moveLinkageDown() {
        linkageCurrentPosition = TILT_DOWN_POSITION;
        tiltMotor.setTargetPosition(linkageCurrentPosition);
        tiltMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        tiltMotor.setPower(0.3);
    }
}
