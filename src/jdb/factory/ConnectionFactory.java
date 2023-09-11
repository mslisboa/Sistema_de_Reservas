package jdb.factory;

import java.sql.Connection;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
    // Atributo responsavel por expor o pool de conexões
    public DataSource dataSource;

    // Cria uma conexão com o banco de dados
    public ConnectionFactory() {
        // Cria o pool de conexões
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(
            "jdbc:mysql://localhost/sistema_reservas?useTimezone=true&serverTimezone=UTC"
        );
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("admin");

        // Número de conexões abertas
        comboPooledDataSource.setMaxPoolSize(15);

        // Atribui o pool de conexões ao dataSource
        this.dataSource = comboPooledDataSource;
    }

    // Recupera a conexão criada
    public Connection recuperarConexao() {
        try {
            return this.dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}