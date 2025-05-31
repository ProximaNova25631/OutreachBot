package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.common.hardware.Slide_Test;
@TeleOp
@Config
public class SliderMotorTest extends LinearOpMode {
    private Slide_Test robot = Slide_Test.getInstance();
    private GamepadEx gamepadEx1;
    private FtcDashboard dashboard;

    public static double P = 0;
    public static double I = 0;
    public static double D = 0;
    public static int target = 0;
    public static double feedforward = 0;


    @Override
    public void runOpMode() throws InterruptedException {
        gamepadEx1 = new GamepadEx(gamepad1);
        dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        robot.init(hardwareMap);
        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            robot.clearBulkCache();
            robot.read();
            robot.periodic();
            robot.write();
            robot.sliderActuator.updatePIDCoefficients(P, 0, D);
            robot.sliderActuator.setFeedfoward(feedforward);
            robot.sliderActuator.setTargetPosition(target);
            telemetry.addData("slider position: ", robot.sliderActuator.getPosition());
            telemetry.update();
        }
    }
}
