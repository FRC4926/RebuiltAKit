package frc.robot.vision;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;

public class CameraIOReal implements CameraIO {
  private final PhotonCamera camera;
  private Queue<PhotonPipelineResult> resultQueue = new LinkedList<>();

  public CameraIOReal(String cameraName) {
    camera = new PhotonCamera(cameraName);
  }

  @Override
  public void updateInputs(CameraIOInputs inputs) {
    inputs.isConnected = camera.isConnected();
    if (inputs.isConnected) {
        List<PhotonPipelineResult> results = camera.getAllUnreadResults();
        for (PhotonPipelineResult res : results) {
            resultQueue.add(res);
        }
    }
    PhotonPipelineResult resToReturn = resultQueue.poll();
    if (resToReturn == null) {
      resToReturn = new PhotonPipelineResult();
    }
    inputs.unreadResult = resToReturn;
  }
}
