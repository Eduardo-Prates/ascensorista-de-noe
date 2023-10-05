package estudantes.entidades;


import professor.entidades.Andar;
import professor.entidades.Elevador;


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
    
    /**
     * Construtor padrão de Ascensorista.
     * Esse construtor sem parâmetros que será usado pela Arca. Embora a
     * assinatura do construtor não deva ser mudada, o código interno pode
     * ser alterado conforme a necessidade.
     */
    public Ascensorista(){
        /* TODO: codificar */
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

        verificarSituacao(elevador, andar);

    }

    public void dispensarAnimaisPossiveis(Elevador elevador, Andar andar){
        Animal[] animais = elevador.checarAnimaisNoElevador();

        for(int i=0; i<animais.length; i++){
            if(animais[i].getAndarDesejado() == elevador.getAndar()){
                elevador.desembarcar(animais[i], andar);
            }
        }
    }

    //usar um vetor de que contenha os andares desejados pelos animais
    public void verificarSituacao(Elevador elevador, Andar andar){
        Animal[] animaisNoElevador = elevador.checarAnimaisNoElevador();
        Animal[] animaisNaFila = andar.checarFilaParaElevador();

        int pesoElevador = getPesoElevador(animaisNoElevador);
        int temperaturaElevador = elevador.getTemperaturaDoArCondicionado();
        boolean cheioDeAgua = elevador.isCheioDeAgua();

        switch(andar.getAndar()){
            case 4:
                break;
            case 3:
                break;
            case 2:
                break;
            case 1:
                break;
            case 0:
                //fazer verificação que se os animais querem o elevador com água, temperatura, etc.
                break;
        }
    }

    public int getPesoElevador(Animal[] animaisNoElevador){
        int peso=0;
        for(int i=0; i<animaisNoElevador.length; i++){
            peso+=animaisNoElevador[i].getPeso();
        }
        return peso;
    }

    public boolean verificarPeso(int peso){
        if(peso <= 2500){
            return true;
        } else {
            return false;
        }
    }
}
