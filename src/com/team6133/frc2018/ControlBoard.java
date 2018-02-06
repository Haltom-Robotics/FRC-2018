package com.team6133.frc2018;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Contains the button mappings for the competition control board. Like the
 * drive code, one instance of the ControlBoard object is created upon startup,
 * then other methods request the singleton ControlBoard instance. Implements
 * the ControlBoardInterface.
 *
 * @see ControlBoardInterface
 */
public class ControlBoard implements ControlBoardInterface {
    private static final boolean kUseGamepad = false;
    private static ControlBoardInterface mInstance = null;
    private final Joystick mThrottleStick;
    private final Joystick mButtonBoard;
    protected ControlBoard() {
        mThrottleStick = new Joystick(1);
        mButtonBoard = new Joystick(0);
    }

    public static ControlBoardInterface getInstance() {
        if (mInstance == null) {
            if (kUseGamepad) {
                mInstance = new GamepadControlBoard();
            } else {
                mInstance = new ControlBoard();
            }
        }
        return mInstance;
    }

    // DRIVER CONTROLS
    @Override
    public double getThrottleX() {
        return -mThrottleStick.getX();
    }

    @Override
    public double getThrottleY() {
        return -mThrottleStick.getY();
    }

    @Override
    public double getTwist() {
        return mThrottleStick.getZ();
    }

    // OPERATOR CONTROLS
    @Override
    public boolean getRotateLeftButton() {
        return mButtonBoard.getRawButton(1);
    }

    @Override
    public boolean getRotateRightButton() {
        return mButtonBoard.getRawButton(4);
    }

    @Override
    public boolean getIntakeStackButton() {
        return mButtonBoard.getRawAxis(2) < -0.1;
    }

    @Override
    public boolean getIntakeFloorButton() {
        return mButtonBoard.getRawAxis(3) < -0.1;
    }

    @Override
    public boolean getSpoolShooterButton() {
        return false;
    }

    @Override
    public boolean getExhaustExchangeButton() {
        return false;
    }

    @Override
    public boolean getExhaustSwitchButton() {
        return false;
    }

    @Override
    public boolean getUnjamButton() {
        return mButtonBoard.getRawButton(4);
    }

    @Override
    public boolean getWantsLaunchButton() {
        return mButtonBoard.getRawButton(8);
    }

    @Override
    public boolean getLoadLauncherButton() {
        return false;
    }

    @Override
    public boolean getExtendClimbButton() {
        return false;
    }

    @Override
    public boolean getRetractClimbButton() {
        return false;
    }

    @Override
    public boolean getClimbButton() {
        return false;
    }

    @Override
    public boolean getBlinkLEDButton() {
        return mButtonBoard.getRawButton(9);
    }

    @Override
    public boolean getWantsCubeIntakeButton() {
        return false;
    }
}