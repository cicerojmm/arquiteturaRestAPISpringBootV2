package br.com.example.spring.repository;

import br.com.example.spring.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);

	@Modifying
	@Query("update Usuario u set u.senha = ?1 where u.email = ?2")
	void atualizarSenha(String senha, String email);
}
