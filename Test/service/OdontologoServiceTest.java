package service;

import dao.BD;
import dao.OdontologoDaoH2;
import modelo.Odontologo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {

    private OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());



    @Test
    public void registrarOdontologo()throws Exception{
        BD.crearBD();
        Odontologo odontologo = new Odontologo();
        odontologo.setMatricula(2038);
        odontologo.setNombre("german");
        odontologo.setApellido("vazquez");

        odontologoService.guardar(odontologo);
        assertTrue(odontologo.getId()==1);

        Odontologo odontologo2 = new Odontologo();
        odontologo.setMatricula(2039);
        odontologo.setNombre("cosme");
        odontologo.setApellido("fulanito");

        odontologoService.guardar(odontologo);



        odontologoService.listar();
    }

}