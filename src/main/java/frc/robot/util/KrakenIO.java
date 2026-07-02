package frc.robot.util;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.RPM;
import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.RotationsPerSecondPerSecond;
import static edu.wpi.first.units.Units.Volts;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.ControlRequest;
import com.ctre.phoenix6.signals.ControlModeValue;
import com.ctre.phoenix6.signals.MotorOutputStatusValue;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularAcceleration;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import org.littletonrobotics.junction.AutoLog;

public interface KrakenIO {
  public static record ClosedLoopInputs(
      double output,
      double pOutput,
      double iOutput,
      double dOutput,
      double feedForwardOutput,
      double reference,
      double error) {
    public static ClosedLoopInputs zero() {
      return new ClosedLoopInputs(0, 0, 0, 0, 0, 0, 0);
    }
  }
  ;

  @AutoLog
  public static class KrakenIOInputs {
    public Voltage supplyVoltage = Volts.of(0);
    public Voltage motorVoltage = Volts.of(0);
    public Current supplyCurrent = Amps.of(0);
    public Current statorCurrent = Amps.of(0);
    public Angle angle = Radians.of(0);
    public AngularVelocity angularVelocity = RPM.of(0);
    public AngularAcceleration angularAcceleration = RotationsPerSecondPerSecond.of(0);
    public double dutyCycle = 0;
    public ControlModeValue controlMode = ControlModeValue.DisabledOutput;
    public MotorOutputStatusValue motorOutputStatus = MotorOutputStatusValue.Unknown;
    public ClosedLoopInputs closedLoop = ClosedLoopInputs.zero();
  }

  public default void applyConfigs(TalonFXConfiguration config) {}

  public default void setControl(ControlRequest request) {}

  public default void resetSignalFrequencies() {}

  public default void optimizeBusUtilization() {}

  public default void updateInputs(KrakenIOInputs inputs) {}
}
