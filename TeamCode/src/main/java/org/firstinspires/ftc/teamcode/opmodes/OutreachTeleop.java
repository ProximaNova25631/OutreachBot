package org.firstinspires.ftc.teamcode.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.common.MecanumDrive;
import org.firstinspires.ftc.teamcode.common.RobotHardware;


// TODO
// 1. Move MecanumDrive to RobotHardware
// 2. Move combos to combos class
// 3. Move Linkage and Sliders to common/modules/Linkage.java, Sliders.java

@TeleOp
public class OutreachTeleop extends LinearOpMode {
    private static RobotHardware robot = RobotHardware.getInstance();
    private static MecanumDrive mecanumDrive = new MecanumDrive(false, 0.25);
    private int sliderCurrentPosition = robot.sliderBottomPosition;
    private int linkageCurrentPosition = robot.TILT_DOWN_POSITION;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            mecanumDrive.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            moveSliders();
            moveLinkage();

            if (gamepad1.dpad_right) {
                robot.claw.setPosition(robot.clawOpenPosition);
            }

            if (gamepad1.left_bumper) {
                grabAndTiltUpCombo();
            } else if (gamepad1.right_bumper) {
                tiltDownCombo();
            }

            telemetry.update();
        }
    }

    private void moveSliders() {
        // Check to see if we are close enough to the current target
        int currentPosition = robot.sliderMotor.getCurrentPosition();
        if (Math.abs(currentPosition - sliderCurrentPosition) <= 10) {
            robot.sliderMotor.setPower(0.0);
        }

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

    private void moveLinkage() {
        // Check to see if we are close enough to the current target
        int currentPosition = robot.tiltMotor.getCurrentPosition();
        if (Math.abs(currentPosition - linkageCurrentPosition) <= 10) {
            robot.tiltMotor.setPower(0.0);
        }

        if (gamepad1.dpad_up) {
            linkageCurrentPosition = robot.TILT_UP_POSITION;
            robot.tiltMotor.setTargetPosition(linkageCurrentPosition);
            robot.tiltMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.tiltMotor.setPower(0.5);
        }

        if (gamepad1.dpad_down) {
            linkageCurrentPosition = robot.TILT_DOWN_POSITION;
            robot.tiltMotor.setTargetPosition(linkageCurrentPosition);
            robot.tiltMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.tiltMotor.setPower(0.3);
        }

        telemetry.addData("Linkage Encoder Position", robot.tiltMotor.getCurrentPosition());
        telemetry.addData("Linkage Desired Position", robot.tiltMotor.getTargetPosition());
        telemetry.addData("Linkage power", robot.tiltMotor.getPower());
    }

    private void moveWrist() {
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