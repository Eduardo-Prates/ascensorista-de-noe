package estudantes.entidades;

abstract class Mamifero extends Animal{

    boolean peludo;
    /**
     * Construtor do mamífero.
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
        return super.toString() +
                "Peludo: " + peludo + "\n";
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
