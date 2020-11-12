package br.com.gustavoakira.devpay.conversors;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseConverter<E,D>{
	public abstract D convertEntityForDTO(E entity);
	
	public abstract E convertDTOtoEntity(D dto);
	
	public List<D> convertEntitystoDTOs(List<E> entitys){
		List<D> dtos = new ArrayList<>();
		entitys.stream().forEach(entity -> dtos.add(convertEntityForDTO(entity)));
		return dtos;
	}
	
	public List<E> convertDTOsToEntitys(List<D> dtos){
		List<E> entitys = new ArrayList<>();
		dtos.stream().forEach(dto->entitys.add(convertDTOtoEntity(dto)));
		return entitys;
	}
}
