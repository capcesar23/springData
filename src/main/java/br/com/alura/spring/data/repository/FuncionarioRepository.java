package br.com.alura.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
//public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> por
//public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>
//podemos fazer a paginação, deixando mais rápido as consultas
@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>, 
JpaSpecificationExecutor<Funcionario>{
	
	// Busca por nome
	List<Funcionario> findByNome(String nome);
	
	//novo método Busca por nome com paginação
//    List<Funcionario> findByNome(String nome, Pageable pageable);
	
	// Bucar por nome, salario, data de contratação POR jpql
	@Query(	"SELECT f FROM Funcionario f WHERE f.nome = :nome "
			+ "AND f.salario >= :salario AND f.dataContratacao = :data")//informando o comando sql
	List<Funcionario> findNomeSalarioMaiorDataContatacao(String nome, Double salario,	LocalDate data);
	
	//retornar uma lista de funcionário ou todos os funcionários 
	//que tenham a data de contratação superior a data informada.
	//nativeQuery = true informa que é um comando nativo
	@Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao = :data",nativeQuery = true)
	List<Funcionario> findDataContatacao(LocalDate data);
	
	// retornar lista com  maior salario
	@Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true )
	List<FuncionarioProjecao> findFuncionarioSalario();
	
//	//a mesma função do metodo acima mais utilizando a Utilizando a classe dto
//	@Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
//	List<FuncionarioDto> findFuncionarioSalarioComProjecaoClasse();
}
