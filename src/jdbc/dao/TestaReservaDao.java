package jdbc.dao;

import java.sql.Connection;
import java.util.List;

import jdb.factory.ConnectionFactory;
import jdbc.modelo.Reserva;

public class TestaReservaDao {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();

        ReservaDao reservaDao = new ReservaDao(connection);
        //Reserva reserva = new Reserva(new Date(201810l), new Date(201910l), new BigDecimal("565.35"), "A vista");

        //reservaDao.salvar(reserva);

        List<Reserva> reservas = reservaDao.buscar();

        for (Reserva reserva : reservas) {
           System.out.println(reserva.getId() + "\n" + reserva.getFormaPagamento());
        }
    }
}