package org.firstinspires.ftc.teamcode.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.common.Combos;
import org.firstinspires.ftc.teamcode.common.Slides;
import org.firstinspires.ftc.teamcode.common.MecanumDrive;
import org.firstinspires.ftc.teamcode.common.RobotHardware;


// TODO
// 1. Move MecanumDrive to RobotHardware
// 2. Move combos to combos class
// 3. Move Linkage and Sliders to common/modules/Linkage.java, Sliders.java

@TeleOp
public class OutreachTeleop extends LinearOpMode {
    private static final RobotHardware robot = RobotHardware.getInstance();
    private static final MecanumDrive mecanumDrive = new MecanumDrive(false, 0.75);
    private int sliderCurrentPosition = RobotHardware.SLIDER_BOTTOM_POSITION;

    @Override
    public void runOpMode() throws InterruptedException {
        Combos combos = new Combos();
        Slides slides = new Slides();
        robot.init(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            mecanumDrive.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            moveSliders();
            moveLinkage();

            if (gamepad1.dpad_right) {
                robot.claw.setPosition(RobotHardware.CLAW_OPEN_POSITION);
            }

            if (gamepad1.left_bumper) {
                combos.grabAndTiltUpCombo();
            } else if (gamepad1.right_bumper) {
                combos.tiltDownCombo();
            }

            telemetry.update();
        }
    }

    private void moveSliders() {
        // Check to see if we are close enough to the current target
        robot.slides.stopSlidesIfClose();

        if (gamepad1.y) {
            robot.slides.slidesMoveToTop();
        }

        if (gamepad1.a) {
            robot.slides.slidesMoveToBottom();
        }

        telemetry.addData("Slider Encoder Position", robot.slides.getSliderCurrentPosition());
        telemetry.addData("Slider Desired Position", robot.slides.getSliderTargetPosition());
        telemetry.addData("Slider power", robot.slides.getSliderPower());
    }

    private void moveLinkage() {
        // Check to see if we are close enough to the current target
        robot.linkage.stopLinkageIfClose();

        if (gamepad1.dpad_up) {
            robot.linkage.moveLinkageUp();
        }

        if (gamepad1.dpad_down) {
            robot.linkage.moveLinkageDown();
        }

        telemetry.addData("Linkage Encoder Position", robot.linkage.getCurrentPosition());
        telemetry.addData("Linkage Desired Position", robot.linkage.getTargetPosition());
        telemetry.addData("Linkage power", robot.linkage.getPower());
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
}