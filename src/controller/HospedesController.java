package controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import model.ConnectionFactory;
import model.dao.HospedesDao;
import model.entity.Hospedes;

public class HospedesController {
    private HospedesDao hospedesDao;

    public HospedesController() {
        Connection connection = new ConnectionFactory().recuperarConexao();
        this.hospedesDao = new HospedesDao(connection);
    }

    public void salvar(Hospedes hospedes) {
        this.hospedesDao.salvar(hospedes);
    }

    public List<Hospedes> listarHospedes() {
        return this.hospedesDao.listarHospedes();
    }

    public List<Hospedes> listarHospedesId(String id) {
        return this.hospedesDao.buscarId(id);
    }

    public void atualizar(String nome, String sobrenome, Date dataNascimento, String nacionalidade, String telefone, Integer idReserva, Integer id) {
        this.hospedesDao.atualizar(nome, sobrenome, dataNascimento, nacionalidade, telefone, idReserva, id);
    }

    public void deletar(Integer id) {
        this.hospedesDao.deletar(id);
    }
}