package frc.robot.vision;

import org.littletonrobotics.junction.AutoLog;
import org.photonvision.targeting.PhotonPipelineResult;

public interface CameraIO {
  @AutoLog
  public static class CameraIOInputs {
    public boolean isConnected = false;
    public PhotonPipelineResult unreadResult = new PhotonPipelineResult();
  }

  public default void updateInputs(CameraIOInputs inputs) {}
}
