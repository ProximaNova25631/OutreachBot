package org.firstinspires.ftc.teamcode.common.commandbase.teleopcommand;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.LinkageCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.SlidesCommand;
import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.WristStepCommand;
import org.firstinspires.ftc.teamcode.common.hardware.Config;
import org.firstinspires.ftc.teamcode.common.hardware.RobotHardware;

public class DeliveryUpCommand extends SequentialCommandGroup {
    public DeliveryUpCommand() {
        super(
                new SlidesCommand(Config.SLIDER_BOTTOM_POSITION),
                new WaitCommand(500),
                new LinkageCommand(Config.TILT_UP_POSITION),
                new WaitCommand(500),
                new LinkageCommand(Config.TILT_UP_POSITION),
                new WaitCommand(500),
                new InstantCommand(() -> RobotHardware.getInstance().wristActuator.setTargetPosition(Config.WRIST_INIT_POS))
//                new WristStepCommand(Config.WRIST_INIT_POS)
        );
    }
}
