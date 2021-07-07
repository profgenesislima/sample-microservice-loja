package br.edu.ifpe.gus.graphqlservice.resolvers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import br.edu.ifpe.gus.graphqlservice.entity.Author;
import br.edu.ifpe.gus.graphqlservice.entity.Post;
import br.edu.ifpe.gus.graphqlservice.repository.AuthorRepository;
import br.edu.ifpe.gus.graphqlservice.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RequestQueryResolver implements GraphQLQueryResolver{

	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	public List<Post> recentPosts(Integer limit, Integer offset, String orderBy){
		PageRequest pageRequest = PageRequest.of(limit, offset, Sort.Direction.DESC, orderBy);
		return postRepository.findAll(pageRequest).getContent();
	}
	
	public List<Author> authorsWithTopPosts(){
		log.info("authors with top posts.");
		return authorRepository.findAuthorsWithPosts();
	}
	
	
	
}
