package org.firstinspires.ftc.teamcode.common.commandbase.teleopcommand;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.LinkageCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.SlidesCommand;
import org.firstinspires.ftc.teamcode.common.hardware.Config;

public class DeliveryUpCommand extends SequentialCommandGroup {
    public DeliveryUpCommand() {
        super(
                new SlidesCommand(Config.SLIDER_BOTTOM_POSITION),
                new WaitCommand(500),
                new LinkageCommand(Config.TILT_UP_POSITION),
                new WaitCommand(500),
                new LinkageCommand(Config.TILT_UP_POSITION)
        );
    }
}
