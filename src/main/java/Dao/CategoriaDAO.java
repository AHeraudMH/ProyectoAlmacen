/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import InterfacesDao.CrudSimpleInterface;
import Entidades.Categoria;
import Utilidades.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author AHeraudMH
 */
public class CategoriaDAO implements CrudSimpleInterface<Categoria> {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public CategoriaDAO() {
        CON = Conexion.getInstancia();
    }

    @Override
    public List<Categoria> listar(String texto) {
        List<Categoria> registros = new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT * FROM Categoria WHERE nombre LIKE ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Categoria(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            ps = null;
            CON.desconectar();
        }
        return registros;
    }

    @Override
    public boolean insertar(Categoria obj) {
        resp=false;
        
        try {
            ps=CON.conectar().prepareStatement("INSERT INTO categoria(nombre, descripcion, activo) VALUES (?,?,1)");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            if (ps.executeUpdate() > 0) {
                resp=true;
            }
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean actualizar(Categoria obj) {
        f; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean desactivar(int id) {
        f; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean activar(int id) {
        f; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int total() {
        f; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(String texto) {
        f; //To change body of generated methods, choose Tools | Templates.
    }

}
