package MovementAndImageAPI.src;

/**
 * 
 * @author Eirika Sawh An exception to show that the image location couldn't be
 *         found.
 */
public class ImageNotFoundException extends Exception {

	private static final String IMAGE_NOT_FOUND_MESSAGE = "There was no image at the specified location.\n"
			+ "The image was not updated.";

	@Override
	public String getMessage() {
		return IMAGE_NOT_FOUND_MESSAGE;
	}
}
