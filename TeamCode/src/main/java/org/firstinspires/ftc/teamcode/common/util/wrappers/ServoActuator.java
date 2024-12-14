package org.firstinspires.ftc.teamcode.common.util.wrappers;

import java.util.ArrayList;
import java.util.List;

public class ServoActuator {
    private List<WServo> servoList = new ArrayList<>();
    private double prevTargetPosition = 0.0;
    private double targetPosition = 0.0;
    private double minPosition = 0.0;
    private double maxPosition = 1.0;

    public ServoActuator(WServo... servos) {
        for (WServo s : servos) {
            this.servoList.add(s);
        }
    }

    public void write() {
        if (Math.abs(targetPosition - prevTargetPosition) > 0.005) {
            for (WServo s : servoList) {
                s.setPosition(targetPosition);
                prevTargetPosition = targetPosition;
            }
        }
    }

    public void setTargetPosition(double position) {
        targetPosition = position;
    }

    public double getTargetPosition() {
        return targetPosition;
    }

    public ServoActuator setMinPosition(double minPosition) {
        this.minPosition = minPosition;
        return this;
    }

    public ServoActuator setMaxPosition(double maxPosition) {
        this.maxPosition = maxPosition;
        return this;
    }

    public void stepTargetPosition(double step) {
        targetPosition = prevTargetPosition + step;
        if (targetPosition < minPosition) {
            targetPosition = minPosition;
        }
        if (targetPosition > maxPosition) {
            targetPosition = maxPosition;
        }
    }
}
