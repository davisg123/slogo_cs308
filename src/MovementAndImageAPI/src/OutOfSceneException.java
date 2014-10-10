package MovementAndImageAPI.src;

public class OutOfSceneException extends Exception {
	
	private static final String OUT_OF_SCENE_MESSAGE = "The specified point is outside of the scene's limits.";

	@Override
	public String getMessage() {
		return OUT_OF_SCENE_MESSAGE;
	}
}
