package Model;

public class Relacionamento {

   
    
    public String idrelacionamento;
    private String nomeparente;
    public String tiporelacao;
    
    public int Usuario_idusuario;

    
    public Relacionamento() {
    }
    public Relacionamento(String tiporelacao) {
        this.tiporelacao = tiporelacao;
    }
    public Relacionamento(String idrelacionamento, String nomeparente) {
        this.idrelacionamento = idrelacionamento;
        this.nomeparente = nomeparente;
    }
    public Relacionamento(String idrelacionamento, String nomeparente, String tiporelacao, int Usuario_idusuario) {
        this.nomeparente = nomeparente;
    }
    public Relacionamento(String nomeparente, String tiporelacao, int Usuario_idusuario) {
        this.nomeparente = nomeparente;
        this.tiporelacao = tiporelacao;
        this.Usuario_idusuario = Usuario_idusuario;
    }   

    public String getIdrelacionamento() {
        return idrelacionamento;
    }

    public void setIdrelacionamento(String idrelacionamento) {
        this.idrelacionamento = idrelacionamento;
    }

    public String getNomeparente() {
        return nomeparente;
    }

    public void setNomeparente(String nomeparente) {
        this.nomeparente = nomeparente;
    }
    
      public String getTiporelacao() {
        return tiporelacao;
    }

    public void setTiporelacao(String tiporelacao) {
        this.tiporelacao = tiporelacao;
    } 

    @Override
    public String toString() {
        return getTiporelacao();
    }

    public int getUsuario_idusuario() {
        return Usuario_idusuario;
    }

    public void setUsuario_idusuario(int Usuario_idusuario) {
        this.Usuario_idusuario = Usuario_idusuario;
    }

    
   
}
