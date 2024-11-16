package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides {
    private static final String SLIDER_MOTOR = "SliderMotor";
    public DcMotor sliderMotor;

    private static RobotHardware robot = RobotHardware.getInstance();
    private int sliderCurrentPosition = RobotHardware.SLIDER_BOTTOM_POSITION;

    public void init(HardwareMap hardwareMap) {
        sliderMotor = hardwareMap.dcMotor.get(SLIDER_MOTOR);
        sliderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void slidesMoveToTop() {
        sliderCurrentPosition = RobotHardware.SLIDER_TOP_POSITION;
        sliderMotor.setTargetPosition(sliderCurrentPosition);
        sliderMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sliderMotor.setPower(0.5);
    }

    public void slidesMoveToBottom() {
        sliderCurrentPosition = RobotHardware.SLIDER_BOTTOM_POSITION;
        sliderMotor.setTargetPosition(sliderCurrentPosition);
        sliderMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sliderMotor.setPower(0.3);
    }

    public void stopSlidesIfClose() {
        int slidercurrentPosition = sliderMotor.getCurrentPosition();
        if (Math.abs(slidercurrentPosition - sliderCurrentPosition) <= 10) {
            sliderMotor.setPower(0.0);
        }

    }

    public int getSliderCurrentPosition() {
        return sliderMotor.getCurrentPosition();
    }

    public int getSliderTargetPosition() {
        return sliderMotor.getTargetPosition();
    }

    public double getSliderPower() {
        return sliderMotor.getPower();
    }

}