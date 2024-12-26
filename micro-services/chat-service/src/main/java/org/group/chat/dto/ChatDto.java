package org.group.chat.dto;

import lombok.*;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto{
	private Long id;
	private String code;
	private Boolean isInput;
	private Timestamp creationDate;
	private Long idUser;

}
