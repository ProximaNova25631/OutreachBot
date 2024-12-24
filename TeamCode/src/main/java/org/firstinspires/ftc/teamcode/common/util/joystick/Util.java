package org.firstinspires.ftc.teamcode.common.util.joystick;

public class Util {
    public static double joystickScalar(double num, double min) {
        return joystickScalar(num, min, 0.6, 1.5);
    }

    public static double joystickScalar(double n, double m, double l, double a) {
        return Math.signum(n) * m
                + (1 - m) *
                (Math.abs(n) > l ?
                        Math.pow(Math.abs(n), Math.log(l / a) / Math.log(l)) * Math.signum(n) :
                        n / a);
    }
}
