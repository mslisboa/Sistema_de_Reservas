package controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import model.ConnectionFactory;
import model.dao.ReservaDao;
import model.entity.Reserva;

public class ReservaController {
    private ReservaDao reservaDao;

    public ReservaController() {
        Connection connection = new ConnectionFactory().recuperarConexao();
        this.reservaDao = new ReservaDao(connection);
    }

    public void salvar(Reserva reserva) {
		this.reservaDao.salvar(reserva);
	}
		
	public List<Reserva> buscar() {
		return this.reservaDao.buscar();
	}
	
	public List<Reserva> buscarId(String id) {
		return this.reservaDao.buscarId(id);
	}
	
	public void atualizar(Date dataEntrada, Date dataSaida, String valor, String formaPagamento, Integer id) {
		this.reservaDao.atualizar(dataEntrada, dataSaida, valor, formaPagamento, id);
	}
	
	public void deletar(Integer id) {
		this.reservaDao.deletar(id);
	}
}