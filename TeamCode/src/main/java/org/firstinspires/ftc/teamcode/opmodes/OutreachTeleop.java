package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Two-Motor Tank Drive", group = "TeleOp")
public class OutreachTeleop extends OpMode {

    // Declare the two motors
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void init() {
        // Initialize the motors using the hardware map
        leftMotor = hardwareMap.get(DcMotor.class, "left_drive");
        rightMotor = hardwareMap.get(DcMotor.class, "right_drive");

        // Set the direction of one motor to reverse if necessary
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void loop() {
        // Tank drive logic: use gamepad joysticks to control the motors
        double leftPower = -gamepad1.left_stick_y * 0.5;  // Invert because up is negative on the joystick
        double rightPower = -gamepad1.right_stick_y * 0.5;

        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);

        // Telemetry for debugging (shows the power being applied to each motor)
        telemetry.addData("Left Power", leftPower);
        telemetry.addData("Right Power", rightPower);
        telemetry.update();
    }
}
