package run;

import dao.RepositorioVoo;
import entidade.Passageiro;
import entidade.Passagem;
import entidade.Voo;

import java.util.Scanner;

public class AbcArea {
    public static void main(String[] args) {

        RepositorioVoo repo = RepositorioVoo.getInstance();

        Scanner scanText = new Scanner(System.in);
        Scanner scanNumber = new Scanner(System.in);

        int menuPrincpal = 0;

        Double distancia = 0.0;
        Integer quantidade = 0;
        int linhas = 0;
        int coluna = 0;

        while (menuPrincpal != 9) {

            System.out.println("Companhia Aérea ABC:");

            System.out.println("1 - Cadastrar Vôo");
            System.out.println("2 - Comprar Passagem");
            System.out.println("3 - Listar Vôos");
            System.out.println("4 - Menu Passageiro");
            System.out.println("5 - Menu Vôo");

            menuPrincpal = scanNumber.nextInt();

            switch (menuPrincpal) {

                case (1):
                    System.out.println("-------Cadastrar Vôo-------");
                    System.out.println("Digite a distância a ser percorrida: ");
                    distancia = scanNumber.nextDouble();
                    System.out.println("Qtd de filas: ");
                    linhas = scanNumber.nextInt();
                    System.out.println("Qtd de colunas: ");
                    coluna = scanNumber.nextInt();
                    quantidade = linhas * coluna;

                    repo.adicionarVoo(new Voo(distancia,quantidade,linhas,coluna));
                    System.out.println(" ---- voo cadastrado ----");

                    break;
                case (2):

                    System.out.println("--------- Comprar Passagem ----------");
                    System.out.println("Escola o Voo:");
                    repo.recuperarTodosVoos().forEach(voo1 ->
                            System.out.println("Codigo: " + voo1.getCodigo() +
                                    "\nDistancia:" + voo1.getDistancia() +
                                    "\nQuantidade de assentos: " + voo1.getQuantidade() + "\n"
                            ));
                    System.out.println("Digite o codigo do Vôo:");
                    Voo vooPassagem = new Voo();
                    vooPassagem.setCodigo(scanNumber.nextInt());
                    vooPassagem = repo.existeVoo(vooPassagem);

                    System.out.println("Informe o codigo do Passageiro:");
                    Passageiro passVoo = new Passageiro();
                    passVoo.setCodigo(scanNumber.nextInt());

                    System.out.println(" -- Lugares --");
                    imprimirPassageirosVoo(vooPassagem);

                    System.out.println(" -- Escola o seu lugar --");
                    System.out.println("Fila:");
                    int filaPassagem = scanNumber.nextInt();
                    System.out.println("Coluna:");
                    int colunaPassagem = scanNumber.nextInt();

                    Passagem passagemVoo = new Passagem(vooPassagem, passVoo, filaPassagem, colunaPassagem);
                    repo.adicionarPassageiroVoo(passagemVoo);

                    break;
                case (3):
                    System.out.println("Listar Vôos");
                    repo.recuperarTodosVoos().forEach(voo1 ->
                            System.out.println("Codigo: " + voo1.getCodigo() +
                                    "\nDistancia:" + voo1.getDistancia() +
                                    "\nQuantidade de assentos: " + voo1.getQuantidade() + "\n"
                            ));
                    break;
                case (4):
                    //Menu de passageiros ----
                    menuPassageiro();

                    break;
                case (5):
                    System.out.println("menu Voo");
                    //Menu de Voo
                    menuVoo();

                    break;
                default:
                    System.out.println("Opção Inválida");
                    break;

            }
        }


    }

