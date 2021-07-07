package br.edu.ifpe.gus.graphqlservice.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import br.edu.ifpe.gus.graphqlservice.dto.AuthorInputRequest;
import br.edu.ifpe.gus.graphqlservice.dto.PostInputRequest;
import br.edu.ifpe.gus.graphqlservice.entity.Author;
import br.edu.ifpe.gus.graphqlservice.entity.Post;
import br.edu.ifpe.gus.graphqlservice.entity.Status;
import br.edu.ifpe.gus.graphqlservice.repository.AuthorRepository;
import br.edu.ifpe.gus.graphqlservice.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RequestMutationResolver implements GraphQLMutationResolver {

	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	public Post newPost(PostInputRequest postInputRequest) {
		
		Author author = authorRepository.findById(postInputRequest.getAuthorId()).orElse(new Author("Gênesis", 35));;
		
		if(author.getId() == null)
			authorRepository.save(author);
		
		Post post = Post.builder()
				    	.title(postInputRequest.getTitle())
				    	.content(postInputRequest.getContent())
				    	.author(author)
				    	.status(Status.ACTIVE)
				    	.build();
		
		return post = postRepository.save(post);
		
		
	}
	
	public Author newAuthor(AuthorInputRequest authorInputRequest)   {
        Author author = new Author(authorInputRequest.getName(), 
authorInputRequest.getAge());
        author = authorRepository.save(author);
        return author;
    }
	
	
}
