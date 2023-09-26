package professor.entidades;


import estudantes.entidades.Animal;
import java.util.HashSet;

/**
 * Classe que representa o elevador com suas características.
 * <br><br>
 * <strong>Não mexa aqui!!!</strong>
 * 
 * @author Jean Cheiran
 * @version 1.0
 */
public class Elevador {
    
    /**
     * Limite de peso do elevador.
     * Quando esse valor é ultrapassado, o elevador não deve se movimentar.
     */
    public final int LIMITE_DE_PESO = 2500; //em quilos
    
    private HashSet<Animal> animais;
    private int andar; //0 é o térreo
    private int temperaturaDoArCondicionado; //em graus Celsius
    private boolean cheioDeAgua;
    
    /**
     * Construtor padrão do elevador.
     * Ele sempre começa vazio, sem água dentro e no andar 0 (térreo).
     */
    public Elevador(){
        animais = new HashSet<Animal>();
        andar = 0;
        cheioDeAgua = false;
    }

    /**
     * Inclui um animal no elevador.
     * O animal não é removido de qualquer outra estrutura que faça parte 
     * quando entra no elevador.
     * @param animal animal que quer embarcar (um valor null será ignorado)
     */
    public void embarcar(Animal animal) {
        System.out.println("Embarque no andar "+this.andar+": "+animal);
        if(animal == null){
            return;
        }
        animais.add(animal);
    }

    /**
     * Remove um animal do elevador e coloca ele no andar que desceu.
     * Não há qualquer verificação nesse método.
     * @param animal animal desembarcando
     * @param andar andar que está descendo
     */
    public void desembarcar(Animal animal, Andar andar) {
        System.out.println("Desembarque no andar "+this.andar+": "+animal);
        andar.desembarcar(animal);
        animais.remove(animal);
    }

    /**
     * Devolve um vetor contendo todos os animais do elevador.
     * Os animais não estarão em uma ordem definida (por exemplo, ordem em que
     * entraram), pois se misturaram dentro do elevador para se acomodar. O uso
     * desse vetor deve ser cuidadoso, pois os elementos são referências para
     * os animais que também estão no elevador, ou seja, modificar o estado
     * interno de um animal do vetor causa modificação no mesmo animal do
     * elevador. Recomenda-se o uso do método clone() em um animal antes de
     * manipular seus dados para evitar efeito colateral.
     * @return vetor de animais sem ordem garantida
     */
    public Animal[] checarAnimaisNoElevador() {
        return animais.toArray(Animal[]::new);
    }

    /**
     * Retorna o andar no qual o elevador está parado.
     * Qualquer uso de subir() ou descer() movimenta imediatamente o elevador,
     * então esse método é bastante sensível.
     * @return andar em que o elevador está no momento
     */
    public int getAndar(){
        return andar;
    }
    
    /**
     * Faz o elevador subir um andar imediatamente.
     * O tempo é relativo na Arca de Noé, então subir com o elevador tem efeito
     * imediato. A verificação nesse método lança uma exceção se o elevador
     * tentar subir para um andar inválido (acima do limite da arca - andar 4).
     * Além disso, o elevador não vai subir se o peso dos animais dentro dele
     * exceder a capacidade máxima do elevador.
     * @throws RuntimeException se o elevador tenta passar do último andar
     */
    public void subir() {
        if(andar < Arca.QUANTIDADE_DE_ANDARES_NA_ARCA - 1){
            int peso = 0;
            for(Animal animalNoElevador : animais){
                peso += animalNoElevador.getPeso();
            }
            if(peso <= LIMITE_DE_PESO){
                andar++;
            }
        }else{
            throw new RuntimeException("Elevador no ultimo andar e tentando subir");
        }
    }

    /**
     * Faz o elevador descer um andar imediatamente.
     * O tempo é relativo na Arca de Noé, então descer com o elevador tem efeito
     * imediato. A verificação nesse método lança uma exceção se o elevador
     * tentar descer para um andar inválido (abaixo do térreo - andar 0).
     * Além disso, o elevador não vai descer se o peso dos animais dentro dele
     * exceder a capacidade máxima do elevador.
     * @throws RuntimeException se o elevador tenta descer abaixo do térreo
     */
    public void descer() {
        if(andar > 0){
            int peso = 0;
            for(Animal animalNoElevador : animais){
                peso += animalNoElevador.getPeso();
            }
            if(peso <= LIMITE_DE_PESO){
                andar--;
            }
        }else{
            throw new RuntimeException("Elevador no terreo e tentando descer");
        }
    }
    
    /**
     * Retorna se o elevador está cheio d'água.
     * @return true se estiver inundado; false caso contrário
     */
    public boolean isCheioDeAgua() {
        return cheioDeAgua;
    }

    /**
     * Enche o elevador d'água imediatamente.
     * O tempo é relativo na Arca de Noé, então inundar o elevador tem efeito
     * imediato. Se o elevador já estiver cheio, o método não faz nada.
     */
    public void encher() {
        cheioDeAgua = true;
    }

    /**
     * Drena a água do elevador imediatamente.
     * O tempo é relativo na Arca de Noé, então drenar o elevador tem efeito
     * imediato. Se o elevador já estiver vazio, o método não faz nada.
     */
    public void drenar() {
        cheioDeAgua = false;       
    }
    
    /**
     * Retorna a temperatura atual do ar-condicionado.
     * @return temperatura do ar-condicionado em graus Celsius
     */
    public int getTemperaturaDoArCondicionado(){
        return temperaturaDoArCondicionado;
    }
    
    /**
     * Modifica imediatamente a temperatura do ar-condicionado.
     * O ar-condicionado funciona entre 0 e 40 graus Celsius. Se a tentativa
     * de mudança está dentro da faixa de funcionamento, ele altera o valor
     * e retorna um indicador de sucesso. Se a tentativa está fora da faixa
     * de funcionamento, ela nãotem efeito e retorna um indicador de fracasso.
     * @param novaTemperatura em graus Celsius entre 0 graus e 40 graus
     * @return true se conseguiu modificar a temperatura; false caso contrário
     */
    public boolean setTemperaturaDoArCondicionado(int novaTemperatura){
        if(novaTemperatura >= 0 && novaTemperatura <= 40){
            temperaturaDoArCondicionado = novaTemperatura;
            return true;
        }
        return false;
    }
}
