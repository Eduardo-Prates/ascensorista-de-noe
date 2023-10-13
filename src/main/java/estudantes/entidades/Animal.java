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
    public Animal(int id, String nome, String especie, int andarDesejado, int peso, int temperaturaIdeal){
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.andarDesejado = andarDesejado;
        this.peso = peso;
        this.temperaturaIdeal = temperaturaIdeal;
        tempoDeEspera = 0;
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
     * Retorna o nome do animal.
     * O nome é gerado aleatoriamente e é composto por 3 letras maiúsculas.
     * @return nome do animal
     */
    public String getNome() {return nome;}

    /**
     * Retorna a espécie do animal.
     * A espécie é gerada aleatoriamente e é composta por 3 letras maiúsculas.
     * @return espécie do animal
     */
    public String getEspecie() {return especie;}

    /**
     * Retorna o andar desejado pelo animal.
     * O andar desejado é gerado aleatoriamente e está entre 0 e 4.
     * @return andar desejado pelo animal
     */
    public int getAndarDesejado() {return andarDesejado;}
    
    /**
     * Retorna o peso do animal.
     * O peso é gerado aleatoriamente e está entre 1 e 1.000 quilos.
     * @return peso do animal em quilos
     */
    public int getPeso(){
        return peso;
    }

    /**
     * Retorna a temperatura ideal do animal.
     * A temperatura ideal é gerada aleatoriamente e está entre 0 e 50 graus
     * Celsius.
     * @return temperatura ideal do animal em graus Celsius
     */
    public int getTemperaturaIdeal(){return temperaturaIdeal;}

    /**
     * Retorna o tempo de espera do animal.
     * O tempo de espera é incrementado a cada ciclo que o animal passa na fila
     * de espera para embarcar no elevador.
     * @return tempo de espera do animal em segundos (ciclos de espera)
     */
    public int getTempoDeEspera(){return tempoDeEspera;}


    /**
     * Retorna uma representação em String do animal.
     * A representação é composta por todos os atributos do animal.
     * @return representação em String do animal
     */
    @Override //faça o toString com quebras de linha
    public String toString(){
        return  "\nIdentificador: " + id + "\n" +
                "Nome: " + nome + "\n" +
                "Espécie: " + especie + "\n" +
                "Andar desejado: " + andarDesejado + "\n" +
                "Peso: " + peso + "\n" +
                "Temperatura ideal: " + temperaturaIdeal + "\n" +
                "Tempo de espera: " + tempoDeEspera + "\n" +
                "Paciência máxima: " + PACIENCIA_MAXIMA + "\n";
    }

    /**
     * Retorna se o animal é igual a outro.
     * @param o o outro animal a ser comparado.
     * @return true se os animais são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(this == o){
            return true;
        }

        if(!(o instanceof Animal)){
            return false;
        } else {
            Animal outroAnimal = (Animal) o;
            return this.id == outroAnimal.id &&
                    this.nome.equals(outroAnimal.getNome()) &&
                    this.especie.equals(outroAnimal.getEspecie())&&
                    this.peso == outroAnimal.peso &&
                    this.andarDesejado == outroAnimal.andarDesejado &&
                    this.tempoDeEspera == outroAnimal.tempoDeEspera &&
                    this.temperaturaIdeal == outroAnimal.temperaturaIdeal;
        }
    }

    /**
     * Retorna o hashcode do animal.
     * @return hashcode do animal
     */
    @Override
    public int hashCode(){
        int hash = 3;
        hash *= 5 + this.id;
        hash *= 7 + this.nome.hashCode();
        hash *= 11 + this.especie.hashCode();
        hash *= 13 + this.andarDesejado;
        hash *= 17 + this.peso;
        hash *= 23 + this.temperaturaIdeal;
        return hash;
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
    public void aumentaEspera() {
        tempoDeEspera++;
        if (PACIENCIA_MAXIMA < tempoDeEspera){
            throw new RuntimeException("Animal esperando a mais tempo que a paciência");
        }
    }
}
