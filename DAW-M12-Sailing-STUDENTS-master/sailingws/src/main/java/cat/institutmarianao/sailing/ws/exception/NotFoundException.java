package cat.institutmarianao.sailing.ws.exception;

public class NotFoundException extends RuntimeException {
	public NotFoundException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;
}