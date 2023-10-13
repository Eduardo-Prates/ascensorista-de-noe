package estudantes.entidades;

public class Peixe extends Animal{

    public final int PACIENCIA_MAXIMA = 60;
    private String corDasEscamas;

    /**
     * Construtor do animal.
     *
     * @param id
     * @param nome
     * @param especie
     * @param andarDesejado
     * @param peso
     * @param temperaturaIdeal
     */
    public Peixe(int id, String nome, String especie, int andarDesejado, int peso, int temperaturaIdeal, String corDasEscamas) {
        super(id, nome, especie, andarDesejado, peso, temperaturaIdeal);
        this.corDasEscamas = corDasEscamas;
    }

    public String getCorDasEscamas() {
        return corDasEscamas;
    }

    /**
     * @return Uma String com a indicação da ação de nadar do peixe.
     */
    public String nadar(){
        return "nadando";
    }

    /**
     * Retorna uma representação em String do peixe.
     * A representação é composta por todos os atributos do peixe.
     * @return representação em String do peixe
     */
    @Override
    public String toString(){
        return super.toString() +
                "Cor das escamas: " + corDasEscamas;
    }

    /**
     * Retorna se o peixe é igual a outro.
     * @param o o outro peixe a ser comparado.
     * @return true se os peixes são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(this == o){
            return true;
        }

        if(!(o instanceof Peixe)){
            return false;
        } else {
            Peixe outroPeixe = (Peixe) o;
            return this.getId() == outroPeixe.getId() &&
                    this.getNome().equals(outroPeixe.getNome()) &&
                    this.getEspecie().equals(outroPeixe.getEspecie())&&
                    this.getPeso() == outroPeixe.getPeso() &&
                    this.getAndarDesejado() == outroPeixe.getAndarDesejado() &&
                    this.getTempoDeEspera() == outroPeixe.getTempoDeEspera() &&
                    this.getTemperaturaIdeal() == outroPeixe.getTemperaturaIdeal() &&
                    this.getCorDasEscamas().equals(outroPeixe.getCorDasEscamas());
        }

        /*
        return this.getId() == outroMamiferoVoador.getId() &&
                    this.getNome().equals(outroMamiferoVoador.getNome()) &&
                    this.getEspecie().equals(outroMamiferoVoador.getEspecie())&&
                    this.getPeso() == outroMamiferoVoador.getPeso() &&
                    this.getAndarDesejado() == outroMamiferoVoador.getAndarDesejado() &&
                    this.getTempoDeEspera() == outroMamiferoVoador.getTempoDeEspera() &&
                    this.getTemperaturaIdeal() == outroMamiferoVoador.getTemperaturaIdeal() &&
                    this.isPeludo() == outroMamiferoVoador.isPeludo();
         */
    }

    /**
     * Retorna o hashcode do peixe.
     * @return hashcode do peixe
     */
    @Override
    public int hashCode(){
        int hash = super.hashCode();
        hash *= 29 + this.corDasEscamas.hashCode();
        return hash;
    }
}
