package professor.entidades;

import estudantes.entidades.Anfibio;
import estudantes.entidades.Animal;
import estudantes.entidades.Ascensorista;
import estudantes.entidades.Ave;
import estudantes.entidades.AveVoadora;
import estudantes.entidades.MamiferoAquatico;
import estudantes.entidades.MamiferoTerrestre;
import estudantes.entidades.MamiferoVoador;
import estudantes.entidades.Peixe;
import estudantes.entidades.Reptil;
import estudantes.entidades.ReptilAquatico;
import java.util.Random;
import professor.gui.Simulador;


/**
 * Classe que representa a arca com seus andares, elevador e ascensorista.
 * <br><br>
 * <strong>Não mexa aqui!!!</strong>
 *
 * @author Jean Cheiran
 * @version 1.2
 */
public class Arca {

    private static final String[] PRENOMES_DE_ANIMAIS = {"Guimaraes","Machado","Clarice","Cecilia","Graciliano","Conceicao","Ariano","Carolina","Lima","Erico","Martha","Olavo","Lygia","Jose","Chico","Mia","Jose","Jose","Augusto"};
    private static final String[] SOBRENOMES_DE_ANIMAIS = {"Rosa","de Assis","Lispector","Meireles","Ramos","Evaristo","Suassuna","de Jesus","Barreto","Verissimo","Medeiros","Bilac","Bojunga","de Alencar","Buarque","Couto","Bonifacio","Saramago","dos Anjos"};
    private static final String[] PRIMEIRA_PARTE_ESPECIE = {"Chioglossa","Bufo","Crocodylus","Tropidurus","Struthio","Raphus","Rhynchocyon","Psychrolutes","Amphiprion","Felis","Canis","Hemidactylus","Lepidopus","Cicinnurus","Tyrannus","Draco"};
    private static final String[] SEGUNDA_PARTE_ESPECIE = {"lusitanica","periglenes","porosus","oreadicus","camelus","cucullatus","petersi","marcidus","clarkii","catus","familiaris","mabouia","caudatus","regius","melancholicus","volans"};
    private static final String[] CORES = {"arco-iris", "azul", "bege", "laranja", "prata", "purpura", "rosa", "verde"};

    public Random gerador;
    public int tempo = 0;
    public Elevador elevador;
    public Andar[] andares;
    public Ascensorista ascensorista;
    public static final int QUANTIDADE_DE_ANDARES_NA_ARCA = 5;

    /**
     * Construtor padrão da arca.
     * Ela sempre cria um novo elevador, um ascensorista e um andar para cada
     * valor dentro do limite de andares da arca.
     */
    public Arca() {
        gerador = new Random(1);
        elevador = new Elevador();
        andares = new Andar[QUANTIDADE_DE_ANDARES_NA_ARCA];
        for (int i = 0; i < QUANTIDADE_DE_ANDARES_NA_ARCA; i++) {
            andares[i] = new Andar(i);
        }
        ascensorista = new Ascensorista();
    }

    /**
     * Retorna o elevador da arca.
     * @return elevador da arca
     */
    public Elevador getElevador() {
        return elevador;
    }

    /**
     * Retorna um vetor com todos os andares da arca.
     * O uso desse vetor deve ser cuidadoso, pois os elementos são referências
     * diretas para os andares da arca, ou seja, modificar o estado interno de
     * um andar do vetor causa modificação no andar da arca.
     * @return vetor com andares da arca
     */
    public Andar[] getAndares() {
        return andares;
    }

