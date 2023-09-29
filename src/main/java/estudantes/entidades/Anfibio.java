package estudantes.entidades;

public class Anfibio extends Animal{

    /**
     * Construtor do anfíbio.
     * Todos os atributos são passados por parâmetro, exceto o tempo de espera
     * que sempre começa em 0.
     * @param id
     * @param nome
     * @param especie
     * @param andarDesejado
     * @param peso
     * @param temperaturaIdeal
     */
    public Anfibio(int id, String nome, String especie, int andarDesejado, int peso, int temperaturaIdeal) {
        super(id, nome, especie, andarDesejado, peso, temperaturaIdeal);
        PACIENCIA_MAXIMA = 45;
    }

    /**
     * @return Uma String com a indicação da ação de andar do anfíbio.
     */
    public String andar(){
        return "andando";
    }

    /**
     * @return Uma String com a indicação da ação de nadar do anfíbio.
     */
    public String nadar(){
        return "nadando";
    }

    /**
     * Retorna uma representação em String do anfíbio.
     * A representação é composta por todos os atributos do anfíbio.
     * @return representação em String do anfíbio
     */
    @Override
    public String toString(){
        return "Anfibio " + super.toString();
    }

    /**
     * Retorna se o anfíbio é igual a outro.
     * @param o o outro anfíbio a ser comparado.
     * @return true se os anfíbio são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(this == o){
            return true;
        }

        if(!(o instanceof Anfibio)){
            return false;
        } else {
            Anfibio outroAnfibio = (Anfibio) o;
            return this.getNome().equals(outroAnfibio.getNome()) &&
                    this.getEspecie().equals(outroAnfibio.getEspecie())&&
                    this.getPeso() == outroAnfibio.getPeso() &&
                    this.getTemperaturaIdeal() == outroAnfibio.getTemperaturaIdeal();
        }
    }
}
