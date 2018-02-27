package com.team6133.frc2018.auto.modes;

import com.team6133.frc2018.Constants;
import com.team6133.frc2018.auto.AutoModeBase;
import com.team6133.frc2018.auto.AutoModeEndedException;
import com.team6133.frc2018.auto.AutonPathSettings;
import com.team6133.frc2018.auto.actions.*;
import com.team6133.lib.util.SensorTarget;
import com.team6133.lib.util.math.Rotation2d;
import edu.wpi.first.wpilibj.Timer;

import java.util.Arrays;

public class Option_StartRight_EndScoreSwitch extends AutoModeBase {
    AutonPathSettings path1 = new AutonPathSettings(FACE_LEFT, 0,150, new SensorTarget(SensorTarget.Sensor.LeftIRPD, 150, true), 0.5);
    AutonPathSettings path2 = new AutonPathSettings(FACE_RIGHT, -12, 0, new SensorTarget(SensorTarget.Sensor.Ultra, Constants.SWITCH_SIDE_DISTANCE_INCHES, false), .5);
    @Override
    protected void routine() throws AutoModeEndedException {
        System.out.println("Option_StartRight_EndScoreSwitch()");
        double start = Timer.getFPGATimestamp();
        runAction(new ResetStartingPoseAction(Rotation2d.fromDegrees(FACE_RIGHT)));
        runAction(new DrivePathAction(path1));
        System.out.println("Path 1/2 Time:\t" + (Timer.getFPGATimestamp() - start));

        runAction( new ParallelAction(Arrays.asList(new Action[] {
                new DrivePathAction(path2),
                new PrepLaunchSwitchAction(),
        })));
        System.out.println("Path 2/2 Time:\t" + (Timer.getFPGATimestamp() - start));
        runAction(new LaunchSwitchAction());
        System.out.println("Score Switch Time:\t" + (Timer.getFPGATimestamp() - start));

        runAction(new WaitAction(15));
    }
}
