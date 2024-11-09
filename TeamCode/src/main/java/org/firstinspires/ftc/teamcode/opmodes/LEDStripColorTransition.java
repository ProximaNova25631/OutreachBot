package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "LED Test", group = "TeleOp")
public class LEDStripColorTransition extends LinearOpMode {

    private RevBlinkinLedDriver ledStrip;

    @Override
    public void runOpMode() {
        // Initialize hardware
        ledStrip = hardwareMap.get(RevBlinkinLedDriver.class, "ledStrip");

        // Wait for the start button to be pressed
        waitForStart();

        // Loop to change colors
        while (opModeIsActive()) {
            ledStrip.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE_VIOLET);
            sleep(1000);
            ledStrip.setPattern(RevBlinkinLedDriver.BlinkinPattern.VIOLET);
            sleep(1000);
//            ledStrip.setPattern(RevBlinkinLedDriver.BlinkinPattern.TWINKLES_RAINBOW_PALETTE);
  //          sleep(2500);
    //        ledStrip.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE_VIOLET);
      //      sleep(1000);
        //    ledStrip.setPattern(RevBlinkinLedDriver.BlinkinPattern.VIOLET);
          //  sleep(1000);
            //ledStrip.setPattern(RevBlinkinLedDriver.BlinkinPattern.SINELON_RAINBOW_PALETTE);
            //sleep(2500);
        }
    }
}