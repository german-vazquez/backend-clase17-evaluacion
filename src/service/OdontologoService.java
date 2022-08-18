package service;

import dao.IDao;
import modelo.Odontologo;

import java.util.List;

public class OdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardar (Odontologo odontologo){
        return odontologoIDao.registrar(odontologo);
    }

    public Odontologo buscarId(Integer id){
        return odontologoIDao.buscar(id);
    }

    public List<Odontologo> listar(){
        return odontologoIDao.buscarTodos();
    }




    public IDao<Odontologo> getOdontologoIDao() {
        return odontologoIDao;
    }

    public void setOdontologoIDao(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

}
