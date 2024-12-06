package org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.common.hardware.RobotHardware;

public class ElbowCommand extends InstantCommand {
    public ElbowCommand(RobotHardware.ElbowPosition position) {
        super(
                () -> RobotHardware.getInstance().elbowActuator.setTargetPosition(position)
        );
    }
}
