package org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand;

import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.common.hardware.RobotHardware;

public class ClawCommand extends InstantCommand {
    public ClawCommand(RobotHardware.ClawPosition position) {
        super(
                ()-> RobotHardware.getInstance().clawActuator.setTargetPosition(position)
        );
    }
}
