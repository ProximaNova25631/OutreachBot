package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.MecanumDrive;
import org.firstinspires.ftc.teamcode.common.RobotHardware;

@TeleOp
public class TeleopWithoutSliders extends LinearOpMode {
    private static RobotHardware robot = RobotHardware.getInstance();
    private static MecanumDrive mecanumDrive = new MecanumDrive(false, 0.25);

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            mecanumDrive.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

            if (gamepad1.dpad_right) {
                robot.claw.setPosition(robot.CLAW_OPEN_POSITION);
            }

            if (gamepad1.left_bumper) {
                grabAndTiltUpCombo();
            } else if (gamepad1.right_bumper) {
                tiltDownCombo();
            }

            telemetry.update();
        }
    }

    private void tiltDownCombo() {
        robot.elbow.setPosition(robot.ELBOW_DOWN_POSITION);
        robot.claw.setPosition(robot.CLAW_OPEN_POSITION);
        robot.rightArm.setPosition(robot.ARM_DOWN_POSITION);
    }

    private void grabAndTiltUpCombo() throws InterruptedException {
        robot.rightArm.setPosition(robot.ARM_DOWN_POSITION + .05);
        Thread.sleep(500);
        robot.claw.setPosition(robot.CLAW_CLOSED_POSITION);
        Thread.sleep(500);
        robot.elbow.setPosition(robot.ELBOW_UP_POSITION);
        robot.rightArm.setPosition(robot.ARM_UP_POSITION);
    }
}
