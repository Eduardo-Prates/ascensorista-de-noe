package estudantes.entidades;

import professor.entidades.Andar;
import professor.entidades.Elevador;

import java.util.Arrays;
import java.util.ArrayList;


/**
 * Classe que traz a lógica do algoritmo de uso do elevador.
 * <br><br>
 * Você pode incluir novos atributos e métodos nessa classe para criar
 * lógicas mais complexas para o gerenciamento do uso do elevador, mas esses
 * <strong>atributos e métodos devem ser todos privados</strong>. O único
 * método público deve ser "agir".
 *
 * @author Jean Cheiran
 * @version 1.0
 */
public class Ascensorista {

    private ArrayList<Animal> animaisDentroDoElevador;
    private boolean flag;
    private final int NIVEL_DA_AGUA = 0;
    private final int TEMPERATURA = 1;
    private final int SEM_ALTERACAO = 0;
    private final int ALAGADO = 1;
    private final int DRENADO = 2;

    /**
     * Construtor padrão de Ascensorista.
     * Esse construtor sem parâmetros que será usado pela Arca. Embora a
     * assinatura do construtor não deva ser mudada, o código interno pode
     * ser alterado conforme a necessidade.
     */
    public Ascensorista(){
        animaisDentroDoElevador = new ArrayList();
    }

    /**
     * Executa a lógica de controle do elevador e dos animais.
     * Esse método é o único método de controle invocado durante a simulação
     * de vida da arca.
     * <br><br>
     * Aqui podem ser feitas todas as verificações sobre os animais do elevador
     * e da fila de animais que estão esperando no andar. A partir desses
     * estados, você pode movimentar animais para dentro e para fora do
     * elevador usando os métodos "desembarcar" e "embarcar" por exemplo.
     * O estado do elevador também é importante para acionar os comandos do
     * elevador como "drenar", "encher", "subir" e "descer".
     * @param elevador o elevador controlado pelo ascensorista
     * @param andar o andar no qual o elevador está parado
     */
    public void agir(Elevador elevador, Andar andar) {
        dispensarAnimaisPossiveis(elevador, andar);

        if(avaliarSituacao(elevador, andar)){
            agir(elevador, andar);
        } else {
            alterarPosicaoDoElevador(elevador);
        }
    }

    /**
     * Método que dispensa os animais que estão no elevador e que desejam
     * descer no andar atual.
     * @param elevador
     * @param andar
     */
    private void dispensarAnimaisPossiveis(Elevador elevador, Andar andar){
        for(int i=0; i<animaisDentroDoElevador.size(); i++){
            if(animaisDentroDoElevador.get(i).getAndarDesejado() == elevador.getAndar()){
                elevador.desembarcar(animaisDentroDoElevador.get(i), andar);
                animaisDentroDoElevador.remove(i);
            }
        }
    }

