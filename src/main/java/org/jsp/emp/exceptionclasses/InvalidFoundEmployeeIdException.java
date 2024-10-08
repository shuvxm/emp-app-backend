package org.jsp.emp.exceptionclasses;

import lombok.NoArgsConstructor;

@NoArgsConstructor

public class InvalidFoundEmployeeIdException extends RuntimeException
{
	private String message;
	public InvalidFoundEmployeeIdException(String message)
	{
		this.message=message;
	}
	@Override
	public String getMessage()
	{
		return this.message;
	}

}
