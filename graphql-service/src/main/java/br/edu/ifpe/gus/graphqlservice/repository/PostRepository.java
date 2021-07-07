package br.edu.ifpe.gus.graphqlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpe.gus.graphqlservice.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
