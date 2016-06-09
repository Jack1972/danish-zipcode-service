package biz.petersen.zipcode;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class ZipCodeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5850158592974475030L;

	public ZipCodeNotFoundException(String zipcode) {
		super("could not find zipcode: " + zipcode);
	}
}