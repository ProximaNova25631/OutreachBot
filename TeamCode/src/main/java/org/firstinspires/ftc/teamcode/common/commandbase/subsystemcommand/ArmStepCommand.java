package org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.common.hardware.RobotHardware;

public class ArmStepCommand extends InstantCommand{
    public ArmStepCommand(double step){
        super(
                ()-> RobotHardware.getInstance().armActuator.setTargetPosition(step)
        );
    }
}

