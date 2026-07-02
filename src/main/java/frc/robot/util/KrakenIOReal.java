package frc.robot.util;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.ControlRequest;
import com.ctre.phoenix6.hardware.TalonFX;

public class KrakenIOReal implements KrakenIO {
  final TalonFX motor;

  public KrakenIOReal(int canID) {
    motor = new TalonFX(canID);
  }

  @Override
  public void applyConfigs(TalonFXConfiguration configs) {
    motor.getConfigurator().apply(configs);
  }

  @Override
  public void setControl(ControlRequest request) {
    motor.setControl(request);
  }

  @Override
  public void resetSignalFrequencies() {
    motor.resetSignalFrequencies();
  }

  public void optimizeBusUtilization() {
    motor.optimizeBusUtilization();
  }

  @Override
  public void updateInputs(KrakenIOInputs inputs) {
    inputs.supplyVoltage = motor.getSupplyVoltage().getValue();
    inputs.motorVoltage = motor.getMotorVoltage().getValue();
    inputs.supplyCurrent = motor.getSupplyCurrent().getValue();
    inputs.statorCurrent = motor.getStatorCurrent().getValue();
    inputs.angle = motor.getPosition().getValue();
    inputs.angularVelocity = motor.getVelocity().getValue();
    inputs.angularAcceleration = motor.getAcceleration().getValue();
    inputs.dutyCycle = motor.getDutyCycle().getValue();
    inputs.controlMode = motor.getControlMode().getValue();
    inputs.motorOutputStatus = motor.getMotorOutputStatus().getValue();
    inputs.closedLoop =
        new ClosedLoopInputs(
            motor.getClosedLoopOutput().getValue(),
            motor.getClosedLoopProportionalOutput().getValue(),
            motor.getClosedLoopIntegratedOutput().getValue(),
            motor.getClosedLoopDerivativeOutput().getValue(),
            motor.getClosedLoopFeedForward().getValue(),
            motor.getClosedLoopReference().getValue(),
            motor.getClosedLoopError().getValue());
  }
}
