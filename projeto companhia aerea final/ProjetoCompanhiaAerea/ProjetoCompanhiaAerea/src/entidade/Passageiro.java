package entidade;

import java.util.Objects;
import java.util.Random;

public class Passageiro {

    private String nome;
    private Integer codigo;

    public Passageiro(){

    }
    public Passageiro(String nome){
        this.nome = nome;
        this.codigo = new Random().nextInt();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object obj) {
        Passageiro entrada = (Passageiro) obj;
        if(entrada.getCodigo().equals(this.codigo)){
            return true;
        }else{
            return false;
        }
    }

}
