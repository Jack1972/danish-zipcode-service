package biz.petersen.zipcode;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class CityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5850158592974475030L;

	public CityNotFoundException(String city) {
		super("could not find city: " + city);
	}
}