package org.firstinspires.ftc.teamcode.common.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.common.subsystem.IntakeDeliverySubsystem;
import org.firstinspires.ftc.teamcode.common.util.wrappers.ServoActuator;
import org.firstinspires.ftc.teamcode.common.util.wrappers.WServo;

public class RobotHardware {
    private static RobotHardware instance = null;

    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor backLeftMotor;
    public DcMotor backRightMotor;

    public ServoActuator armActuator;
    public ServoActuator elbowActuator;
    public ServoActuator clawActuator;
    public ServoActuator wristActuator;

    public Linkage linkage;
    public Slides slides;

    public IntakeDeliverySubsystem intakeDeliverySubsystem;
    public static final MecanumDrive mecanumDrive = new MecanumDrive(true, 0.75);

    public static RobotHardware getInstance() {
        if (instance == null) {
            instance = new RobotHardware();
        }

        return instance;
    }

    public void init(HardwareMap hardwareMap) {
        frontLeftMotor = hardwareMap.dcMotor.get(Config.FRONT_LEFT_MOTOR);
        frontRightMotor = hardwareMap.dcMotor.get(Config.FRONT_RIGHT_MOTOR);
        backLeftMotor = hardwareMap.dcMotor.get(Config.BACK_LEFT_MOTOR);
        backRightMotor = hardwareMap.dcMotor.get(Config.BACK_RIGHT_MOTOR);

        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        linkage = new Linkage();
        linkage.init(hardwareMap);
        slides = new Slides();
        slides.init(hardwareMap);

        armActuator = new ServoActuator(new WServo(hardwareMap.get(Servo.class, Config.LEFT_ARM)));
        //        new WServo(hardwareMap.get(Servo.class, Config.RIGHT_ARM), Servo.Direction.REVERSE)
        elbowActuator = new ServoActuator(new WServo(hardwareMap.get(Servo.class, Config.ELBOW)));
        clawActuator = new ServoActuator(new WServo(hardwareMap.get(Servo.class, Config.CLAW)));
        wristActuator = new ServoActuator(new WServo(hardwareMap.get(Servo.class, Config.WRIST)));

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

