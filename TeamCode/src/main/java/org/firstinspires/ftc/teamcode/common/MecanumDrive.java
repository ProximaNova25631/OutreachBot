package org.firstinspires.ftc.teamcode.common;

public class MecanumDrive {
    private static RobotHardware robot = RobotHardware.getInstance();
    private boolean useTrigonometric = false;
    private double speedLimit = 1.0;

    public MecanumDrive(boolean useTrigonometric, double speedLimit) {
        this.useTrigonometric = useTrigonometric;
        this.speedLimit = speedLimit;
    }

    public void drive(double strafeX, double driveY, double rotate) {

        double frontLeftPower = 0.0;
        double frontRightPower = 0.0;
        double backLeftPower = 0.0;
        double backRightPower = 0.0;

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

        robot.frontLeftMotor.setPower(frontLeftPower * speedLimit);
        robot.backLeftMotor.setPower(backLeftPower * speedLimit);
        robot.frontRightMotor.setPower(frontRightPower* speedLimit);
        robot.backRightMotor.setPower(backRightPower * speedLimit);
    }
}
