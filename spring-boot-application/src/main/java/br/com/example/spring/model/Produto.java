package br.com.example.spring.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRODUTO", indexes = { @Index(name = "IDX_CODIGO_PRODUTO", columnList = "CODIGO") })
public class Produto implements Serializable {

    @Id
    @SequenceGenerator(name = "GENERATOR_PRODUTO", sequenceName = "PRO_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "GENERATOR_PRODUTO")
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "CODIGO", nullable = false)
    private String codigo;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "ATIVO", nullable = false)
    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
