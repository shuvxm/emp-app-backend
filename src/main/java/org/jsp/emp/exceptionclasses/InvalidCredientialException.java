package org.jsp.emp.exceptionclasses;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidCredientialException extends RuntimeException
{
	private String message;
	public InvalidCredientialException(String name) 
	{
		this.message=message;
	}
	@Override
	public String getMessage()
	{
		return this.message;
		
	}

}
