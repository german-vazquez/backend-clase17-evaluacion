package dao;

import modelo.Odontologo;
import org.apache.log4j.Logger;
import service.OdontologoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private static final String INSERT = "insert into odontologos(matricula, nombre, apellido) values (?,?,?)";
    private static final String SELECT = "select * from odontologos where id =?";
    private static final String TODOS = "select * from odontologos";
    private static final Logger logger = Logger.getLogger(OdontologoDaoH2.class);


    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;

        try {
            connection = BD.getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, odontologo.getMatricula());
            ps.setString(2, odontologo.getNombre());
            ps.setString(3, odontologo.getApellido());
            ps.executeUpdate();
            ResultSet idGenerada = ps.getGeneratedKeys();
            logger.info("se guardó un nuevo odontologo");

            while (idGenerada.next()) {
                odontologo.setId(idGenerada.getInt(1));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return odontologo;
    }

    @Override
    public Odontologo buscar(Integer id) {
        Connection connection = null;
        Odontologo odontologo = null;

        try {
            connection = BD.getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            logger.info("se busca el odontologo con id = "+ id);
            while (rs.next()) {
                odontologo = new Odontologo(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            connection = BD.getConnection();

            //PreparedStatement ps = connection.prepareStatement();

            Statement ps = connection.createStatement();
            ResultSet rs = ps.executeQuery(TODOS);
            while (rs.next()) {
                int idOdontologo = rs.getInt("id");
                int matricula = rs.getInt("matricula");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Odontologo odontologo = new Odontologo();
                odontologo.setId(idOdontologo);
                odontologo.setMatricula(matricula);
                odontologo.setNombre(nombre);
                odontologo.setApellido(apellido);

                odontologos.add(odontologo);
                logger.info("se muestra la lista de odontologos");
                System.out.println("id: "+ rs.getInt(1)+ "- Matricula: "+ rs.getInt(2)+ " - "+rs.getString(3) + " "+ rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return odontologos;
    }

}


