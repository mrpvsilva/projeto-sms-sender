package Interfaces;

import java.util.List;

import Dominio.Servico;

public interface IServicoRepository extends IRepositoryBase<Servico> {

	public Servico findByName(String nome);

	public List<Servico> findAll(boolean ativo);
	
	public List<Servico> findAll(String nome, String ativo);
	
	public List<String> DDL();

}
