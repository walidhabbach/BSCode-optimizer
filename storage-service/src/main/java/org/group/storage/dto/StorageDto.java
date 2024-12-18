package org.group.storage.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StorageDto{
	private Long id;
	private String text;
	private Boolean isInput;
	private Timestamp creationDate;
	private Long idUser;

}
