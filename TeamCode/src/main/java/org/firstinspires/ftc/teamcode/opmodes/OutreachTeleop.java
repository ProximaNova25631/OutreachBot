package org.firstinspires.ftc.teamcode.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.RobotHardware;

@TeleOp
public class OutreachTeleop extends LinearOpMode {
    private static RobotHardware robot = RobotHardware.getInstance();

    private static double speedLimit = 0.25;

    private int sliderCurrentPosition = robot.sliderBottomPosition;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            moveRobot();

            moveSliders();

            if (gamepad1.dpad_right) {
                robot.claw.setPosition(robot.clawOpenPosition);
            }

            if (gamepad1.left_bumper) {
                grabAndTiltUpCombo();
            } else if (gamepad1.right_bumper) {
                tiltDownCombo();
            }
//            if (gamepad1.x) {
//                tiltMotor.setPower(0.5);
//                tiltMotor.setTargetPosition(TiltUpPosition);
//                tiltMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            }
//
//            if (gamepad1.b) {
//                tiltMotor.setPower(0.3);
//                tiltMotor.setTargetPosition(TiltDownPosition);
//                tiltMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            }

//
//            if (gamepad1.dpad_left) {
//                wristPosition += 0.05;
//                if (wristPosition > 0.8) {
//                    wristPosition = 0.8;
//                }
//            } else if (gamepad1.dpad_right) {
//                wristPosition -= 0.05;
//                if (wristPosition < 0.2) {
//                    wristPosition = 0.2;
//                }
//            }

            telemetry.update();
        }
    }

    private void moveRobot() {
        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        robot.frontLeftMotor.setPower(frontLeftPower * speedLimit);
        robot.backLeftMotor.setPower(backLeftPower * speedLimit);
        robot.frontRightMotor.setPower(frontRightPower* speedLimit);
        robot.backRightMotor.setPower(backRightPower * speedLimit);
    }

    private void moveSliders() {

        // Check to see if we are close enough to the current target
        int currentPosition = robot.sliderMotor.getCurrentPosition();
        if (Math.abs(currentPosition - sliderCurrentPosition) <= 10) {
            robot.sliderMotor.setPower(0.0);
        }
        telemetry.addData("debug currentPosition", currentPosition);
        telemetry.addData("debug sliderCurrentPosition", sliderCurrentPosition);

        if (gamepad1.y) {
            sliderCurrentPosition = robot.sliderTopPosition;
            robot.sliderMotor.setTargetPosition(sliderCurrentPosition);
            robot.sliderMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.sliderMotor.setPower(0.5);
        }

        if (gamepad1.a) {
            sliderCurrentPosition = robot.sliderBottomPosition;
            robot.sliderMotor.setTargetPosition(sliderCurrentPosition);
            robot.sliderMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.sliderMotor.setPower(0.3);
        }

        telemetry.addData("Slider Encoder Position", robot.sliderMotor.getCurrentPosition());
        telemetry.addData("Slider Desired Position", robot.sliderMotor.getTargetPosition());
    }

    private void tiltDownCombo() {
        robot.elbow.setPosition(robot.elbowDownPosition);
        robot.claw.setPosition(robot.clawOpenPosition);
        robot.rightArm.setPosition(robot.armDownPosition);
    }

    private void grabAndTiltUpCombo() throws InterruptedException {
        robot.rightArm.setPosition(robot.armDownPosition + .05);
        Thread.sleep(500);
        robot.claw.setPosition(robot.clawClosedPosition);
        Thread.sleep(500);
        robot.elbow.setPosition(robot.elbowUpPosition);
        robot.rightArm.setPosition(robot.armUpPosition);
    }
}