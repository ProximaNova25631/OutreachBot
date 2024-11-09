package org.firstinspires.ftc.teamcode.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

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
        DcMotor sliderMotor = hardwareMap.dcMotor.get("SliderMotor");
        Servo clawServo = hardwareMap.servo.get("clawServo");
        Servo armServo = hardwareMap.servo.get("armServo");
        Servo rotationClawServo = hardwareMap.servo.get("rotationClawServo");
        tiltMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        tiltMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        int TiltUpPosition;
        int TiltDownPosition;
        int TopPosition;
        int BottomPosition;

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
        TopPosition = 0;
        BottomPosition = 0;

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            //Linkage Programming
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
            //Claw Programming
            // check to see if we need to move the servo.
            if(gamepad1.left_bumper) {
                // move to 0 degrees.
                clawServo.setPosition(0);
            } else if (gamepad1.left_trigger > 0) {
                // move to 90 degrees.
                clawServo.setPosition(0.5);
            }
            telemetry.addData("Claw Servo Position", clawServo.getPosition());
            telemetry.addData("Claw Status", "Running");
            telemetry.update();

            //Arm Programming
            if(gamepad1.right_bumper) {
                // move to 0 degrees.
                //set to correct degrees
                armServo.setPosition(0);
            } else if (gamepad1.right_trigger > 0.0) {
                // move to 90 degrees.
                //set to correct degrees
                armServo.setPosition(0.5);
            }
            telemetry.addData("Arm Servo Position", armServo.getPosition());
            telemetry.addData("Arm Status", "Running");
            telemetry.update();

            //Claw Rotation Programming
            if (gamepad1.dpad_left) {
                // move to 0 degrees.
                //set to correct degrees
                rotationClawServo.setPosition(0);
            } else if (gamepad1.dpad_up) {
                // move to 90 degrees.
                //set to correct degrees
                rotationClawServo.setPosition(0.5);
            } else if (gamepad1.dpad_right) {
                // move to 180 degrees.
                //set to correct degrees
                rotationClawServo.setPosition(1);
            }

            telemetry.addData("Claw Rotation Servo Position", rotationClawServo.getPosition());
            telemetry.addData("Claw Rotation Status", "Running");
            telemetry.update();

            //Sliders Programming
            if (gamepad1.y) {
                sliderMotor.setTargetPosition(TopPosition);
                sliderMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                sliderMotor.setPower(0.5);

            }

            if (gamepad1.a) {
                sliderMotor.setTargetPosition(BottomPosition);
                sliderMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                sliderMotor.setPower(0.3);
            }

            // Get the current position of the armMotor
            double position2 = sliderMotor.getCurrentPosition();

            // Get the target position of the armMotor
            double desiredPosition2 = sliderMotor.getTargetPosition();

            // Show the position of the tiltMotor on telemetry
            telemetry.addData("Slider Encoder Position", position2);

            // Show the target position of the tiltMotor on telemetry
            telemetry.addData("Slider Desired Position", desiredPosition2);

            telemetry.update();


        }
    }
}