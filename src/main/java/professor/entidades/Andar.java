package professor.entidades;


import estudantes.entidades.Animal;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Classe que representa um andar da arca onde animais embarcam e desembarcam.
 * <br><br>
 * <strong>Não mexa aqui!!!</strong>
 * 
 * @author Jean Cheiran
 * @version 1.0
 */
public class Andar {
    private int andar; //começa no 0
    private LinkedList<Animal> filaParaElevador;
    private HashSet<Animal> animaisQueDesceram;
    
    /**
     * Construtor do andar.
     * Ele sempre começa sem animais na fila e sem animais desembarcados.
     * @param andar número do andar (0 é térreo)
     */
    public Andar(int andar){
        this.andar = andar;
        filaParaElevador = new LinkedList<Animal>();
        animaisQueDesceram = new HashSet<Animal>();
    }
    
    /**
     * Retorna o número do andar
     * @return andar (0 é térreo)
     */
    public int getAndar(){
        return andar;
    }
    
    /**
     * Inclui o animal no fim da fila de espera para pegar o elevador.
     * Esse método é usado pelo simulador de vida da arca e não deveria ser
     * invocado por outras classes. Como se trata de uma fila, o animal é sempre
     * colocado no final e não é removido de nenhum outro lugar onde esteja.
     * @param animal o animal para ser colocado no final da fila (um valor null 
     * será ignorado)
     */
    public void colocarNaFila(Animal animal){
        if(animal != null){
            filaParaElevador.offer(animal);
        }
    }
    
    /**
     * Remove e retorna o animal de uma posição especificada da fila.
     * A fila começa no primeiro animal (cabeça) que está na posição 0. Os
     * demais elementos do fim da fila são reorganizados para ocuparem o espaço
     * do animal que saiu sem deixar buracos. A verificação desse método lança 
     * uma exceção quando se tenta tirar um animal de uma posição que não existe.
     * @param posicao posição do animal na fila (o primeiro animal está em 0)
     * @return o animal que ocupava a posição na fila
     * @throws IndexOutOfBoundsException se a posição for menor que zero ou 
     * igual ou maior ao tamanho da fila
     */
    public Animal tirarDaFila(int posicao){
        return filaParaElevador.remove(posicao);
    }
    
    /**
     * Remove a primeira ocorrência do animal especificado da fila.
     * Os animais são comparados na fila pelo método equals(Object o). Apenas o
     * primeiro elemeto encontrado (a ordem de busca é do início para o fim
     * da fila) será removido se houver duplicatas na fila. Se a fila não 
     * contém o animal, ela permanece inalterada.
     * @param animal animal para ser removido
     * @return true se o animal foi encontrado na fila; false caso contrário
     */
    public boolean tirarDaFila(Animal animal){
        return filaParaElevador.remove(animal);
    }
    
    /**
     * Remove e retorna o primeiro animal da fila (cabeça da fila).
     * Esse método chama o próximo animal da fila, removendo ele quando for
     * retornado.
     * @return o animal que é primeiro (cabeça) da fila
     */
    public Animal chamarProximoDaFila(){
        return filaParaElevador.poll();
    }
    
    /**
     * Devolve um vetor contendo todos os animais da fila para pegar o elevador.
     * Os animais serão retornados no vetor na mesma ordem da fila. O uso
     * desse vetor deve ser cuidadoso, pois os elementos são referências para
     * os animais que também estão na fila, ou seja, modificar o estado
     * interno de um animal do vetor causa modificação no mesmo animal na fila.
     * Recomenda-se o uso do método clone() em um animal antes de manipular 
     * seus dados para evitar efeito colateral.
     * @return vetor de animais na mesma ordem da fila (o animal da primeira
     * posição da fila estará no índice 0 do vetor e assim por diante)
     */
    public Animal[] checarFilaParaElevador(){
        return filaParaElevador.toArray(Animal[]::new);
    }
    
    /**
     * Retorna o tamanho atual da fila para pegar o elevador.
     * @return quantidade de animais na fila
     */
    public int consultarTamanhoDaFila(){
        return filaParaElevador.size();
    }
    
    /**
     * Coloca um animal no grupo de animais que desembarcaram no andar.
     * Esse método deve ser chamado pelo elevador para que um animal possa
     * sair do elevador e ser colocados junto de outros animais que desceram
     * do elevador nesse mesmo andar. 
     * @param animal animal que desembarcou (um valor null será ignorado)
     */
    public void desembarcar(Animal animal){
        if(animal != null){
            animaisQueDesceram.add(animal);
        }
    }
    
    /**
     * Devolve um vetor contendo todos os animais que desembarcaram nesse andar.
     * Os animais não estarão em uma ordem definida (por exemplo, ordem em que
     * desembarcaram), pois se misturaram com os demais quando desceram. O uso
     * desse vetor deve ser cuidadoso, pois os elementos são referências para
     * os animais que também estão no grupo de animais que desembarcaram, ou 
     * seja, modificar o estado interno de um animal do vetor causa modificação 
     * no mesmo animal do grupo no andar. Recomenda-se o uso do método clone() 
     * em um animal antes de manipular seus dados para evitar efeito colateral.
     * @return vetor de animais sem ordem pré-definida
     */
    public Animal[] checarDesembarcados(){
        return animaisQueDesceram.toArray(Animal[]::new);
    }
}
