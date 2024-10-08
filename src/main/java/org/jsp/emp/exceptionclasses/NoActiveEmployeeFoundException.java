package org.jsp.emp.exceptionclasses;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoActiveEmployeeFoundException extends RuntimeException 
{
	private String message;
	public NoActiveEmployeeFoundException(String message)
	{
		this.message=message;
	}
	public String getMessage()
	{
		return this.message;
	}

}
