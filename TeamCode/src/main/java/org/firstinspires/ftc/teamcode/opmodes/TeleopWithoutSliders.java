package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.ClawCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.teleopcommand.IntakeDownCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.teleopcommand.IntakeUpCommand;
import org.firstinspires.ftc.teamcode.common.hardware.Config;
import org.firstinspires.ftc.teamcode.common.hardware.RobotHardware;

@TeleOp
public class TeleopWithoutSliders extends CommandOpMode {
    private static RobotHardware robot = RobotHardware.getInstance();
    private GamepadEx gamepadEx1;

    @Override
    public void initialize() {
        gamepadEx1 = new GamepadEx(gamepad1);
        robot.init(hardwareMap);
        robot.mecanumDrive.setSpeedLimit(0.25);

        // Set up commands to execute from gamepad controllers
        gamepadEx1.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whenPressed(new IntakeDownCommand());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new IntakeUpCommand());
        gamepadEx1.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whenPressed(new ClawCommand(Config.CLAW_OPEN_POSITION));
    }

    @Override
    public void run() {
        CommandScheduler.getInstance().run();
        robot.read();
        robot.periodic();
        robot.write();

        robot.mecanumDrive.robotCentric(gamepadEx1.getLeftY(), gamepadEx1.getLeftX(), gamepadEx1.getRightX());
    }
}
