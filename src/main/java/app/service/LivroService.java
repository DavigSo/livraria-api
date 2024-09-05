package app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import app.entity.Livro;

@Service
public class LivroService {
	
	private List<Livro> livros = new ArrayList<>();
	/** fornece um contador atômico que pode ser incrementado 
	de forma segura em ambientes multithread.**/
	private AtomicLong idCounter = new AtomicLong();
	
	public Livro save(Livro livro) {
		if(livro.getId() == null) {
			// Isso é usado para gerar novos IDs únicos para os livros
			 livro.setId(idCounter.incrementAndGet());
			livros.add(livro);
		} else {
			for(int i = 0; i < livros.size(); i++) {
				if(livros.get(i).getId().equals(livro.getId())) {
					livros.set(i, livro);
					return livro;
				}
			}
		}
		return livro;
	}
	
	public List<Livro> findAll() {
		return new ArrayList<>(livros);
	}
	
	/** O uso de Optional é uma forma de evitar retornos 
	nulos e lidar com a ausência de resultados de forma mais segura. **/
	public Optional<Livro> findById(Long id) {
		return livros.stream().filter(livro -> livro.getId().equals(id)).findFirst();
	}
	
	public void deleteById(Long id) {
		livros.removeIf(livro -> livro.getId().equals(id));
	}
	
	public boolean existsById(Long id) {
		/**Quando você precisa verificar rapidamente, usa-se o anymatch
		se algum elemento em uma coleção atende a uma condição**/
		return livros.stream().anyMatch(livro -> livro.getId().equals(id));
	}
}
