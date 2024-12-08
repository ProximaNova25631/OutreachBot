package org.firstinspires.ftc.teamcode.common.commandbase.teleopcommand;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.ArmCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.ClawCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.ElbowCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.SlidesCommand;
import org.firstinspires.ftc.teamcode.common.hardware.Config;
import org.firstinspires.ftc.teamcode.common.hardware.RobotHardware;

public class IntakeUpCommand extends SequentialCommandGroup {
    public IntakeUpCommand() {
        super(
                new ArmCommand(Config.ARM_DOWN_GRAB_POSITION),
                new WaitCommand(500),
                new ClawCommand(Config.CLAW_CLOSED_POSITION),
                new ElbowCommand(Config.ELBOW_UP_POSITION),
                new ArmCommand(Config.ARM_UP_POSITION),
                new WaitCommand(500),
                new SlidesCommand(Config.SLIDER_BOTTOM_POSITION)
        );
    }
}
