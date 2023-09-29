package estudantes.entidades;

abstract class Mamifero extends Animal{

    boolean peludo;
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
    public Mamifero(int id, String nome, String especie, int andarDesejado, int peso, int temperaturaIdeal, boolean peludo) {
        super(id, nome, especie, andarDesejado, peso, temperaturaIdeal);
        this.peludo = peludo;
    }

    /**
     * Retorna se o mamífero é peludo ou não.
     * @return true se o mamífero é peludo, false caso contrário
     */
    public boolean isPeludo() {
        return peludo;
    }

    /**
     * Retorna uma representação em String do mamífero.
     * A representação é composta por todos os atributos do mamífero.
     * @return representação em String do mamífero
     */
    @Override
    public String toString(){
        return super.toString() + "\n" +
                "Peludo: " + peludo;
    }

    /**
     * Retorna se o mamífero é igual a outro.
     * @param o o outro mamífero a ser comparada.
     * @return true se os mamíferos são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(this == o){
            return true;
        }

        if(!(o instanceof Mamifero)){
            return false;
        } else {
            Mamifero outroMamifero = (Mamifero) o;
            return this.getNome().equals(outroMamifero.getNome()) &&
                    this.getEspecie().equals(outroMamifero.getEspecie())&&
                    this.getPeso() == outroMamifero.getPeso() &&
                    this.getTemperaturaIdeal() == outroMamifero.getTemperaturaIdeal() &&
                    this.isPeludo() == outroMamifero.isPeludo();
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
