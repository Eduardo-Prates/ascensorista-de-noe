package estudantes.entidades;

public class Ave extends Animal{

    public final int PACIENCIA_MAXIMA = 30;
    private String corDasPenas;
    /**
     * Construtor da ave.
     *
     * @param id
     * @param nome
     * @param especie
     * @param andarDesejado
     * @param peso
     * @param temperaturaIdeal
     */
    public Ave(int id, String nome, String especie, int andarDesejado, int peso, int temperaturaIdeal, String corDasPenas) {
        super(id, nome, especie, andarDesejado, peso, temperaturaIdeal);
        this.corDasPenas = corDasPenas;
    }

    /**
     * Retorna a cor das penas do animal.
     * @return uma String contendo a cor das penas do animal
     */
    public String getCorDasPenas() {
        return corDasPenas;
    }

    /**
     * @return Uma String com a indicação da ação de andar da ave.
     */
    public String andar() {
        return "andando";
    }

    /**
     * Retorna uma representação em String da ave.
     * A representação é composta por todos os atributos do ave.
     * @return representação em String do ave
     */
    @Override
    public String toString(){
        return "Ave " + super.toString() + "\n" +
                "Cor das penas: " + corDasPenas;
    }

    /**
     * Retorna se a ave é igual a outra.
     * @param o a outra ave a ser comparada.
     * @return true se as aves são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(this == o){
            return true;
        }

        if(!(o instanceof Ave)){
            return false;
        } else {
            Ave outraAve = (Ave) o;
            return this.getNome().equals(outraAve.getNome()) &&
                    this.getEspecie().equals(outraAve.getEspecie())&&
                    this.getPeso() == outraAve.getPeso() &&
                    this.getTemperaturaIdeal() == outraAve.getTemperaturaIdeal() &&
                    this.getCorDasPenas().equals(outraAve.getCorDasPenas());
        }
    }

    /**
     * Retorna o hashcode da ave.
     * @return hashcode da ave
     */
    @Override
    public int hashCode(){
        int hash = super.hashCode();
        hash *= 29 + this.corDasPenas.hashCode();
        return hash;
    }
}
