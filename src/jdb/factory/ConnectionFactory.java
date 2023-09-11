package jdb.factory;

import java.sql.Connection;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
    public DataSource dataSource;

    // Cria uma conexão com o banco de dados
    public ConnectionFactory() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(
            "jdbc:mysql://localhost/sistema_reservas?useTimezone=true&serverTimezone=UTC"
        );
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("admin");

        comboPooledDataSource.setMaxPoolSize(15);

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