package estudantes.entidades;


/**
 * Classe que define um animal da arca.
 * <br><br>
 * Essa classe pode ser estendida. Adicionalmente, ela deve:
 * <br><br>
 * 1) ter um construtor completo,<br>
 * 2) implementar métodos de acesso (getters), sendo que métodos de modificação
 * (setters) NÃO devem ser implementados,<br>
 * 3) sobre-escrever os métodos toString, equals e hashCode,<br>
 * 4) deve implementar o método <i>aumentaEspera</i> conforme indicado na
 * documentação.
 * <br><br>
 * <strong>Não devem ser criados métodos adicionais nessa classe.</strong>
 * 
 * @author Jean Cheiran
 * @version 1.0
 */
public class Animal {
    
    /**
     * Limite da paciência de um animal esperando na fila antes de ir embora.
     */
    public final int PACIENCIA_MAXIMA = 25; //em segundos (ciclos de espera)
    
    private int id;
    private String nome;
    private String especie;
    private int peso; //em quilos
    private int andarDesejado; //0 é o térreo
    private int tempoDeEspera;
    private int temperaturaIdeal; //em graus Celsius
    
    /**
     * Construtor do animal.
     * Todos os atributos são passados por parâmetro, exceto o tempo de espera
     * que sempre começa em 0.
     * @param id
     * @param nome
     * @param especie
     * @param andarDesejado
     * @param peso
     * @param temperaturaIdeal 
     */
    public Animal(int id, String nome, String especie, int andarDesejado, 
            int peso, int temperaturaIdeal){
        /* TODO: codificar */
    }
    
    /**
     * Retorna o número de identificaçao do animal.
     * O valor é gerado aleatoriamente e está entre 0 e 999999.
     * @return número da identificação do animal
     */
    public int getId(){
        return id;
    }
    
    /**
     * Retorna o peso do animal.
     * O peso é gerado aleatoriamente e está entre 1 e 1.000 quilos.
     * @return peso do animal em quilos
     */
    public int getPeso(){
        return peso;
    }
    
    /**
     * Aumenta o tempo de espera um animal na fila quando passa um ciclo.
     * Esse é o tempo de espera na fila de um andar para embarcar no elevador.
     * Um ciclo ocorre sempre que o método de simular vida na arca é invocado.
     * Esse método não deve ser chamado fora da classe Arca.
     * <br><br>
     * A implementação desse método deve comparar a paciência do animal com
     * o tempo de espera depois de incrementado. Se ela for menor, uma exceção
     * deve ser lançada.
     * <br><br>
     * @throws RuntimeException se o animal está esperando a mais tempo que a paciência
     * @see professor.entidades.Arca#simularVida
     */
    public void aumentaEspera(){
        
    }
}
