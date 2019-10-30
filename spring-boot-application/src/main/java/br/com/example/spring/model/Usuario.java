package br.com.example.spring.model;

import br.com.example.spring.enums.TipoPermissao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USUARIO", indexes = {@Index(name = "IDX_EMAIL", columnList = "EMAIL")})
public class Usuario implements UserDetails {

    private static final long serialVersionUID = -7023455488399103384L;

    @Id
    @SequenceGenerator(name = "GENERATOR_USUARIO", sequenceName = "USU_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "GENERATOR_USUARIO")
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "SENHA", nullable = false)
    private String senha;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "ATIVO", nullable = false)
    private Boolean status;

    @Enumerated(EnumType.STRING)
    @Column(name = "PERMISSAO", nullable = false)
    private TipoPermissao permissao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public TipoPermissao getPermissao() {
        return permissao;
    }

    public void setPermisao(TipoPermissao permissao) {
        this.permissao = permissao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> listaAutorizacoes = Arrays.asList(new SimpleGrantedAuthority(permissao.getCodigo()));
		return listaAutorizacoes;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.status;
	}
}