    /**
     * Método que avalia a situação do elevador e do andar para decidir se
     * o elevador deve subir ou descer, se deve encher ou drenar, se deve
     * alterar a temperatura do ar condicionado, e se deve embarcar ou
     * desembarcar animais.
     * @param elevador
     * @param andar
     */
    private boolean avaliarSituacao(Elevador elevador, Andar andar){

        Animal animaisNoElevador[] = elevador.checarAnimaisNoElevador();
        Animal animaisNoAndar[] = andar.checarFilaParaElevador();

        if (animaisNoAndar.length != 0) {
            Animal proximoDaFila = animaisNoAndar[0];

            if(verificarPeso(animaisNoElevador, proximoDaFila)){
                int[] condicoesEntradaAnimal = new int[2];

                if(verificarAlagado(condicoesEntradaAnimal, animaisNoElevador, proximoDaFila, elevador)){

                    if (verificarTemperatura(condicoesEntradaAnimal, animaisNoElevador, proximoDaFila, elevador)) {
                        alojarAnimal(condicoesEntradaAnimal, proximoDaFila, elevador, andar);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Verifica o peso total dos animais no elevador.
     * @param animaisNoElevador
     * @return peso total dos animais no elevador
     */
    private int getPesoElevador(Animal[] animaisNoElevador){
        int peso=0;
        for(int i=0; i<animaisNoElevador.length; i++){
            peso+=animaisNoElevador[i].getPeso();
        }
        return peso;
    }

    /**
     * Verifica se o peso do próximo animal que está na fila não extrapolou
     * o peso máximo do elevador.
     * @param animaisNoElevador
     * @param proximoDaFila
     * @return true se o peso do animal que está na fila é compatível com o
     * peso máximo do elevador, false caso não for.
     */
    private boolean verificarPeso(Animal[] animaisNoElevador, Animal proximoDaFila){
        int peso = getPesoElevador(animaisNoElevador) + proximoDaFila.getPeso();
        System.out.println("Peso do elevador: " + getPesoElevador(animaisNoElevador));
        System.out.println("Peso do próximo da fila: " +proximoDaFila.getPeso());
        if(peso <= 2500){
            System.out.println("entrou");
            return true;
        } else {
            System.out.println("não entrou");
            return false;
        }
    }

    private boolean verificarAlagado(int[] condicoesEntradaAnimal, Animal[] animaisNoElevador, Animal proximoDaFila, Elevador elevador){

        int necessidadeAlagado = 0;

        String classe = proximoDaFila.getClass().getName();
        String[] classeRepartida = classe.split("\\.");
        String nomeClasse = classeRepartida[2];

        switch (nomeClasse) {
            case "Ave", "AveVoadora", "MamiferoTerrestre", "MamiferoVoador", "Reptil" -> {
                necessidadeAlagado = DRENADO;
            }
            case "MamiferoAquatico", "Peixe" -> {
                necessidadeAlagado = ALAGADO;
            }
            case "Anfibio", "ReptilAquatico" -> {
                necessidadeAlagado = SEM_ALTERACAO;
            }
            default -> {
                return false;
            }
        }

        if(animaisNoElevador.length == 0){
            condicoesEntradaAnimal[NIVEL_DA_AGUA] = necessidadeAlagado;
            return true;
        } else {
            boolean alagado = elevador.isCheioDeAgua();
            boolean podeSerInverso = verificarNecessidadeAlagadoNoElevador(animaisNoElevador, elevador);

            if(necessidadeAlagado == ALAGADO){
                if(alagado){
                    condicoesEntradaAnimal[NIVEL_DA_AGUA] = SEM_ALTERACAO;
                    return true;
                } else {
                    if(podeSerInverso){
                        condicoesEntradaAnimal[NIVEL_DA_AGUA] = ALAGADO;
                        return true;
                    } else {
                        return false;
                    }
                }
            } else if(necessidadeAlagado == DRENADO){
                if(alagado){
                    if(podeSerInverso){
                        condicoesEntradaAnimal[NIVEL_DA_AGUA] = DRENADO;
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    condicoesEntradaAnimal[NIVEL_DA_AGUA] = SEM_ALTERACAO;
                    return true;
                }
            } else {
                condicoesEntradaAnimal[NIVEL_DA_AGUA] = SEM_ALTERACAO;
                return true;
            }
        }
    }

    private boolean verificarNecessidadeAlagadoNoElevador(Animal[] animaisNoElevador, Elevador elevador){

        for (Animal animal : animaisNoElevador) {
            String classe = animal.getClass().getName();
            String[] classeRepartida = classe.split("\\.");
            String nomeClasse = classeRepartida[2];

            if(elevador.isCheioDeAgua()) {
                switch (nomeClasse) {
                    case "Peixe", "MamiferoAquatico" -> {
                        return false;
                    }
                }
            } else {
                switch (nomeClasse) {
                    case "Ave", "AveVoadora", "MamiferoTerrestre", "MamiferoVoador", "Reptil" -> {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Verifica se a temperatura do próximo animal que está na fila não
     * extrapolou a temperatura máxima do elevador.
     * @param condicoesEntradaAnimal
     * @param animaisNoElevador
     * @param proximoDaFila
     * @param elevador
     * @return true se a temperatura do animal que está na fila é compatível
     * com a temperatura máxima do elevador, false caso não for.
     */
    private boolean verificarTemperatura(int[] condicoesEntradaAnimal, Animal[] animaisNoElevador, Animal proximoDaFila, Elevador elevador){
        int[] temperaturas = getTemperaturas(animaisNoElevador);

        if(temperaturas.length == 0){
            condicoesEntradaAnimal[TEMPERATURA] = proximoDaFila.getTemperaturaIdeal();
            return true;
        } else {
            int tempMin = temperaturas[0];
            int tempMax = temperaturas[temperaturas.length - 1];

            if (proximoDaFila.getTemperaturaIdeal() + 15 >= elevador.getTemperaturaDoArCondicionado() &&
                    proximoDaFila.getTemperaturaIdeal() - 15 <= elevador.getTemperaturaDoArCondicionado()) {
                return true;
            } else {
                if (proximoDaFila.getTemperaturaIdeal() < tempMin) {
                    tempMin = proximoDaFila.getTemperaturaIdeal();
                    if (tempMin + 15 < tempMax - 15) {
                        condicoesEntradaAnimal[TEMPERATURA] = (int) (tempMin + tempMax) / 2;
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    tempMax = proximoDaFila.getTemperaturaIdeal();
                    if (tempMax - 15 < tempMin + 15) {
                        condicoesEntradaAnimal[TEMPERATURA] = (int) (tempMin + tempMax) / 2;
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    /**
     * Retorna um array com as temperaturas dos animais que estão no elevador.
     * @param animaisNoElevador
     * @return array com as temperaturas dos animais que estão no elevador
     */
    private int[] getTemperaturas(Animal[] animaisNoElevador){
        int temperaturas[] = new int[animaisNoElevador.length];
        for(int i=0; i<animaisNoElevador.length; i++){
            temperaturas[i] = animaisNoElevador[i].getTemperaturaIdeal();
        }
        Arrays.stream(temperaturas).sorted();
        return temperaturas;
    }

    /**
     * Aloja o animal no elevador e altera as condições do elevador de acordo
     * com as condições do animal.
     * @param condicoesEntradaAnimal
     * @param proximoDaFila
     * @param elevador
     * @param andar
     */
    private void alojarAnimal(int[] condicoesEntradaAnimal, Animal proximoDaFila, Elevador elevador, Andar andar){
        int condicaoAlagamento = condicoesEntradaAnimal[NIVEL_DA_AGUA];
        int condicaoTemperatura = condicoesEntradaAnimal[TEMPERATURA];

        if(condicaoAlagamento != SEM_ALTERACAO){
            if(condicaoAlagamento == ALAGADO){
                elevador.encher();
            } else {
                elevador.drenar();
            }
        }
        if(condicaoTemperatura != SEM_ALTERACAO){
            elevador.setTemperaturaDoArCondicionado(condicaoTemperatura);
        }
        animaisDentroDoElevador.add(proximoDaFila);
        elevador.embarcar(andar.chamarProximoDaFila());
    }

    /**
     * Método que altera a posição do elevador. Sobe ou desce o elevador.
     * @param elevador
     */
    private void alterarPosicaoDoElevador(Elevador elevador){
        if(elevador.getAndar() == 2){
            flag = true;
        }
        if((elevador.getAndar() == 1 || elevador.getAndar() == 3) && flag){
            if(elevador.getAndar() == 3){
                elevador.subir();
            } else {
                elevador.descer();
            }
            flag = false;
        } else {
            if(!animaisDentroDoElevador.isEmpty()){
                if(animaisDentroDoElevador.get(0).getAndarDesejado() > elevador.getAndar()){
                    elevador.subir();
                } else if(elevador.getAndar() != 0) {
                    elevador.descer();
                }
            } else {
                if(elevador.getAndar()>=3){
                    elevador.descer();
                } else {
                    elevador.subir();
                }
            }
        }
    }
}
