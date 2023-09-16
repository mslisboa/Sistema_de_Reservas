package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.modelo.Reserva;

public class ReservaDao {
	
	private Connection connection;
	
	public ReservaDao(Connection connection) {
		this.connection = connection;
	}
	
	public void salvar(Reserva reserva) {
		try {
			String sql = "INSERT INTO Reserva (data_entrada, data_saida, valor, forma_pagamento) VALUES (?, ?, ?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setDate(1, (Date) reserva.getDataEntrada());
				pstm.setDate(2, (Date) reserva.getDataSaida());
				pstm.setBigDecimal(3, reserva.getValor());
				pstm.setString(4, reserva.getFormaPagamento());

				pstm.executeUpdate();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						reserva.setId(rst.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public List<Reserva> buscar() {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {
			String sql = "SELECT id, data_entrada, data_saida, valor, forma_pagamento FROM Reserva";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				transformarResultSetEmReserva(reservas, pstm);
			}
			
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> buscarId(String id) {
		List<Reserva> reservas = new ArrayList<Reserva>();
		try {

			String sql = "SELECT id, data_entrada, data_saida, valor, form_pagamento FROM Reserva WHERE id = ?";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.execute();

				transformarResultSetEmReserva(reservas, pstm);
			}

			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Boolean deletar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM Reserva WHERE id = ?")) {
			stm.setInt(1, id);
			return stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Boolean atualizar(Date dataEntrada, Date dataSaida, String valor, String formaPagamento, Integer id) {
		try (PreparedStatement stm = connection
				.prepareStatement("UPDATE Reserva SET data_entrada = ?, data_saida = ?, valor = ?, forma_pagamento = ? WHERE id = ?")) {
			stm.setDate(1, dataEntrada);
			stm.setDate(2, dataSaida);
			stm.setString(3, valor);
			stm.setString(4, formaPagamento);
			stm.setInt(5, id);

			return stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

						
	private void transformarResultSetEmReserva(List<Reserva> reservas, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Reserva reserva = new Reserva(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getBigDecimal(4), rst.getString(5));

				reservas.add(reserva);
			}
		}
	}
}
