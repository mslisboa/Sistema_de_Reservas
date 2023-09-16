package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.modelo.Hospedes;

public class HospedesDao {
    private Connection connection;

    // Recebe a conexão aberta com o banco de dados
    public HospedesDao(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Hospedes hospede) {
        try {
            // Cria a query
            String sql = "INSERT INTO Hospedes (nome, sobrenome, data_nascimento, nacionalidade, telefone, id_reserva) VALUES (?, ?, ?, ?, ?, ?)";

            // prepara uma statement com os valor a serem setados na query e a executa
            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, hospede.getNome());
                pstm.setString(2, hospede.getSobrenome());
                pstm.setDate(3, (Date) hospede.getDataNascimento());
                pstm.setString(4, hospede.getNacionalidade());
                pstm.setString(5, hospede.getTelefone());
                pstm.setInt(6, hospede.getIdReserva());

                pstm.execute();
            
                // Recupera o id gerado no banco de dados
                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        hospede.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Hospedes> listarHospedes() {
        // Cria a lista que conterá o retorno da busca
        List<Hospedes> hospedes = new ArrayList<>();

        try {
            // Cria um statement e execução da query
            try (PreparedStatement pstm = connection.prepareStatement(
                "SELECT id, nome, sobrenome, data_nascimento, nacionalidade, telefone, id_reserva FROM Hospedes"
            )) {
                pstm.execute();

                // Recupera o resultado da query e a insere na lista
                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        Hospedes hospede = new Hospedes(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDate(4), rst.getString(5), rst.getString(6), rst.getInt(7));

                        hospedes.add(hospede);
                    }
                }

                // Retorna a lista com os hospedes
                return hospedes;
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public List<Hospedes> buscarId(String id) {
        // Cria a lista que conterá o retorno da busca
        List<Hospedes> hospedes = new ArrayList<>();
        
        try {
            // Cria um statement e executa a query
            try (PreparedStatement pstm = connection.prepareStatement(
                "SELECT id, nome, sobrenome, data_nascimeto, nacionalidade, telefone, id_reserva, FROM hospedes id = ?"
            )) {
                pstm.setString(1, id);
                pstm.execute();

                // Recupera o resultado da query e a insere na lista
                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        Hospedes hospede = new Hospedes(
                            rst.getInt(1), rst.getString(2), rst.getString(3),
                            rst.getDate(4), rst.getString(5), rst.getString(6), rst.getInt(7)
                        );

                        hospedes.add(hospede);
                    }
                }

                // Retorna a lista com os hospedes buscados
                return hospedes;
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Boolean atualizar(String nome, String sobrenome, Date data_nascimento, String nacionalidade, String telefone, Integer id_reserva, Integer id) {
        try(PreparedStatement stm = connection.prepareStatement(
            "UPDATE Hospedes SET nome = ?, sobrenome = ?, data_nascimento = ?, nacionalidade = ?, telefone = ?, id_reserva = ? WHERE id = ?"
        )) {
            stm.setString(1, nome);
            stm.setString(2, sobrenome);
            stm.setDate(3, data_nascimento);
            stm.setString(4, nacionalidade);
            stm.setString(5, telefone);
            stm.setInt(6, id_reserva);
            stm.setInt(7, id);

            return stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Boolean deletar(Integer id) {
        try (PreparedStatement stm = connection.prepareStatement(
            "DELETE FROM Hospedes WHERE id = ?"
        )) {
            stm.setInt(1, id);
            return stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}