package org.firstinspires.ftc.teamcode.common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotHardware {
    private static RobotHardware instance = null;

    private static final String FRONT_LEFT_MOTOR = "frontLeftMotor";
    private static final String FRONT_RIGHT_MOTOR = "frontRightMotor";
    private static final String BACK_LEFT_MOTOR = "backLeftMotor";
    private static final String BACK_RIGHT_MOTOR = "backRightMotor";
    private static final String TILT_MOTOR = "TiltMotor";
    private static final String SLIDER_MOTOR = "SliderMotor";
    private static final String LEFT_ARM = "leftArm";
    private static final String RIGHT_ARM = "rightArm";
    private static final String CLAW = "Claw";
    private static final String ELBOW = "Elbow";
    private static final String WRIST = "Wrist";

    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor backLeftMotor;
    public DcMotor backRightMotor;
    public DcMotor tiltMotor;
    public DcMotor sliderMotor;
    public Servo leftArm;
    public Servo rightArm;
    public Servo claw;
    public Servo elbow;
    public Servo wrist;

    public static double armUpPosition = 0.40;
    public static double armDownPosition = 0.568;

    public static int sliderTopPosition = -500;
    public static int sliderBottomPosition = 10;


    public static double clawOpenPosition = 0.5;
    public static double clawClosedPosition = 1.0;

    public static double elbowUpPosition = 0.55;
    public static double elbowDownPosition = 0.25;

    public static int TILT_UP_POSITION = 600;
    public static int TILT_DOWN_POSITION = 0;

    public static RobotHardware getInstance() {
        if (instance == null) {
            instance = new RobotHardware();
        }

        return instance;
    }

    public void init(HardwareMap hardwareMap) {
        frontLeftMotor = hardwareMap.dcMotor.get(FRONT_LEFT_MOTOR);
        frontRightMotor = hardwareMap.dcMotor.get(FRONT_RIGHT_MOTOR);
        backLeftMotor = hardwareMap.dcMotor.get(BACK_LEFT_MOTOR);
        backRightMotor = hardwareMap.dcMotor.get(BACK_RIGHT_MOTOR);
        tiltMotor = hardwareMap.dcMotor.get(TILT_MOTOR);
        sliderMotor = hardwareMap.dcMotor.get(SLIDER_MOTOR);

        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        // Servo declaration
        leftArm = hardwareMap.servo.get(LEFT_ARM);
        rightArm = hardwareMap.servo.get(RIGHT_ARM);
        claw = hardwareMap.servo.get(CLAW);
        elbow = hardwareMap.servo.get(ELBOW);
        wrist = hardwareMap.servo.get(WRIST);

        tiltMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        tiltMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sliderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Initialize positions
        rightArm.setPosition(armUpPosition);
        claw.setPosition(clawClosedPosition);
        elbow.setPosition(elbowUpPosition);
    }
}

