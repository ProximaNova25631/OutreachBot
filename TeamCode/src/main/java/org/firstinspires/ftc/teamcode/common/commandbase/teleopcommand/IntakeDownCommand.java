package org.firstinspires.ftc.teamcode.common.commandbase.teleopcommand;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.ArmCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.ClawCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.ElbowCommand;
import org.firstinspires.ftc.teamcode.common.hardware.RobotHardware;

public class IntakeDownCommand extends SequentialCommandGroup {
    public IntakeDownCommand() {
        super(
                new ElbowCommand(RobotHardware.ElbowPosition.DOWN),
                new ClawCommand(RobotHardware.ClawPosition.OPEN),
                new ArmCommand(RobotHardware.ArmPosition.DOWN)
        );
    }
}
