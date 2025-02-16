package org.firstinspires.ftc.teamcode.opmodes;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.ClawCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.LinkageCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.SlidesCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.teleopcommand.ClipDeliveryCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.teleopcommand.ClipIntakeCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.teleopcommand.ClipPrepCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.teleopcommand.DeliveryUpCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.teleopcommand.IntakeDownCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.teleopcommand.IntakeUpCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.teleopcommand.DeliveryDownCommand;
import org.firstinspires.ftc.teamcode.common.hardware.Config;
import org.firstinspires.ftc.teamcode.common.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.common.util.joystick.Util;

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
                .whenPressed(new ClawCommand(Config.CLAW_OPEN_POSITION));
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whenPressed(new ClawCommand(Config.CLAW_CLOSED_POSITION));
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whenPressed(new DeliveryUpCommand());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whenPressed(new DeliveryDownCommand());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(new SlidesCommand(Config.SLIDER_TOP_POSITION));
        gamepadEx1.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new SlidesCommand(Config.SLIDER_CLOSE_INTAKE_POSITION));

        gamepadEx2.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new ClawCommand(Config.CLAW_CLOSED_POSITION));
        gamepadEx2.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new ClawCommand(Config.CLAW_OPEN_POSITION));
        gamepadEx2.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new ClipPrepCommand());
        gamepadEx2.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(new ClipIntakeCommand());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(new ClipDeliveryCommand());
    }

    @Override
    public void run() {
        CommandScheduler.getInstance().run();
        robot.read();
        robot.periodic();
        robot.write();

        robot.mecanumDrive.setSpeedLimit(1.0);
        robot.mecanumDrive.robotCentric(gamepadEx1.getLeftY(), gamepadEx1.getLeftX(),
                Util.joystickScalar(-gamepadEx1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) + gamepadEx1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER), 0.01));

        if (Math.abs(gamepadEx1.getRightX()) > 0.1) {
            if (gamepadEx1.getRightX() > 0) {
                robot.wristActuator.stepTargetPosition(-0.015);
            } else {
                robot.wristActuator.stepTargetPosition(0.015);
            }
        }

        if (Math.abs(gamepadEx1.getRightY()) > 0.5) {
            if (gamepadEx1.getRightY() > 0) {
                robot.intakeDeliverySubsystem.stepArmElbowActuators(0.01);
            } else {
                robot.intakeDeliverySubsystem.stepArmElbowActuators(-0.01);
            }
        }

        telemetry.addData("Claw position", robot.clawActuator.getTargetPosition());
        telemetry.addData("Arm position", robot.armActuator.getTargetPosition());
        telemetry.addData("Elbow position", robot.elbowActuator.getTargetPosition());
        telemetry.addData("GamepadEx1.rightbumper", gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).get());

        telemetry.addData("Tilt current position", robot.linkage.getCurrentPosition());
        telemetry.addData("Tilt target position", robot.linkage.getTargetPosition());
        telemetry.addData("Tilt current power", robot.linkage.getPower());

        telemetry.addData("Slides current position", robot.slides.getSliderCurrentPosition());
        telemetry.addData("Slides target position", robot.slides.getSliderTargetPosition());
        telemetry.addData("Slides current power", robot.slides.getSliderPower());
        telemetry.addData("Wrist Position", robot.wristActuator.getTargetPosition());


        telemetry.update();
    }
}