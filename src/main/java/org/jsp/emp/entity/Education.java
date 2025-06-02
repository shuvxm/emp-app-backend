package org.jsp.emp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long eid;
	private String qualification;
	private String universityName;
	private double percentage;
	private int completionYear;
	private String highestQualification; // YES - NO

	@ManyToOne
	private Employee employee;

}
