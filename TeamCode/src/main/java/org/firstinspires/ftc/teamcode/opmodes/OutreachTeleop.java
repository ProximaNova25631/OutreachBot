//package org.firstinspires.ftc.teamcode.opmodes;
//
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Two-Motor Tank Drive", group = "TeleOp")
//public class OutreachTeleop extends OpMode {
//
//    // Declare the two motors
//    private DcMotor leftMotor;
//    private DcMotor rightMotor;
//
//    @Override
//    public void init() {
//        // Initialize the motors using the hardware map
//        leftMotor = hardwareMap.get(DcMotor.class, "left_drive");
//        rightMotor = hardwareMap.get(DcMotor.class, "right_drive");
//
//        // Set the direction of one motor to reverse if necessary
//        leftMotor.setDirection(DcMotor.Direction.REVERSE);
//        rightMotor.setDirection(DcMotor.Direction.FORWARD);
//    }
//
//    @Override
//    public void loop() {
//        // Tank drive logic: use gamepad joysticks to control the motors
//        double leftPower = -gamepad1.left_stick_y * 0.5;  // Invert because up is negative on the joystick
//        double rightPower = -gamepad1.right_stick_y * 0.5;
//
//        leftMotor.setPower(leftPower);
//        rightMotor.setPower(rightPower);
//
//        // Telemetry for debugging (shows the power being applied to each motor)
//        telemetry.addData("Left Power", leftPower);
//        telemetry.addData("Right Power", rightPower);
//        telemetry.update();
//    }
//}

package org.firstinspires.ftc.teamcode.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class OutreachTeleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        DcMotor tiltMotor = hardwareMap.dcMotor.get("TiltMotor");
        tiltMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        tiltMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        int TiltUpPosition;
        int TiltDownPosition;

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        TiltUpPosition = 5;
        TiltDownPosition = -665;

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            if (gamepad1.x) {
                tiltMotor.setTargetPosition(TiltUpPosition);
                tiltMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                tiltMotor.setPower(0.5);
            }

            if (gamepad1.b) {
                tiltMotor.setTargetPosition(TiltDownPosition);
                tiltMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                tiltMotor.setPower(0.3);
            }

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);


            // Get the current position of the armMotor
            double position = tiltMotor.getCurrentPosition();

            // Get the target position of the armMotor
            double desiredPosition = tiltMotor.getTargetPosition();

            // Show the position of the tiltMotor on telemetry
            telemetry.addData("Encoder Position", position);

            // Show the target position of the tiltMotor on telemetry
            telemetry.addData("Desired Position", desiredPosition);

            telemetry.update();

        }
    }
}