    /**
     * Simula a vida dentro da arca e atualiza a interface.
     * Cria animais novos nas filas para pegar o elevador com determinada
     * frequência, verifica a mortalidade de animais por condições no elevador,
     * invoca a lógica do ascensorista para lidar com os animais e com o
     * controle do elevador, e avisa a interface do simulador que pode fazer
     * as atualizações com base no estado da arca.
     * @see java.util.Random
     * @see professor.gui.Simulador#atualizarInterface
     */
    public void simularVida() {
        //cria animais
        for (int i = 0; i < QUANTIDADE_DE_ANDARES_NA_ARCA; i++) {
            if (gerador.nextInt(2) == 0) { //2 = 50% de chance de gerar um animal em cada andar
                int id = gerador.nextInt(1000000);
                String nome = PRENOMES_DE_ANIMAIS[gerador.nextInt(PRENOMES_DE_ANIMAIS.length)]+" "+
                        SOBRENOMES_DE_ANIMAIS[gerador.nextInt(SOBRENOMES_DE_ANIMAIS.length)];
                String especie = PRIMEIRA_PARTE_ESPECIE[gerador.nextInt(PRIMEIRA_PARTE_ESPECIE.length)]+" "+
                        SEGUNDA_PARTE_ESPECIE[gerador.nextInt(SEGUNDA_PARTE_ESPECIE.length)];
                int andarDesejado = gerador.nextInt(QUANTIDADE_DE_ANDARES_NA_ARCA);
                int peso = gerador.nextInt(1000) + 1;
                int temperatura = gerador.nextInt(51) - 10;

                Animal novo;

                //escolhe o tipo de animal que gera
                switch(gerador.nextInt(9)){
                    case 0: novo = new Anfibio(id, nome, especie, andarDesejado, peso, temperatura);
                        break;
                    case 1: novo = new Ave(id, nome, especie, andarDesejado, peso, temperatura, CORES[gerador.nextInt(CORES.length)]);
                        break;
                    case 2: novo = new AveVoadora(id, nome, especie, andarDesejado, peso, temperatura, CORES[gerador.nextInt(CORES.length)]);
                        break;
                    case 3: novo = new MamiferoAquatico(id, nome, especie, andarDesejado, peso, temperatura, false);
                        break;
                    case 4: novo = new MamiferoTerrestre(id, nome, especie, andarDesejado, peso, temperatura, true);
                        break;
                    case 5: novo = new MamiferoTerrestre(id, nome, especie, andarDesejado, peso, temperatura, true);
                        break;
                    case 6: novo = new Peixe(id, nome, especie, andarDesejado, peso, temperatura, CORES[gerador.nextInt(CORES.length)]);
                        break;
                    case 7: novo = new Reptil(id, nome, especie, andarDesejado, peso, temperatura);
                        break;
                    case 8: novo = new ReptilAquatico(id, nome, especie, andarDesejado, peso, temperatura);
                        break;
                    default: novo = new Animal(id, nome, especie, andarDesejado, peso, temperatura);
                }

                andares[i].colocarNaFila(novo);
            }
        }

        //chamar ascensorista
        ascensorista.agir(elevador, andares[elevador.getAndar()]);

        //atualizar esperas nas filas e debandar os impacientes
        for (int i = 0; i < QUANTIDADE_DE_ANDARES_NA_ARCA; i++){
            Animal[] fila = andares[i].checarFilaParaElevador();
            for(Animal animal : fila){
                try{
                    animal.aumentaEspera();
                }catch(RuntimeException e){
                    andares[i].tirarDaFila(animal);
                    System.err.println(animal.getNome()+" cansou e foi embora!");
                }
            }
        }

        //verificar se animais morreram no elevador
        Animal[] bichosNoElevador = elevador.checarAnimaisNoElevador();
        for(Animal bicho : bichosNoElevador){
            //afogamentos
            if(bicho instanceof Ave || bicho instanceof AveVoadora || (bicho instanceof Reptil && !(bicho instanceof ReptilAquatico))
                    || bicho instanceof MamiferoTerrestre || bicho instanceof MamiferoVoador){
                if(elevador.isCheioDeAgua()){
                    throw new RuntimeException(bicho.getNome()+" morreu afogado!\n"+elevador+"\n"+bicho);
                }
            }
            //esturricação
            if(bicho instanceof Peixe || bicho instanceof MamiferoAquatico){
                if(!elevador.isCheioDeAgua()){
                    throw new RuntimeException(bicho.getNome()+" morreu esturricado!\n"+elevador+"\n"+bicho);
                }
            }
            //hipotermina ou hipertermia
            if(bicho.getTemperaturaIdeal() > elevador.getTemperaturaDoArCondicionado() + 15
                    || bicho.getTemperaturaIdeal() < elevador.getTemperaturaDoArCondicionado() - 15){
                throw new RuntimeException(bicho.getNome()+" morreu por causa da temperatura!\n"+elevador+"\n"+bicho);
            }
        }

        //movimentar elevador
        elevador.movimentar();

        //atualiza a interface
        Simulador.getInstancia().atualizarInterface();

        //para a simulação depois de 2 minutos
        System.out.println("[ --- tempo "+(tempo++)+" --- ]");
        if(tempo > 180){
            Simulador.pararSimulacao();
        }
    }
}