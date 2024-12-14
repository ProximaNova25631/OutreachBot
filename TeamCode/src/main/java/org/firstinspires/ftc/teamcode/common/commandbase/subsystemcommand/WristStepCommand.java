package org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.common.hardware.RobotHardware;

public class WristStepCommand extends InstantCommand {
    public WristStepCommand(double step) {
        super(
                ()-> RobotHardware.getInstance().wristActuator.stepTargetPosition(step)
        );
    }
}
