package org.firstinspires.ftc.teamcode.opmodes;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.ClawCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.teleopcommand.IntakeDownCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.teleopcommand.IntakeUpCommand;
import org.firstinspires.ftc.teamcode.common.hardware.RobotHardware;

@TeleOp
public class OutreachTeleop extends CommandOpMode {
    private static final RobotHardware robot = RobotHardware.getInstance();

    private GamepadEx gamepadEx1;
    private GamepadEx gamepadEx2;

    @Override
    public void initialize() {
        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        robot.init(hardwareMap);

        // Set up commands to execute from gamepad controllers
        gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new IntakeDownCommand());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new IntakeUpCommand());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(new ClawCommand(RobotHardware.ClawPosition.OPEN));
    }

    @Override
    public void run() {
        CommandScheduler.getInstance().run();
        robot.read();
        robot.periodic();
        robot.write();

        robot.mecanumDrive.robotCentric(-gamepadEx1.getLeftY(), gamepadEx1.getLeftX(), gamepadEx1.getRightX());
    }

//    @Override
//    public void runOpMode() throws InterruptedException {
//        Combos combos = new Combos();
//        robot.init(hardwareMap);
//
//        waitForStart();
//
//        if (isStopRequested()) return;
//
//        while (opModeIsActive()) {
//            mecanumDrive.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
//            moveSliders();
//            moveLinkage();
//
//            if (gamepad1.dpad_right) {
//                robot.claw.setPosition(RobotHardware.CLAW_OPEN_POSITION);
//            }
//
//            if (gamepad1.left_bumper) {
//                combos.grabAndTiltUpCombo();
//            } else if (gamepad1.right_bumper) {
//                combos.tiltDownCombo();
//            }
//
//            telemetry.update();
//        }
//    }

    private void moveSliders() {
        // Check to see if we are close enough to the current target
        robot.slides.stopSlidesIfClose();

        if (gamepad1.y) {
            robot.slides.slidesMoveToTop();
        }

        if (gamepad1.a) {
            robot.slides.slidesMoveToBottom();
        }

        telemetry.addData("Slider Position", robot.slides.getSliderCurrentPosition());
        telemetry.addData("Slider Desired", robot.slides.getSliderTargetPosition());
        telemetry.addData("Slider Power", robot.slides.getSliderPower());
        telemetry.addData("ZeroPowerBehavior", robot.slides.sliderMotor2.getZeroPowerBehavior());
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