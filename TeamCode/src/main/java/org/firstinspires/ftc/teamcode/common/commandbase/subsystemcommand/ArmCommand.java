package org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.common.hardware.RobotHardware;

public class ArmCommand extends InstantCommand {
    public ArmCommand(double position) {
        super(
                () -> RobotHardware.getInstance().armActuator.setTargetPosition(position)
        );
    }
}
