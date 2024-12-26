package org.group.chat.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

 	@Column(columnDefinition = "TEXT")
	private String code;

	private Boolean isInput;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Timestamp creationDate;

	private Long idUser;
}
