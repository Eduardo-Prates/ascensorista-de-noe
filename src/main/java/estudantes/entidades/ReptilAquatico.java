package estudantes.entidades;

public class ReptilAquatico extends Reptil{

    public final int PACIENCIA_MAXIMA = 15;

    /**
     * Construtor do réptil.
     *
     * @param id
     * @param nome
     * @param especie
     * @param andarDesejado
     * @param peso
     * @param temperaturaIdeal
     */
    public ReptilAquatico(int id, String nome, String especie, int andarDesejado, int peso, int temperaturaIdeal) {
        super(id, nome, especie, andarDesejado, peso, temperaturaIdeal);
    }

    /**
     * @return Uma String com a indicação da ação de nadar do réptil aquático.
     */
    public String nadar() {
        return "nadando";
    }


    /**
     * Retorna uma representação em String do réptil aquático.
     * A representação é composta por todos os atributos do réptil aquático.
     * @return representação em String do réptil aquático
     */
    @Override
    public String toString(){
        return  "Réptil aquático " + getId() + ":\n" +
                "Nome: " + getNome() + "\n" +
                "Espécie: " + getEspecie() + "\n" +
                "Andar desejado: " + getAndarDesejado() + "\n" +
                "Peso: " + getPeso() + "\n" +
                "Temperatura ideal: " + getTemperaturaIdeal() + "\n" +
                "Tempo de espera: " + getTempoDeEspera() + "\n" +
                "Paciência máxima: " + PACIENCIA_MAXIMA + "\n";
    }

    /**
     * Retorna se o réptil aquático é igual a outro.
     * @param o o outro réptil aquático a ser comparado.
     * @return true se os répteis aquáticos são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(this == o){
            return true;
        }

        if(!(o instanceof ReptilAquatico)){
            return false;
        } else {
            ReptilAquatico outroReptilAquatico = (ReptilAquatico) o;
            return this.getNome().equals(outroReptilAquatico.getNome()) &&
                    this.getEspecie().equals(outroReptilAquatico.getEspecie())&&
                    this.getPeso() == outroReptilAquatico.getPeso() &&
                    this.getTemperaturaIdeal() == outroReptilAquatico.getTemperaturaIdeal();
        }
    }

    /**
     * Retorna o hashcode do réptil aquático.
     * @return hashcode do réptil aquático
     */
    @Override
    public int hashCode(){
        return super.hashCode();
    }
}
