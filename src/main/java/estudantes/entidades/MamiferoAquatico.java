package estudantes.entidades;

class MamiferoAquatico extends Mamifero{

    /**
     * Construtor do mamifero.
     * Todos os atributos são passados por parâmetro, exceto o tempo de espera
     * que sempre começa em 0.
     *
     * @param id
     * @param nome
     * @param especie
     * @param andarDesejado
     * @param peso
     * @param temperaturaIdeal
     */
    public MamiferoAquatico(int id, String nome, String especie, int andarDesejado, int peso, int temperaturaIdeal, boolean peludo) {
        super(id, nome, especie, andarDesejado, peso, temperaturaIdeal, peludo);
    }

    /**
     * @return Uma String com a indicação da ação de nadar do anfíbio.
     */
    public String nadar(){
        return "nadando";
    }

    /**
     * Retorna uma representação em String do mamífero aquático.
     * A representação é composta por todos os atributos do mamífero aquático.
     * @return representação em String do mamífero aquático
     */
    @Override
    public String toString(){
        return "Mamifero aquático " + super.toString();
    }

    /**
     * Retorna se o mamífero aquático é igual a outro.
     * @param o o outro mamífero aquático a ser comparada.
     * @return true se os mamíferos aquáticos são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(this == o){
            return true;
        }

        if(!(o instanceof MamiferoAquatico)){
            return false;
        } else {
            MamiferoAquatico outroMamiferoAquatico = (MamiferoAquatico) o;
            return this.getNome().equals(outroMamiferoAquatico.getNome()) &&
                    this.getEspecie().equals(outroMamiferoAquatico.getEspecie())&&
                    this.getPeso() == outroMamiferoAquatico.getPeso() &&
                    this.getTemperaturaIdeal() == outroMamiferoAquatico.getTemperaturaIdeal() &&
                    this.isPeludo() == outroMamiferoAquatico.isPeludo();
        }
    }

    /**
     * Retorna o hashcode do mamífero.
     * @return hashcode do mamífero
     */
    @Override
    public int hashCode(){
        int hash = super.hashCode();
        hash *= 29 + (this.isPeludo() ? 31 : 37);
        return hash;
    }
}