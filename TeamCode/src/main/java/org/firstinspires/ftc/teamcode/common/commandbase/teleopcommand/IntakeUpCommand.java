package org.firstinspires.ftc.teamcode.common.commandbase.teleopcommand;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.ArmCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.ClawCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.ElbowCommand;
import org.firstinspires.ftc.teamcode.common.hardware.RobotHardware;

public class IntakeUpCommand extends SequentialCommandGroup {
    public IntakeUpCommand() {
        super(
                new ArmCommand(RobotHardware.ArmPosition.DOWN_GRAB),
                new WaitCommand(500),
                new ClawCommand(RobotHardware.ClawPosition.OPEN),
                new WaitCommand(500),
                new ElbowCommand(RobotHardware.ElbowPosition.UP),
                new ArmCommand(RobotHardware.ArmPosition.UP)
        );
    }
}
