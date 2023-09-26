package professor.entidades;


import estudantes.entidades.Animal;
import estudantes.entidades.Ascensorista;
import java.util.Random;
import professor.gui.Simulador;


/**
 * Classe que representa a arca com seus andares, elevador e ascensorista.
 * <br><br>
 * <strong>Não mexa aqui!!!</strong>
 * 
 * @author Jean Cheiran
 * @version 1.0
 */
public class Arca {
    
    private static final String[] PRENOMES_DE_ANIMAIS = {"Guimaraes","Machado","Clarice","Cecilia","Graciliano","Conceicao","Ariano","Carolina","Lima","Erico","Martha","Olavo","Lygia","Jose","Chico","Mia","Jose","Jose","Augusto"};
    private static final String[] SOBRENOMES_DE_ANIMAIS = {"Rosa","de Assis","Lispector","Meireles","Ramos","Evaristo","Suassuna","de Jesus","Barreto","Verissimo","Medeiros","Bilac","Bojunga","de Alencar","Buarque","Couto","Bonifacio","Saramago","dos Anjos"};
    private static final String[] PRIMEIRA_PARTE_ESPECIE = {"Chioglossa","Bufo","Crocodylus","Tropidurus","Struthio","Raphus","Rhynchocyon","Psychrolutes","Amphiprion","Felis","Canis","Hemidactylus","Lepidopus","Cicinnurus","Tyrannus","Draco"};
    private static final String[] SEGUNDA_PARTE_ESPECIE = {"lusitanica","periglenes","porosus","oreadicus","camelus","cucullatus","petersi","marcidus","clarkii","catus","familiaris","mabouia","caudatus","regius","melancholicus","volans"};

    private Random gerador;
    private int tempo = 0;
    private Elevador elevador;
    private Andar[] andares;
    private Ascensorista ascensorista;
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
        for (int i = 0; i < QUANTIDADE_DE_ANDARES_NA_ARCA; i++) {
            if (gerador.nextInt(5) == 0) { //20% de chance de gerar um animal em cada andar
                int id = gerador.nextInt(1000000);
                String nome = PRENOMES_DE_ANIMAIS[gerador.nextInt(PRENOMES_DE_ANIMAIS.length)]+" "+
                        SOBRENOMES_DE_ANIMAIS[gerador.nextInt(SOBRENOMES_DE_ANIMAIS.length)];
                String especie = PRIMEIRA_PARTE_ESPECIE[gerador.nextInt(PRIMEIRA_PARTE_ESPECIE.length)]+" "+
                        SEGUNDA_PARTE_ESPECIE[gerador.nextInt(SEGUNDA_PARTE_ESPECIE.length)];
                int andarDesejado = gerador.nextInt(QUANTIDADE_DE_ANDARES_NA_ARCA);
                int peso = gerador.nextInt(1000) + 1;
                int temperatura = gerador.nextInt(41);
                
                Animal novo = new Animal(id, nome, especie, andarDesejado, peso, temperatura);
                andares[i].colocarNaFila(novo);
            }
        }
        
        //TODO do professor
        
        //chamar ascensorista
        ascensorista.agir(elevador, andares[elevador.getAndar()]);
        
        //TODO do professor
        
        //atualiza a interface
        Simulador.getInstancia().atualizarInterface();
    }
}
