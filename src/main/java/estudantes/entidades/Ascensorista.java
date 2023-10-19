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
 * @author Eduardo Prates Tiadoro
 * @author Reinaldo Zimmer Wendt
 * @version 1.0
 */
public class Ascensorista {

    //lista de animais que estão dentro do elevador.
    private ArrayList<Animal> animaisDentroDoElevador;

    //auxilia na tomada de decisão de subir ou descer o elevador.
    private boolean flag;

    //Referente a posicao no array de condicoesEntradaAnimal que
    // indica se o animal precisa de alagamento ou não.
    private final int NIVEL_DA_AGUA = 0;

    //Referente a posicao no array de condicoesEntradaAnimal que
    // indica a temperatura ideal do animal.

    private final int TEMPERATURA = 1;
    private final int TEMPERATURA_MINIMA_ELEVADOR = 0;
    private final int TEMPERATURA_MAXIMA_ELEVADOR = 40;

    //Constante que indica que o animal não precisa modificação
    // nas condições de água e temperatura
    private final int SEM_ALTERACAO = 100;

    //Constantes que indicam se o animal precisa de alagamento, se o animal
    // precisa de drenagem ou se o animal não precisa de alteração.
    private final int ALAGADO = 1;
    private final int DRENADO = 2;

    //Indica o peso máximo do elevador.
    private final int PESO_MAXIMO_ELEVADOR = 2500;

    //Indica o intervalo de temperatura que os animais podem suportar
    //para menos ou para mais.
    private final int INTERVALO_TEMPERATURA = 15;

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

