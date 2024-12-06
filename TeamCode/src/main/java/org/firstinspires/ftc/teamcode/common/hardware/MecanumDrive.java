package org.firstinspires.ftc.teamcode.common.hardware;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.common.hardware.RobotHardware;

public class MecanumDrive {
    private static RobotHardware robot = RobotHardware.getInstance();
    private boolean useTrigonometric = false;
    private double speedLimit = 1.0;
    private Telemetry telemetry;

    private double frontLeftPower = 0.0;
    private double frontRightPower = 0.0;
    private double backLeftPower = 0.0;
    private double backRightPower = 0.0;

    public MecanumDrive(boolean useTrigonometric, double speedLimit) {
        this.useTrigonometric = useTrigonometric;
        this.speedLimit = speedLimit;
    }

    public void robotCentric(double strafeX, double driveY, double rotate) {
        if (useTrigonometric) {
            double r = Math.hypot(strafeX, driveY);          // Radius (magnitude)
            double robotAngle = Math.atan2(driveY, strafeX);  // Direction (angle)

            // Convert angle to the respective power for each wheel
            frontLeftPower = r * Math.sin(robotAngle + Math.PI / 4) + rotate;
            frontRightPower = r * Math.cos(robotAngle + Math.PI / 4) - rotate;
            backLeftPower = r * Math.cos(robotAngle + Math.PI / 4) + rotate;
            backRightPower = r * Math.sin(robotAngle + Math.PI / 4) - rotate;

            // Normalize power values to ensure they are within [-1.0, 1.0]
            double maxPower = Math.max(Math.abs(frontLeftPower), Math.max(Math.abs(frontRightPower),
                    Math.max(Math.abs(backLeftPower), Math.abs(backRightPower))));
            if (maxPower > 1.0) {
                frontLeftPower /= maxPower;
                frontRightPower /= maxPower;
                backLeftPower /= maxPower;
                backRightPower /= maxPower;
            }
        } else {
            double y = -driveY; // Remember, Y stick value is reversed
            double x = strafeX * 1.1; // Counteract imperfect strafing
            double rx = rotate;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            frontLeftPower = (y + x + rx) / denominator;
            backLeftPower = (y - x + rx) / denominator;
            frontRightPower = (y - x - rx) / denominator;
            backRightPower = (y + x - rx) / denominator;
        }
    }

    public void write() {
        robot.frontLeftMotor.setPower(frontLeftPower * speedLimit);
        robot.backLeftMotor.setPower(backLeftPower * speedLimit);
        robot.frontRightMotor.setPower(frontRightPower* speedLimit);
        robot.backRightMotor.setPower(backRightPower * speedLimit);
    }

    public void stop(){
        robotCentric(0, 0, 0);
    }

    public void setTelemetry(Telemetry telemetry){
        this.telemetry = telemetry;
    }
}
