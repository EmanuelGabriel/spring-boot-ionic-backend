package com.fmtz.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fmtz.cursomc.domain.Categoria;
import com.fmtz.cursomc.repositories.CategoriaRepository;
import com.fmtz.cursomc.services.exceptions.DataIntegrityException;
import com.fmtz.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;


	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		}
	
	public Categoria insert(Categoria obj) {
		//o id nulo é pro método save entender que é uma inserção, se passassemos um id ele entenderia como uma atualização
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		
		try {
		repo.deleteById(id);
		}
		catch (DataIntegrityViolationException  e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

}
