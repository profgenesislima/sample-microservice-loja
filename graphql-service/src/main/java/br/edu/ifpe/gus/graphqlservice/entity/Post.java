package br.edu.ifpe.gus.graphqlservice.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"author"})
@EqualsAndHashCode(exclude = {"author"})
public class Post implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4147183165260056145L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String content;
	private Status status = Status.NON_ACTIVE;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Author author;

}
