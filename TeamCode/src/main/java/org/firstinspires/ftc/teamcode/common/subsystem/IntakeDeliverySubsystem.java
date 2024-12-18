package org.firstinspires.ftc.teamcode.common.subsystem;

import org.firstinspires.ftc.teamcode.common.hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.common.util.wrappers.SubsystemCore;

public class IntakeDeliverySubsystem extends SubsystemCore {

    private RobotHardware robot = RobotHardware.getInstance();

    @Override
    public void read() {
    }

    @Override
    public void periodic() {
    }

    @Override
    public void write() {
        robot.armActuator.write();
        robot.elbowActuator.write();
        robot.wristActuator.write();
        robot.clawActuator.write();
        robot.slides.write();
        robot.linkage.write();
    }

    @Override
    public void reset() {
    }

    public void stepArmElbowActuators(double step) {
        if (step > 0.0) {
            if (robot.armActuator.getPrevTargetPosition() + step > robot.armActuator.getMaxPosition()) {
                robot.elbowActuator.stepTargetPosition(step);
            } else {
                robot.armActuator.stepTargetPosition(step);
            }
        } else if (step < 0.0) {
            if (robot.elbowActuator.getPrevTargetPosition() + step < robot.elbowActuator.getMinPosition()) {
                robot.armActuator.stepTargetPosition(step);
            } else {
                robot.elbowActuator.stepTargetPosition(step);
            }
        }
    }
}
