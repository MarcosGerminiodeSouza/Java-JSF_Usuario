package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.context.FacesContext;

public class CRUD_Usuario {
    private Connection conexao;
    private Map<String,Object> sessionMap = FacesContext
                                            .getCurrentInstance()
                                            .getExternalContext()
                                            .getSessionMap();
    
    // Conexão com banco de dados
    public Connection getConnection(){  
        try{  
            Class.forName("com.mysql.jdbc.Driver");     
            conexao = DriverManager.getConnection( "jdbc:mysql://localhost:3306/usuario","root","");  
        }
        catch(Exception e){  
            System.out.println(e);  
        }  
        return conexao;  
    }    
    
    // Listar
    public ArrayList ListarUsuarios() {
        ArrayList lista = null;
        try {
            lista = new ArrayList();
            conexao = getConnection();
            Statement declaracao = getConnection().createStatement();
            ResultSet rs = declaracao.executeQuery("select * from usuario");
            while (rs.next()) {
                Usuario Obj_Usuario = new Usuario();
                Obj_Usuario.setCodigo(rs.getInt("codigo"));
                Obj_Usuario.setNome(rs.getString("nome"));
                lista.add(Obj_Usuario);
            }
            conexao.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
    
    // Inserir
    public String inserir(int codigo, String nome) {
        int result = 0;
        try {
            conexao = getConnection();
            PreparedStatement declaracao = conexao.prepareStatement(
            "insert into usuario(codigo, nome) values(?, ?)");
            declaracao.setString(1, String.valueOf(codigo));
            declaracao.setString(2, nome);
            result = declaracao.executeUpdate();
            conexao.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (result != 0)
            return "index";
        else
            return "inserirUsuario";
    }
    
    // Excluir
    public void excluir(int codigo) {
        try {
            conexao = getConnection();
            PreparedStatement declaracao = conexao.prepareStatement(
            "delete from usuario where codigo = " + codigo);
            declaracao.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // Alterar
    public String alterar(int codigo) {
        int result = 0;
        try {
            conexao = getConnection();
            Statement declaracao = getConnection().createStatement();
            ResultSet rs = declaracao.executeQuery("select * from usuario where codigo = " + codigo);            rs.next();
            Usuario Obj_Usuario = new Usuario();
            Obj_Usuario.setCodigo(rs.getInt("codigo"));
            Obj_Usuario.setNome(rs.getString("nome"));
            sessionMap.put("alterarUsuario", Obj_Usuario);
            conexao.close();
        } catch (Exception e) {
            System.out.println(e);
            result = 1;
        }
        if (result != 0)
            return "index";
        else
            return "alterarUsuario";
    }
    
    // Atualizar
    public String atualizar(Usuario usuario) {
        try {
            conexao = getConnection();
            PreparedStatement declaracao = conexao.prepareStatement(
            "update usuario set nome = ? where (codigo = ?)");
            declaracao.setString(1, usuario.nome);
            declaracao.setString(2, String.valueOf(usuario.codigo));
            declaracao.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "index";
    }    
}