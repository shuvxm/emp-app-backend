package org.jsp.emp.responsestructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ResponseStructure<T> {

	private int status;
	private String message;
	private T body;
	
}
