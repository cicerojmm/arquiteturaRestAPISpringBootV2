package br.com.example.spring.conversor;

import java.util.ArrayList;
import java.util.List;

public abstract class ConversorBase<E, T> {

	public abstract T converterEntidadeParaDto(E entidade);

	public abstract E converterDtoParaEntidade(T dto);

	public List<T> converterEntidadesParaDtos(List<E> entidades) {
		List<T> dtos = new ArrayList<>();
		entidades.stream().forEach(entidade -> dtos.add(converterEntidadeParaDto(entidade)));
		return dtos;
	}

	public List<E> converterDtosParaEntidades(List<T> dtos) {
		List<E> entidades = new ArrayList<>();
		dtos.stream().forEach(dto -> entidades.add(converterDtoParaEntidade(dto)));
		return entidades;
	}
}
