package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Autonomous Tank Drive", group = "Autonomous")
public class OutreachAuto extends LinearOpMode {

    // Declare the two motors
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void runOpMode() {
        // Initialize the motors
        leftMotor = hardwareMap.get(DcMotor.class, "left_drive");
        rightMotor = hardwareMap.get(DcMotor.class, "right_drive");

        // Set the direction of one motor to reverse (if necessary)
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the start button to be pressed
        waitForStart();

        // Step 1: Drive forward for 2 seconds
        driveForward(0.5);  // Set power to 50%
        sleep(2000);        // Run for 2000 milliseconds (2 seconds)

        // Step 2: Stop the robot for 1 second
        stopDriving();
        sleep(1000);        // Pause for 1 second

        // Step 3: Turn right by running only the left motor for 1 second
        turnRight(0.5);     // Set power to 50% on the left motor
        sleep(1000);        // Turn for 1 second

        // Step 4: Stop the robot
        stopDriving();
    }

    // Method to drive forward by setting both motors to the same power
    private void driveForward(double power) {
        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }

    // Method to turn right by only driving the left motor
    private void turnRight(double power) {
        leftMotor.setPower(power);
        rightMotor.setPower(0);  // Stop the right motor to turn
    }

    // Method to stop both motors
    private void stopDriving() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}
