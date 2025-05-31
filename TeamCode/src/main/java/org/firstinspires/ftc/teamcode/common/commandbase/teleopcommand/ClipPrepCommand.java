package org.firstinspires.ftc.teamcode.common.commandbase.teleopcommand;

//import com.arcrobotics.ftclib.command.SequentialCommandGroup;
//import com.arcrobotics.ftclib.command.WaitCommand;
//
//import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.ArmCommand;
//import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.ClawCommand;
//import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.ElbowCommand;
//import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.LinkageCommand;
//import org.firstinspires.ftc.teamcode.common.commandbase.subsystemcommand.SlidesCommand;
//import org.firstinspires.ftc.teamcode.common.hardware.Config;

//public class ClipPrepCommand extends SequentialCommandGroup {
//    public ClipPrepCommand() {
//        super(
//            new LinkageCommand(Config.TILT_UP_POSITION),
//            new WaitCommand(500),
//            new SlidesCommand(Config.SLIDER_BOTTOM_POSITION),
//            new ClawCommand(Config.CLAW_OPEN_POSITION),
//            new ArmCommand(Config.ARM_WALL_INTAKE_POSITION),
//            new ElbowCommand(Config.ELBOW_CLIP_INTAKE_POSITION)
//        );
//    }
//}
