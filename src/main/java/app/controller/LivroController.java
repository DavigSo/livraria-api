package app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Livro;
import app.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@PostMapping
	public ResponseEntity<Livro> createLivro(@RequestBody Livro livro) {

		try {
			Livro savedLivro = livroService.save(livro);
			return new ResponseEntity<>(savedLivro, HttpStatus.CREATED);

		} catch (Exception e) {
			// para exibir a pilha de rastreamento da exceção
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping
	public ResponseEntity<List<Livro>> getAllLivros() {
		try {
			List<Livro> livros = livroService.findAll();
			return ResponseEntity.ok(livros);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Livro> getLivroById(@PathVariable Long Id) {
		try {
			Optional<Livro> livro = livroService.findById(Id);
			/**
			 * map é usado aqui para transformar o OptionalLivro em um ResponseEntity
			 * ResponseEntity::ok é chamado, que cria uma resposta com status 200 (OK) e o
			 * livro como corpo da resposta.
			 **/
			return livro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
			/**
			 * orElseGet(() - > ResponseEntity.notFound().build()): Se o Optional estiver
			 * vazio (o livro não for encontrado), orElseGet é chamado para fornecer uma
			 * resposta alternativa. Neste caso, ResponseEntity.notFound().build() cria uma
			 * resposta com status 404 (Not Found) e um corpo vazio.
			 **/
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Livro> updateLivro(@PathVariable Long id, @RequestBody Livro livro) {

		try {
			if (!livroService.existsById(id)) {
				return ResponseEntity.notFound().build();
			}
			livro.setId(id);
			Livro updatedLivro = livroService.save(livro);
			return ResponseEntity.ok(updatedLivro);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLivro(@PathVariable Long id) {
		try {
			if (!livroService.existsById(id)) {
				return ResponseEntity.notFound().build();
			}
			livroService.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
