package br.edu.ifpe.gus.graphqlservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifpe.gus.graphqlservice.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

	@Query("select distinct a from Author a left join fetch a.posts")
	List<Author> findAuthorsWithPosts();
}
