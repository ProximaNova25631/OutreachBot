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
        Servo leftArm = hardwareMap.servo.get("leftArm");
        Servo rightArm = hardwareMap.servo.get("rightArm");
        Servo Claw = hardwareMap.servo.get("Claw");
        Servo Elbow = hardwareMap.servo.get("Elbow");
        Servo Wrist = hardwareMap.servo.get("Wrist");
        tiltMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        tiltMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sliderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        int TiltUpPosition;
        int TiltDownPosition;
        int TopPosition;
        int BottomPosition;

        double armPosition = 0.2;
        double armDownPosition = 0.7;
        double armUpPosition = 0.8;
        leftArm.setPosition(armUpPosition);

//        double clawPosition = 0.0;
        double clawOpenPosition = 0.5;
        double clawClosedPosition = 1.0;
        Claw.setPosition(clawClosedPosition);

        double elbowUpPosition = 0.55;
        double elbowDownPosition = 0.25;
        Elbow.setPosition(elbowUpPosition);

        double elbowPosition = 0.0;
//        Elbow.setPosition(elbowPosition);
//
        double wristPosition = 0.0;
//        Wrist.setPosition(wristPosition);

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
        TopPosition = -1600;
        BottomPosition = 10;

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            //Linkage Programming
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            if (gamepad1.x) {
                tiltMotor.setPower(0.5);
                tiltMotor.setTargetPosition(TiltUpPosition);
                tiltMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }

            if (gamepad1.b) {
                tiltMotor.setPower(0.3);
                tiltMotor.setTargetPosition(TiltDownPosition);
                tiltMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
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
//

            // GOOD
            if (gamepad1.dpad_up) {
                leftArm.setPosition(armUpPosition);
            } else if (gamepad1.dpad_down) {
                leftArm.setPosition(armDownPosition);
            }

            // GOOD
            if (gamepad1.dpad_left) {
                Claw.setPosition(clawClosedPosition);
            } else if (gamepad1.dpad_right) {
                Claw.setPosition(clawOpenPosition);
            }

//            telemetry.addData("claw position", clawPosition);

            if (gamepad1.left_bumper) {
                Elbow.setPosition(elbowUpPosition);
            } else if (gamepad1.right_bumper) {
                Elbow.setPosition(elbowDownPosition);
            }

            telemetry.addData("elbow position: ", elbowPosition);
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

            telemetry.addData("wrist position: ", wristPosition);

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