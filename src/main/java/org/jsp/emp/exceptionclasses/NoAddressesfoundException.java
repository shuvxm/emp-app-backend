package org.jsp.emp.exceptionclasses;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class NoAddressesfoundException extends RuntimeException {

	private String message;

	public NoAddressesfoundException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
