package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides {
    private static final String SLIDER_MOTOR_1 = "slidesMotor1";
    private static final String SLIDER_MOTOR_2 = "slidesMotor2";
    public DcMotor sliderMotor1;
    public DcMotor sliderMotor2;
    private int sliderTargetPosition = Config.SLIDER_CLOSE_INTAKE_POSITION;

    public void init(HardwareMap hardwareMap) {
        sliderMotor1 = hardwareMap.dcMotor.get(SLIDER_MOTOR_1);
        sliderMotor1.setDirection(DcMotorSimple.Direction.REVERSE);
        sliderMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sliderMotor2 = hardwareMap.dcMotor.get(SLIDER_MOTOR_2);
        sliderMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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

    public void setTargetPosition(int targetPosition) {
        sliderTargetPosition = targetPosition;
    }

    public void write() {
        if (Math.abs(sliderTargetPosition - sliderMotor1.getCurrentPosition()) <= 40) {
            if (sliderTargetPosition == Config.SLIDER_TOP_POSITION) {
                sliderMotor1.setPower(0.2);
                sliderMotor2.setPower(0.2);
            } else {
                sliderMotor1.setPower(0.0);
                sliderMotor2.setPower(0.0);
            }
        } else {
            sliderMotor1.setTargetPosition(sliderTargetPosition);
            sliderMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sliderMotor1.setPower(1.0);
            sliderMotor2.setTargetPosition(sliderTargetPosition);
            sliderMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sliderMotor2.setPower(1.0);
        }
    }
}