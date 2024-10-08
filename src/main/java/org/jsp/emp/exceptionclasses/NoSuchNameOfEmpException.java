package org.jsp.emp.exceptionclasses;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoSuchNameOfEmpException extends RuntimeException {
	
	private String message;
	public NoSuchNameOfEmpException(String name) 
	{
		this.message=message;
	}
	@Override
	public String getMessage()
	{
		return this.message;
		
	}
}
