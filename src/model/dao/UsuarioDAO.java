/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Usuario;

/**
 *
 * @author Rocha
 */
public class UsuarioDAO {

    public boolean checkLogin(String cpf, String senha_hash) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE cpf = ? and senha_hash = md5(?) ");
            stmt.setString(1, cpf);
            stmt.setString(2, senha_hash);
            rs = stmt.executeQuery();

            if (rs.next()) {
                check = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);

        }
        return check;
    }

    public Usuario findByCpf(String cpf) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = new Usuario();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE cpf = ?");
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            while (rs.next()) {
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(cpf);
                usuario.setEmail(rs.getString("email"));
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setTelefone(rs.getString("telefone"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);

        }
        if (usuario.getCpf() == null) {
            return null;
        }
        return usuario;
    }

    public void updateUsuarioPCpf(String cpf, String telefone,String senha) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE usuario SET senha_hash = md5(?) WHERE cpf = ? AND telefone = ?");
            stmt.setString(1, senha);
            stmt.setString(2, cpf);
            stmt.setString(3, telefone);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Dados não compatíveis!");
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Usuario> findAll() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();

                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNome(rs.getString("nome"));
                usuario.setTelefone(rs.getString("telefone"));

                usuarios.add(usuario);

            }

        } catch (SQLException ex) {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return usuarios;
    }

    public void updateUsuario(Usuario usuario) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        Usuario u = usuario;
        try {
            stmt = con.prepareStatement("UPDATE usuario SET nome = ? , email = ? , telefone = ? WHERE cpf = ? ");
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getCpf());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar!" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void create(Usuario u) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO usuario (cpf,nome,telefone,email,senha_hash) VALUES (?,?,?,?,md5(?))");
            stmt.setString(1, u.getCpf());
            stmt.setString(2, u.getNome());
            stmt.setString(3, u.getTelefone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getSenha_hash());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar!" + ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public void addAdmin(String cpf) {
        Usuario usuario = findByCpf(cpf);
        if (usuario != null) {
            Connection con = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
            Connection con2 = ConnectionFactory.getConnection();
            PreparedStatement stmt2 = null;
            ResultSet ultimoId = null;

            try {
                stmt = con.prepareStatement("INSERT INTO admin (id_usuario) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, usuario.getId_usuario());
                stmt.executeUpdate();
                ultimoId = stmt.getGeneratedKeys();
                int idAdmin = 0;
                if (ultimoId.next()) {
                    idAdmin = ultimoId.getInt(1);
                }
                stmt2 = con.prepareStatement("INSERT INTO usuario_admin (id_usuario,id_admin) VALUES(?,?) ");
                stmt2.setInt(1, usuario.getId_usuario());
                stmt2.setInt(2, idAdmin);
                stmt2.executeUpdate();
                JOptionPane.showMessageDialog(null, "Admin adicionado com sucesso!");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar admin!" + ex);

            } finally {
                ConnectionFactory.closeConnection(con, stmt, ultimoId);
                ConnectionFactory.closeConnection(con2, stmt2);
            }
        }
    }

    public boolean isAdmin(String cpf) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean admin = false;
        try {
            stmt = con.prepareStatement("SELECT count(*) FROM usuario u INNER JOIN usuario_admin ua ON (u.id_usuario = ua.id_usuario) INNER JOIN admin a ON (ua.id_admin = a.id_admin)  WHERE cpf = ?");
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    admin = true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return admin;
    }

    public void delete(String cpf) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE u, ua, a FROM usuario u INNER JOIN usuario_admin ua ON (u.id_usuario = ua.id_usuario) INNER JOIN admin a ON (ua.id_admin = a.id_admin)  WHERE cpf = ?");
            stmt.setString(1, cpf);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario removido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao remover usuario!" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
