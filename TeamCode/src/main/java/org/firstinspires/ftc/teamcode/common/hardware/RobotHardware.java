//package org.firstinspires.ftc.teamcode.common.hardware;
//import com.arcrobotics.ftclib.kinematics.Odometry;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
//
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
//import org.firstinspires.ftc.teamcode.common.Odometry.SparkFunOdometry;
//import org.firstinspires.ftc.teamcode.common.subsystem.IntakeDeliverySubsystem;
//import org.firstinspires.ftc.teamcode.common.util.wrappers.ServoActuator;
//import org.firstinspires.ftc.teamcode.common.util.wrappers.WServo;
//
//public class RobotHardware {
//    private static RobotHardware instance = null;
//
//    public DcMotor frontLeftMotor;
//    public DcMotor frontRightMotor;
//    public DcMotor backLeftMotor;
//    public DcMotor backRightMotor;
//
//    public ServoActuator armActuator;
//    public ServoActuator elbowActuator;
//    public ServoActuator clawActuator;
//    public ServoActuator wristActuator;
//
//    public Slides slides;
////    public SparkFunOdometry SparkFunOdometry;
//
//    public static RobotHardware getInstance() {
//        if (instance == null) {
//            instance = new RobotHardware();
//        }
//
//        return instance;
//    }
//
//    public void init(HardwareMap hardwareMap) {
////        SparkFunOdometry = hardwareMap.get(SparkFunOdometry.class, Config.SPARK_FUN_ODOMETRY);
//
//        slides = new Slides();
//        slides.init(hardwareMap);
//
//
//    }
//
//
//}
//
