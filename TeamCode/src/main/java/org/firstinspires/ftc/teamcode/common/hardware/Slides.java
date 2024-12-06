package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides {
    private static final String SLIDER_MOTOR_1 = "slidesMotor1";
    private static final String SLIDER_MOTOR_2 = "slidesMotor2";
    public DcMotor sliderMotor1;
    public DcMotor sliderMotor2;

    public static int SLIDER_TOP_POSITION = -530;
    public static int SLIDER_BOTTOM_POSITION = 0;

    private int sliderCurrentPosition = SLIDER_BOTTOM_POSITION;

    public void init(HardwareMap hardwareMap) {
        sliderMotor1 = hardwareMap.dcMotor.get(SLIDER_MOTOR_1);
        sliderMotor1.setDirection(DcMotorSimple.Direction.REVERSE);
        sliderMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sliderMotor2 = hardwareMap.dcMotor.get(SLIDER_MOTOR_2);
        sliderMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void slidesMoveToTop() {
        sliderCurrentPosition = SLIDER_TOP_POSITION;
        sliderMotor1.setTargetPosition(sliderCurrentPosition);
        sliderMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sliderMotor1.setPower(0.5);
        sliderMotor2.setTargetPosition(sliderCurrentPosition);
        sliderMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sliderMotor2.setPower(0.5);
    }

    public void slidesMoveToBottom() {
        sliderCurrentPosition = SLIDER_BOTTOM_POSITION;
        sliderMotor1.setTargetPosition(sliderCurrentPosition);
        sliderMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sliderMotor1.setPower(0.3);
        sliderMotor2.setTargetPosition(sliderCurrentPosition);
        sliderMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sliderMotor2.setPower(0.3);
    }

    public void stopSlidesIfClose() {
        int slidercurrentPosition = sliderMotor1.getCurrentPosition();
        if (Math.abs(slidercurrentPosition - sliderCurrentPosition) <= 10) {
            sliderMotor1.setPower(0.0);
            sliderMotor2.setPower(0.0);
        }
    }

    public String getSliderCurrentPosition() {
        return String.format("motor1 = %d, motor2 = %d", sliderMotor1.getCurrentPosition(), sliderMotor2.getCurrentPosition());
    }

    public String getSliderTargetPosition() {
        return String.format("motor1 = %d, motor2 = %d", sliderMotor1.getTargetPosition(), sliderMotor2.getTargetPosition());
    }

    public String getSliderPower() {
        return String.format("motor1 = %f, motor2 = %f", sliderMotor1.getPower(), sliderMotor2.getPower());
    }

}