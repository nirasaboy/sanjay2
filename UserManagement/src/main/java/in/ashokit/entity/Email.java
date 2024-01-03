package in.ashokit.entity;

import lombok.Data;

@Data
public class Email {
	private String to;
	private String subject;
	private String message;
}
