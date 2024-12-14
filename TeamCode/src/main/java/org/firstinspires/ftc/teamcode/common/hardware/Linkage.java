package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Linkage {
    private static final String TILT_MOTOR = "TiltMotor";
    public DcMotor tiltMotor;

    public static int TILT_UP_POSITION = 950;
    public static int TILT_DOWN_POSITION = 0;

    private int linkageTargetPosition = TILT_DOWN_POSITION;

    public void init(HardwareMap hardwareMap) {
        tiltMotor = hardwareMap.dcMotor.get(TILT_MOTOR);
        tiltMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        tiltMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setTargetPosition(int position) {
        linkageTargetPosition = position;
    }

    public void write() {
        if (Math.abs(tiltMotor.getCurrentPosition() - linkageTargetPosition) <= 20) {
            tiltMotor.setPower(0.0);
        } else {
            tiltMotor.setTargetPosition(linkageTargetPosition);
            tiltMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            tiltMotor.setPower(0.5);
        }
    }

    public int getTargetPosition() {
        return tiltMotor.getTargetPosition();
    }

    public int getCurrentPosition() {
        return tiltMotor.getCurrentPosition();
    }

    public double getPower() {
        return tiltMotor.getPower();
    }
}
