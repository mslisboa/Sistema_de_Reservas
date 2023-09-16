package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import jdb.factory.ConnectionFactory;
import jdbc.modelo.Hospedes;

public class TestaHospedesDao {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.recuperarConexao();

        HospedesDao hospedesDao = new HospedesDao(connection);
        // Hospedes hospedes = new Hospedes("Mauro", "Filho", new Date(202105l), "Brasileiro", "12345678", 1);

        // hospedesDao.salvar(hospedes);

        List<Hospedes> listHospedes = hospedesDao.listarHospedes();
        for (Hospedes hospede : listHospedes) {
            System.out.println(hospede.getNome() + "\n" + hospede.getIdReserva());
        }
    }
}