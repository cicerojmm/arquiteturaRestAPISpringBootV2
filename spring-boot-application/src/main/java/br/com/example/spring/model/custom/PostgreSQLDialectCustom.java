package br.com.example.spring.model.custom;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL94Dialect;

public class PostgreSQLDialectCustom extends PostgreSQL94Dialect {

	public PostgreSQLDialectCustom() {
		this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
}
}
