package Model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "usuario")
@RequestScoped
public class Usuario {
    int codigo;  
    String nome;
    CRUD_Usuario Obj_CRUD = new CRUD_Usuario();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CRUD_Usuario getObj_CRUD() {
        return Obj_CRUD;
    }

    public void setObj_CRUD(CRUD_Usuario Obj_CRUD) {
        this.Obj_CRUD = Obj_CRUD;
    }
}