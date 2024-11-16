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
    private static final String LEFT_ARM = "leftArm";
    private static final String RIGHT_ARM = "rightArm";
    private static final String CLAW = "Claw";
    private static final String ELBOW = "Elbow";
    private static final String WRIST = "Wrist";

    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor backLeftMotor;
    public DcMotor backRightMotor;
    public Servo leftArm;
    public Servo rightArm;
    public Servo claw;
    public Servo elbow;
    public Servo wrist;
    public Linkage linkage;
    public Slides slides;


    public static double ARM_UP_POSITION = 0.20;
    public static double ARM_DOWN_POSITION = 0.28;

    public static int SLIDER_TOP_POSITION = -500;
    public static int SLIDER_BOTTOM_POSITION = 10;


    public static double CLAW_OPEN_POSITION = 0.5;
    public static double CLAW_CLOSED_POSITION = 1.0;

    public static double ELBOW_UP_POSITION = 0.55;
    public static double ELBOW_DOWN_POSITION = 0.25;


    public static RobotHardware getInstance() {
        if (instance == null) {
            instance = new RobotHardware();
        }

        return instance;
    }

    public void init(HardwareMap hardwareMap) {
        linkage = new Linkage();
        linkage.init(hardwareMap);

        frontLeftMotor = hardwareMap.dcMotor.get(FRONT_LEFT_MOTOR);
        frontRightMotor = hardwareMap.dcMotor.get(FRONT_RIGHT_MOTOR);
        backLeftMotor = hardwareMap.dcMotor.get(BACK_LEFT_MOTOR);
        backRightMotor = hardwareMap.dcMotor.get(BACK_RIGHT_MOTOR);

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


        // Initialize positions
        leftArm.setDirection(Servo.Direction.REVERSE);
        leftArm.setPosition(ARM_UP_POSITION);
        claw.setPosition(CLAW_CLOSED_POSITION);
        elbow.setPosition(ELBOW_UP_POSITION);
    }
}

