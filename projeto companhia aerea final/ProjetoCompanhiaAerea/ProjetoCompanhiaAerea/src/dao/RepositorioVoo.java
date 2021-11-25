package dao;

import entidade.Passageiro;
import entidade.Passagem;
import entidade.Voo;

import java.util.ArrayList;
import java.util.List;

/**
 * Cria uma instacia unica do repositorio..
 * onde ele nao pode gerar um novo - Singleton
 */
public class RepositorioVoo {

    private static RepositorioVoo instanciaUnica;

    private RepositorioVoo() { }

    public static synchronized RepositorioVoo getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new RepositorioVoo();
        }
        return instanciaUnica;
    }

    private List<Voo> listaVoos = new ArrayList<Voo>();
    private List<Passageiro> listaPassageiros = new ArrayList<Passageiro>();

    public List<Voo> getListaVoos() {
        return listaVoos;
    }

    public void setListaVoos(List<Voo> listaVoos) {
        this.listaVoos = listaVoos;
    }

    public List<Passageiro> getListaPassageiros() {
        return listaPassageiros;
    }

    public void setListaPassageiros(List<Passageiro> listaPassageiros) {
        this.listaPassageiros = listaPassageiros;
    }

    /**
     * Metodo utilizado para recuperar todos os voos
     * @return listaVoos
     */
    public List<Voo> recuperarTodosVoos(){
        return this.listaVoos;
    }

    public boolean adicionarVoo(Voo voo){
        this.listaVoos.add(voo);
        return true;
    }

    public boolean alterarVoo(Voo voo){
        Voo vooExiste = this.existeVoo(voo);
        if(vooExiste != null){
            this.listaVoos.remove(vooExiste);
            this.adicionarVoo(voo);
            return true;
        }else{
            System.out.println("Voo não existe !");
            return false;
        }
    }

    public boolean removerVoo(Voo voo){
        Voo vooExiste = this.existeVoo(voo);
        if (vooExiste != null){
            this.listaVoos.remove(vooExiste);
            return true;
        }else {
            System.out.println("Voo não existe !");
            return false;
        }
    }

    public Voo existeVoo(Voo voo){
        int index = this.listaVoos.indexOf(voo);
        if(index == -1){
            return null;
        }else{
            return this.listaVoos.get(index);
        }
    }

    public Passageiro existePassageiroVoo(Voo voo, int linha, int coluna){
        Passageiro passExiste = voo.getPassageiros()[linha][coluna];
        if(passExiste != null){
            return passExiste;
        }else{
            return null;
        }
    }

    public boolean adicionarPassageiroVoo(Passagem passagem){
        Voo vooExiste = this.existeVoo(passagem.getVoo());
        if(vooExiste != null){
            Passageiro passExiste = this.existePassageiroVoo(passagem.getVoo(), passagem.getLinha() -1, passagem.getColuna() -1);
            if(passExiste == null){
                vooExiste.getPassageiros()[passagem.getLinha() - 1][passagem.getColuna() - 1] = passagem.getPassageiro();
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    // ------------------------------------------------------------------
    // -- Passageiros --
    // ------------------------------------------------------------------

    /**
     * Varrer toda lista comparando cada objeto pelo codigo - se ele nao encontrar ele retorna -1
     * se ele encontrar ele vai retornar a posicao da lista..
     * @param passageiro
     * @return
     */
    public Passageiro existePassageiro(Passageiro passageiro){
        int index = this.listaPassageiros.indexOf(passageiro);
        if(index != -1){
            return this.listaPassageiros.get(index);
        }else{
            return null;
        }
    }

    public boolean adicionarPassageiro(Passageiro passageiro){
        this.listaPassageiros.add(passageiro);
        return true;
    }

    public boolean alterarPassageiro(Passageiro passageiro){
        Passageiro passExiste = this.existePassageiro(passageiro);
        if(passExiste != null){
            passExiste.setNome(passageiro.getNome());
            return true;
        }else{
            return false;
        }
    }

    public boolean removerPassageiro(Passageiro passageiro){
        Passageiro passExiste = this.existePassageiro(passageiro);
        if(passExiste != null){
            listaPassageiros.remove(passExiste);
            return true;
        }else{
            return false;
        }
    }

}
