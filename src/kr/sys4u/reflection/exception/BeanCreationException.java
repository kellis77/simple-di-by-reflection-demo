package kr.sys4u.reflection.exception;

public class BeanCreationException extends RuntimeException {
	private static final long serialVersionUID = 2703990187765144335L;

	public BeanCreationException() {
		super();
		
	}

	public BeanCreationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	public BeanCreationException(String arg0) {
		super(arg0);
		
	}

	public BeanCreationException(Throwable arg0) {
		super(arg0);
		
	}

}
