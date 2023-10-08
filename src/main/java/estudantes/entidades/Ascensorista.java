package estudantes.entidades;


import professor.entidades.Andar;
import professor.entidades.Elevador;

import java.sql.SQLOutput;
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

    public ArrayList<Animal> animaisDentroDoElevador;
    
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

        Animal animaisNoElevador[] = elevador.checarAnimaisNoElevador();
        Animal animaisNoAndar[] = andar.checarFilaParaElevador();

        //verificar o as condições do próximo animal da fila e colocá-lo no elevador
        if (animaisNoAndar.length != 0) {
            boolean podeEntrar;

            int peso = getPesoElevador(animaisNoElevador);
            if(verificarPeso(peso + animaisNoAndar[0].getPeso())){
                podeEntrar = true;
            } else {
                podeEntrar = false;
            }
            if(podeEntrar) {
                int[] temperaturas = getTemperaturas(animaisNoElevador);
                int tempIdeal = 0;
                if(temperaturas.length > 0){
                    int tempMin = temperaturas[0];
                    int tempMax = temperaturas[temperaturas.length - 1];
                    if (animaisNoAndar[0].getTemperaturaIdeal() + 15 >= elevador.getTemperaturaDoArCondicionado() ||
                        animaisNoAndar[0].getTemperaturaIdeal() - 15 <= elevador.getTemperaturaDoArCondicionado()) {
                            podeEntrar = true;
                    } else {
                        if (animaisNoAndar[0].getTemperaturaIdeal() < tempMin) {
                            tempMin = animaisNoAndar[0].getTemperaturaIdeal();
                            if (tempMin + 15 < tempMax - 15) {
                                tempIdeal = (int) Math.ceil((tempMin + tempMax) / 2);
                                podeEntrar = true;
                            }
                        } else if (animaisNoAndar[0].getTemperaturaIdeal() > tempMax) {
                            tempMax = animaisNoAndar[0].getTemperaturaIdeal();
                            if (tempMax - 15 < tempMin + 15) {
                                tempIdeal = (int) Math.ceil((tempMin + tempMax) / 2);
                                podeEntrar = true;
                            }
                        } else {
                            podeEntrar = false;
                        }
                    }
                } else {
                    tempIdeal = animaisNoAndar[0].getTemperaturaIdeal();
                }
                if (podeEntrar) {
                    animaisDentroDoElevador.add(animaisNoAndar[0]);
                    if(tempIdeal != 0){
                        elevador.setTemperaturaDoArCondicionado(tempIdeal);
                    }
                    elevador.embarcar(andar.chamarProximoDaFila());
                }
            }
        }
        //seguir a trajetória do elevador
        if(animaisDentroDoElevador.get(0).getAndarDesejado() > elevador.getAndar()){
            elevador.subir();
        } else if(elevador.getAndar() != 0) {
            elevador.descer();
        }
    }

    public int getMaiorAndarDesejado(Animal[] animaisNoElevador) {
        int maiorAndar = 0;
        for (int i = 0; i < animaisNoElevador.length; i++) {
            if (animaisNoElevador[i].getAndarDesejado() > maiorAndar) {
                maiorAndar = animaisNoElevador[i].getAndarDesejado();
            }
        }
        return maiorAndar;
    }

    public void dispensarAnimaisPossiveis(Elevador elevador, Andar andar){
        for(int i=0; i<animaisDentroDoElevador.size(); i++){
            if(animaisDentroDoElevador.get(i).getAndarDesejado() == elevador.getAndar()){
                elevador.desembarcar(animaisDentroDoElevador.get(i), andar);
                animaisDentroDoElevador.remove(i);
            }
        }
    }

    public int[] getTemperaturas(Animal[] animaisNoElevador){
        int temperaturas[] = new int[animaisNoElevador.length];
        for(int i=0; i<animaisNoElevador.length; i++){
            temperaturas[i] = animaisNoElevador[i].getTemperaturaIdeal();
        }
        Arrays.stream(temperaturas).sorted();
        return temperaturas;
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
