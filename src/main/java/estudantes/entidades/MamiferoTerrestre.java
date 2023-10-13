package estudantes.entidades;

public class MamiferoTerrestre extends Mamifero{

    public final int PACIENCIA_MAXIMA = 35;

    /**
     * Construtor do mamifero terrestre.
     *
     * @param id
     * @param nome
     * @param especie
     * @param andarDesejado
     * @param peso
     * @param temperaturaIdeal
     * @param peludo
     */
    public MamiferoTerrestre(int id, String nome, String especie, int andarDesejado, int peso, int temperaturaIdeal, boolean peludo) {
        super(id, nome, especie, andarDesejado, peso, temperaturaIdeal, peludo);
    }

    /**
     * @return Uma String com a indicação da ação de andar do mamífero terrestre.
     */
    public String andar() {
        return "andando";
    }

    /**
     * Retorna uma representação em String do mamífero terrestre.
     * A representação é composta por todos os atributos do mamífero terrestre.
     * @return representação em String do mamífero terrestre
     */
    @Override
    public String toString(){
        return super.toString() +
                "Paciência máxima: " + PACIENCIA_MAXIMA + "\n";
    }

    /**
     * Retorna se o mamífero terrestre é igual a outro.
     * @param o o outro mamífero terrestre a ser comparado.
     * @return true se os mamíferos terrestres são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(this == o){
            return true;
        }

        if(!(o instanceof MamiferoTerrestre)){
            return false;
        } else {
            MamiferoTerrestre outroMamiferoTerrestre = (MamiferoTerrestre) o;
            return this.getId() == outroMamiferoTerrestre.getId() &&
                    this.getNome().equals(outroMamiferoTerrestre.getNome()) &&
                    this.getEspecie().equals(outroMamiferoTerrestre.getEspecie())&&
                    this.getPeso() == outroMamiferoTerrestre.getPeso() &&
                    this.getAndarDesejado() == outroMamiferoTerrestre.getAndarDesejado() &&
                    this.getTempoDeEspera() == outroMamiferoTerrestre.getTempoDeEspera() &&
                    this.getTemperaturaIdeal() == outroMamiferoTerrestre.getTemperaturaIdeal() &&
                    this.isPeludo() == outroMamiferoTerrestre.isPeludo();
        }
    }
}
