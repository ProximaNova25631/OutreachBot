package org.firstinspires.ftc.teamcode.common.hardware;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.util.MathUtils;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.common.util.wrappers.MotorActuator;
import org.firstinspires.ftc.teamcode.common.util.wrappers.MotorEncoder;
import org.firstinspires.ftc.teamcode.common.util.wrappers.slidermotors;

import java.util.HashMap;
import java.util.List;
import java.util.function.IntSupplier;

public class Slide_Test {
    private IntSupplier sliderTicks;
    private double manualSliderInput = 0.0;
    private static final String SLIDER_MOTOR_1 = "slidesMotor1";
    public DcMotorEx sliderMotor1;
    public MotorEncoder SliderEncoder;
    public MotorActuator sliderActuator;
    private int sliderTargetPosition = Config.SLIDER_CLOSE_INTAKE_POSITION;
    public HashMap<Sensors.SensorType,Object> sensorValues;
    public List<LynxModule> modules;
    public static Slide_Test instance = null;

    public void init(HardwareMap hardwareMap) {
        sensorValues = new HashMap<>();
        sensorValues.put(Sensors.SensorType.SLIDE_ENCODER, 0);
        sliderMotor1 = hardwareMap.get(DcMotorEx.class, "SLIDER_MOTOR_1");
        SliderEncoder = new MotorEncoder(new MotorEx(hardwareMap, "SLIDER_MOTOR_1").encoder);
        sliderActuator = new MotorActuator(
                () -> intSubscriber(Sensors.SensorType.SLIDE_ENCODER), sliderMotor1)
                .setPIDController(new PIDController(0.005, 0.0, 0.0))
                .setFeedfoward(0.15)
                .setErrorTolerance(30)
                .setLimit(400);
        SliderEncoder.encoder.reset();
        modules = hardwareMap.getAll(LynxModule.class);
        for (LynxModule m : modules) {
            m.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }
        sliderTicks = () -> intSubscriber(Sensors.SensorType.SLIDE_ENCODER);
    }

    public void read() {
        sensorValues.put(Sensors.SensorType.SLIDE_ENCODER, SliderEncoder.getPosition());
    }
    public void periodic() {
        sliderActuator.setCurrentPosition(sliderTicks.getAsInt());
        if (Math.abs(manualSliderInput) > 0.05) {
            sliderActuator.periodic(manualSliderInput);
            sliderActuator.setTargetPosition(sliderTicks.getAsInt());
        } else {
            sliderActuator.periodic();

        }
    }
    public void write () {
        sliderActuator.write();
    }
    public void setManualSliderInput(double input) {
        this.manualSliderInput = MathUtils.clamp(input, -1, sliderTicks.getAsInt());
    }
    public void setTargetPosition(int targetPosition) {
        sliderTargetPosition = targetPosition;
    }

    public double doubleSubscriber(Sensors.SensorType topic) {
        Object value = sensorValues.getOrDefault(topic, 0.0);
        if (value instanceof Integer) {
            return ((Integer) value).doubleValue();
        } else if (value instanceof Double) {
            return (Double) value;
        } else {
            throw new ClassCastException();
        }
    }

    public int intSubscriber(Sensors.SensorType topic) {
        Object value = sensorValues.getOrDefault(topic, 0);
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof Double) {
            return ((Double) value).intValue();
        } else {
            throw new ClassCastException();
        }
    }
    public void clearBulkCache() {
        for (LynxModule m : modules) {
            m.clearBulkCache();
        }
    }
    public static Slide_Test getInstance() {
        if (instance == null) {
            instance = new Slide_Test();
        }
        return instance;
    }


    }