    /**
     * Menu de passageiros, fluxo de passageiros
     */
    public static void menuPassageiro(){

        RepositorioVoo repo = RepositorioVoo.getInstance();

        Scanner scanNumero = new Scanner(System.in);
        Scanner scanTexto = new Scanner(System.in);

        int opcao = 0;

        System.out.println(" ---- Menu Passageiro ----");
        System.out.println("1 - Cadastrar Passageiro");
        System.out.println("2 - Alterar Passageiro");
        System.out.println("3 - Remover Passageiro");
        System.out.println("4 - Sair");

        opcao = scanNumero.nextInt();

        switch (opcao){
            case (1):
                System.out.println(" -- Cadastro Passageiro --");
                System.out.println("Informe o seu nome");
                Passageiro novo = new Passageiro(scanTexto.nextLine());
                System.out.println("Cadastrato: " + novo.getNome() + " - " + novo.getCodigo());
                repo.adicionarPassageiro(novo);
                break;
            case (2):
                System.out.println(" -- Alterar Passageiro --");
                System.out.println("Informe o codigo do passageiro:");
                Passageiro passAlterar = new Passageiro();
                passAlterar.setCodigo(scanNumero.nextInt());

                passAlterar = repo.existePassageiro(passAlterar);

                if(passAlterar != null){
                    System.out.println("Informe o novo nome:");
                    passAlterar.setNome(scanTexto.nextLine());
                    System.out.println("Cadastrato: " + passAlterar.getNome() + " - " + passAlterar.getCodigo());
                }else{
                    System.out.println("Passageiro não existe");
                }
                break;
            case (3):
                System.out.println(" -- Remover Passageiro --");

                System.out.println("Informe o codigo do passageiro:");
                Passageiro passRemover = new Passageiro();
                passRemover.setCodigo(scanNumero.nextInt());

                passRemover = repo.existePassageiro(passRemover);

                if(passRemover != null){
                    repo.removerPassageiro(passRemover);
                    System.out.println("Cadastrato Removido: " + passRemover.getNome() + " - " + passRemover.getCodigo());
                }else{
                    System.out.println("Passageiro não existe");
                }
                break;
            default:
                break;
        }
    }

    /**
     * Menu Vôo, fluxo de vôo
     */
    public static void menuVoo(){

        Scanner scanNumero = new Scanner(System.in);
        Scanner scanTexto = new Scanner(System.in);
        RepositorioVoo repo = RepositorioVoo.getInstance();
        int opcao = 0;

        System.out.println(" ---- Menu Vôo ----");
        System.out.println("1 - Alterar Vôo");
        System.out.println("2 - Remover Vôo");
        System.out.println("4 - Sair");

        opcao = scanNumero.nextInt();

        switch (opcao){
            case (1):
                System.out.println(" -- Alterar Vôo --");
                System.out.println("Informe o codigo do Voo:");
                Voo vooAlterar = new Voo();
                vooAlterar.setCodigo(scanNumero.nextInt());

                vooAlterar = repo.existeVoo(vooAlterar);

                if(vooAlterar != null){
                    System.out.println("Informe o novo nome:");
                    vooAlterar.setCodigo(scanTexto.nextInt());
                    System.out.println("Voo: " + vooAlterar.getCodigo() + " - " + vooAlterar.getDistancia()
                            + " - " + vooAlterar.getQuantidade());
                }else{
                    System.out.println("Voo não existe");
                }
                break;
            case (2):
                System.out.println(" -- Remover Vôo --");
                System.out.println("Informe o codigo do Voo:");
                Voo vooRemover = new Voo();
                vooRemover.setCodigo(scanNumero.nextInt());

                vooRemover = repo.existeVoo(vooRemover);

                if(vooRemover != null){
                    repo.removerVoo(vooRemover);
                    System.out.println("Voo Removido: " + vooRemover.getCodigo() + " - " + vooRemover.getDistancia()
                            + " - " + vooRemover.getQuantidade());

                }else{
                    System.out.println("Voo não existe");
                }
                break;
            default:
                break;
        }
    }

    /**
     * Para imprimir o passageiro, temos que criar dois for. Um para ler a linha e outro para ler a coluna.
     * aqui iremos imprimir o que falamos lá na classe voo, falando sobre os aviões no Brasil.
     * @param voo
     */
    public static void imprimirPassageirosVoo(Voo voo){

        for(int i = 0; i < voo.getPassageiros().length; i++) {
            for (int j = 0; j < voo.getPassageiros()[0].length; j++) {
                System.out.print("i:" + (i + 1) + " j:"+ (j+1) + " => " );
                if(voo.getPassageiros()[i][j] != null){
                    System.out.print(voo.getPassageiros()[i][j].getCodigo() +
                            " - " + voo.getPassageiros()[i][j].getNome() + " \n");
                }else{
                    System.out.println(" Null \n");
                }
            }
        }
    }
}
