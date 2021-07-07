package br.edu.ifpe.gus.graphqlservice.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Gênesis Lima
 * @email profgenesislima@gmail.com
 *
 */
@Data
@Entity
@ToString(exclude = {"posts"})
@EqualsAndHashCode(exclude = {"posts"})
public class Author implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8511362792195503307L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int age;
	private Status status = Status.NON_ACTIVE;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	@JsonIgnore
	private Set<Post> posts;
	
	 public Author(String name, Integer age)  {
	        this.name = name;
	        this.age = age;
	    }
	 

}
