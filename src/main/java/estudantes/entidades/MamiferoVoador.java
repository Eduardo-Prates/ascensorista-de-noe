package estudantes.entidades;

public class MamiferoVoador extends Mamifero{

    public final int PACIENCIA_MAXIMA = 15;

    /**
     * Construtor do mamifero voador.
     *
     * @param id
     * @param nome
     * @param especie
     * @param andarDesejado
     * @param peso
     * @param temperaturaIdeal
     * @param peludo
     */
    public MamiferoVoador(int id, String nome, String especie, int andarDesejado, int peso, int temperaturaIdeal, boolean peludo) {
        super(id, nome, especie, andarDesejado, peso, temperaturaIdeal, peludo);
    }

    /**
     * @return Uma String com a indicação da ação de voar do mamífero voador.
     */
    public String voar(){
        return "voando";
    }

    /**
     * Retorna uma representação em String do mamífero voador.
     * A representação é composta por todos os atributos do mamífero voador.
     * @return representação em String do mamífero voador
     */
    @Override
    public String toString(){
        return super.toString() +
                "Paciência máxima: " + PACIENCIA_MAXIMA + "\n";
    }

    /**
     * Retorna se o mamífero voador é igual a outro.
     * @param o o outro mamífero voador a ser comparado.
     * @return true se os mamíferos voadores são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(this == o){
            return true;
        }

        if(!(o instanceof MamiferoVoador)){
            return false;
        } else {
            MamiferoVoador outroMamiferoVoador = (MamiferoVoador) o;
            return this.getId() == outroMamiferoVoador.getId() &&
                    this.getNome().equals(outroMamiferoVoador.getNome()) &&
                    this.getEspecie().equals(outroMamiferoVoador.getEspecie())&&
                    this.getPeso() == outroMamiferoVoador.getPeso() &&
                    this.getAndarDesejado() == outroMamiferoVoador.getAndarDesejado() &&
                    this.getTempoDeEspera() == outroMamiferoVoador.getTempoDeEspera() &&
                    this.getTemperaturaIdeal() == outroMamiferoVoador.getTemperaturaIdeal() &&
                    this.isPeludo() == outroMamiferoVoador.isPeludo();
        }
    }
}