        //avalia a situação do elevador, se for possível, aloja o animal e chama a função
        //recursivamente, e, se não for possível, altera a posição do elevador
        if(avaliarSituacao(elevador, andar)){
            agir(elevador, andar);
        } else {
            alterarPosicaoDoElevador(elevador);
        }
    }

    /**
     * Método que dispensa os animais que estão no elevador e que desejam
     * descer no andar atual.
     * @param elevador o elevador controlado pelo ascensorista
     * @param andar o andar no qual o elevador está parado
     */
    private void dispensarAnimaisPossiveis(Elevador elevador, Andar andar){
        for(int i=0; i<animaisDentroDoElevador.size(); i++){
            if(animaisDentroDoElevador.get(i).getAndarDesejado() == elevador.getAndar()){
                System.out.println("Saiu no andar " + elevador.getAndar());
                elevador.desembarcar(animaisDentroDoElevador.get(i), andar);
                animaisDentroDoElevador.remove(i);
            }
        }
    }

    /**
     * Método que avalia a situação do elevador e do andar para decidir se
     * o elevador deve encher ou drenar, se deve alterar a temperatura do
     * ar condicionado, e se deve embarcar o animal
     * @param elevador o elevador controlado pelo ascensorista
     * @param andar o andar no qual o elevador está parado
     * @return true se a ação foi realizada com sucesso, false caso contrário
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
     * Verifica se o peso do próximo animal que está na fila não extrapolou
     * o peso máximo do elevador.
     * @param animaisNoElevador array de animais no elevador
     * @param proximoDaFila próximo animal na fila
     * @return true se o peso do animal que está na fila é compatível com o
     * peso máximo do elevador, false caso não for
     */
    private boolean verificarPeso(Animal[] animaisNoElevador, Animal proximoDaFila){
        int peso = getPesoElevador(animaisNoElevador) + proximoDaFila.getPeso();
        if(peso <= PESO_MAXIMO_ELEVADOR){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checa o peso total dos animais no elevador.
     * @param animaisNoElevador array de animais no elevador
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
     * Verifica a necessidade de alagamento do elevador com base no tipo de
     * animal a ser embarcado. Também verifica se a troca entre alagado e
     * drenado pode ser inversa sem afetar os animais que estão no elevador.
     * @param condicoesEntradaAnimal array de condições de entrada do animal
     * @param animaisNoElevador array de animais no elevador
     * @param proximoDaFila próximo animal na fila
     * @param elevador o elevador controlado pelo ascensorista
     * @return true se a necessidade de alagamento foi avaliada com sucesso, false caso contrário
     */
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

        //se não houver animais no elevador, o nível de água do elevador é o ideal do animal
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

    /**
     * Verifica se a troca de entre alagado e drenado não afeta os animais
     * que estão no elevador.
     * @param animaisNoElevador array de animais no elevador
     * @param elevador o elevador controlado pelo ascensorista
     * @return true se a troca de entre alagado e drenado é segura, false caso contrário
     */
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
     * extrapola ou interpola a temperatura do elevador.
     * @param condicoesEntradaAnimal array de condições de entrada do animal
     * @param animaisNoElevador array de animais no elevador
     * @param proximoDaFila próximo animal na fila
     * @param elevador o elevador controlado pelo ascensorista
     * @return true se a temperatura do animal que está na fila é compatível
     * com a temperatura máxima do elevador, false caso não for
     */
    private boolean verificarTemperatura(int[] condicoesEntradaAnimal, Animal[] animaisNoElevador, Animal proximoDaFila, Elevador elevador){

        int[] temperaturas = getTemperaturas(animaisNoElevador);

        //se não houver animais no elevador, a temperatura do elevador é a ideal do animal
        if(animaisNoElevador.length == 0){
            condicoesEntradaAnimal[TEMPERATURA] = proximoDaFila.getTemperaturaIdeal();
            return true;
        } else {
            int tempMin = temperaturas[0];
            int tempMax = temperaturas[temperaturas.length - 1];

            //verifica se a temperatura do animal está nos padrões do elevador,
            //se sim, retorna true e não faz nenhuma alteração
            //se não, verifica a condição e faz as alterações necessárias
            if (proximoDaFila.getTemperaturaIdeal() + INTERVALO_TEMPERATURA >= elevador.getTemperaturaDoArCondicionado() &&
                    proximoDaFila.getTemperaturaIdeal() - INTERVALO_TEMPERATURA <= elevador.getTemperaturaDoArCondicionado()) {
                condicoesEntradaAnimal[TEMPERATURA] = SEM_ALTERACAO;
                return true;
            } else {
                if (proximoDaFila.getTemperaturaIdeal() < tempMin) {
                    tempMin = proximoDaFila.getTemperaturaIdeal();
                    if (tempMin + INTERVALO_TEMPERATURA > tempMax - INTERVALO_TEMPERATURA) {
                        condicoesEntradaAnimal[TEMPERATURA] = (int) (tempMin + tempMax) / 2;
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    tempMax = proximoDaFila.getTemperaturaIdeal();
                    if (tempMax - INTERVALO_TEMPERATURA < tempMin + INTERVALO_TEMPERATURA) {
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
     * @param animaisNoElevador array de animais no elevador
     * @return array com as temperaturas dos animais que estão no elevador
     */
    private int[] getTemperaturas(Animal[] animaisNoElevador){
        int[] temperaturas = new int[animaisNoElevador.length];
        for(int i=0; i<animaisNoElevador.length; i++){
            temperaturas[i] = animaisNoElevador[i].getTemperaturaIdeal();
        }
        temperaturas = Arrays.stream(temperaturas).sorted().toArray();
        return temperaturas;
    }

    /**
     * Aloja o animal no elevador e altera as condições do elevador de acordo
     * com as condições do animal.
     * @param condicoesEntradaAnimal array de condições de entrada do animal
     * @param proximoDaFila próximo animal na fila
     * @param elevador o elevador controlado pelo ascensorista
     * @param andar o andar no qual o elevador está parado
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
            if(!(elevador.setTemperaturaDoArCondicionado(condicaoTemperatura))){
                if(condicaoTemperatura < 0){
                    elevador.setTemperaturaDoArCondicionado(TEMPERATURA_MINIMA_ELEVADOR);
                } else {
                    elevador.setTemperaturaDoArCondicionado(TEMPERATURA_MAXIMA_ELEVADOR);
                }
            }
        }
        animaisDentroDoElevador.add(proximoDaFila);
        elevador.embarcar(andar.chamarProximoDaFila());
    }

    /**
     * Altera a posição do elevador de acordo com a situação do elevador e
     * dos animais que estão dentro dele.
     * @param elevador o elevador controlado pelo ascensorista
     */
    private void alterarPosicaoDoElevador(Elevador elevador){

        //caso o elevador esteja no 3º ele seta a flag como true,
        //para que na próxima vez que ele chegar no 2º ou 4º andar, ele
        //alcance o andar mínimo ou o máximo, e defina a flag como false novamente
        if(elevador.getAndar() == 2){
            flag = true;
        }

        if((elevador.getAndar() == 1 || elevador.getAndar() == 3) && flag){
            //garante que o andar mais alto e o mais baixo sejam atingidos
            //quando se estiver subindo e descendo, respectivamente
            if(elevador.getAndar() == 3){
                elevador.subir();
            } else {
                elevador.descer();
            }
            flag = false;
        } else {
            if(!animaisDentroDoElevador.isEmpty()){
                //garante que a última demanda seja atendida como principal
                if(animaisDentroDoElevador.get(0).getAndarDesejado() > elevador.getAndar()){
                    elevador.subir();
                } else if(elevador.getAndar() != 0) {
                    elevador.descer();
                }
            } else {
                //garante que o elevador não pare caso não haja demanda
                if(elevador.getAndar()>=3){
                    elevador.descer();
                } else {
                    elevador.subir();
                }
            }
        }
    }
}
