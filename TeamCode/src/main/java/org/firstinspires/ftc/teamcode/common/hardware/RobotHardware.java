package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.subsystem.IntakeDeliverySubsystem;
import org.firstinspires.ftc.teamcode.common.util.wrappers.ActuatorPositions;
import org.firstinspires.ftc.teamcode.common.util.wrappers.ServoActuator;
import org.firstinspires.ftc.teamcode.common.util.wrappers.WServo;

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

    public WServo leftArm;
    public WServo rightArm;
    public ServoActuator armActuator;

    public ServoActuator elbowActuator;
    public ServoActuator clawActuator;

    public WServo wrist;
    public ServoActuator wristActuator;

    public Linkage linkage;
    public Slides slides;

    public IntakeDeliverySubsystem intakeDeliverySubsystem;
    public static final MecanumDrive mecanumDrive = new MecanumDrive(true, 0.75);

    private static double ARM_UP_POSITION = 0.20;
    private static double ARM_DOWN_POSITION = 0.60;
    private static double ARM_DOWN_GRAB_POSITION = 0.65;
    private static double CLAW_OPEN_POSITION = 0.2;
    private static double CLAW_CLOSED_POSITION = 1.0;
    private static double ELBOW_UP_POSITION = 0.35;
    private static double ELBOW_MID_POSITION = 0.35;
    private static double ELBOW_DOWN_POSITION = 0.15;

    public enum ClawPosition {
        OPEN,
        CLOSED
    }

    public enum ElbowPosition {
        UP,
        MID,
        DOWN
    }

    public enum ArmPosition {
        UP,
        DOWN,
        DOWN_GRAB
    }


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

        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        linkage = new Linkage();
        linkage.init(hardwareMap);

        slides = new Slides();
        slides.init(hardwareMap);

        leftArm = new WServo(hardwareMap.get(Servo.class, LEFT_ARM));
        leftArm.setDirection(Servo.Direction.FORWARD);
//        rightArm = new WServo(hardwareMap.get(Servo.class, RIGHT_ARM));
//        rightArm.setDirection(Servo.Direction.REVERSE);
        armActuator = new ServoActuator(leftArm);
        armActuator.setActuatorPositions(new ActuatorPositions<ArmPosition>(ARM_UP_POSITION) {{
           put(ArmPosition.UP, ARM_UP_POSITION);
           put(ArmPosition.DOWN, ARM_DOWN_POSITION);
           put(ArmPosition.DOWN_GRAB, ARM_DOWN_GRAB_POSITION);
        }});

        elbowActuator = new ServoActuator(new WServo(hardwareMap.get(Servo.class, ELBOW)));
        elbowActuator.setActuatorPositions(new ActuatorPositions<ElbowPosition>(ELBOW_MID_POSITION) {{
            put(ElbowPosition.UP, ELBOW_UP_POSITION);
            put(ElbowPosition.MID, ELBOW_MID_POSITION);
            put(ElbowPosition.DOWN, ELBOW_DOWN_POSITION);
        }});

        clawActuator = new ServoActuator(new WServo(hardwareMap.get(Servo.class, CLAW)));
        clawActuator.setActuatorPositions(new ActuatorPositions<ClawPosition>(CLAW_CLOSED_POSITION)  {{
            put(ClawPosition.OPEN, CLAW_OPEN_POSITION);
            put(ClawPosition.CLOSED, CLAW_CLOSED_POSITION);
        }});

        wrist = new WServo(hardwareMap.get(Servo.class, WRIST));
        wristActuator = new ServoActuator(wrist);

        intakeDeliverySubsystem = new IntakeDeliverySubsystem();
        intakeDeliverySubsystem.write();
    }

    public void read() {
    }

    public void write() {
        mecanumDrive.write();
        intakeDeliverySubsystem.write();
    }

    public void periodic() {
        intakeDeliverySubsystem.periodic();
    }
}

