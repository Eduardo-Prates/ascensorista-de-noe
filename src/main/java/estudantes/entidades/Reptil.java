package estudantes.entidades;

public class Reptil extends Animal{

    public final int PACIENCIA_MAXIMA = 10;

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
    public Reptil(int id, String nome, String especie, int andarDesejado, int peso, int temperaturaIdeal) {
        super(id, nome, especie, andarDesejado, peso, temperaturaIdeal);
    }

    /**
     * @return Uma String com a indicação da ação de andar do réptil.
     */
    public String andar() {
        return "andando";
    }


    /**
     * Retorna uma representação em String do réptil.
     * A representação é composta por todos os atributos do réptil.
     * @return representação em String do réptil
     */
    @Override
    public String toString(){
        return "Réptil " + super.toString();
    }

    /**
     * Retorna se o réptil é igual a outro.
     * @param o o outro réptil a ser comparado.
     * @return true se os répteis são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(this == o){
            return true;
        }

        if(!(o instanceof Reptil)){
            return false;
        } else {
            Reptil outroReptil = (Reptil) o;
            return this.getNome().equals(outroReptil.getNome()) &&
                    this.getEspecie().equals(outroReptil.getEspecie())&&
                    this.getPeso() == outroReptil.getPeso() &&
                    this.getTemperaturaIdeal() == outroReptil.getTemperaturaIdeal();
        }
    }

    /**
     * Retorna o hashcode do réptil.
     * @return hashcode do réptil
     */
    @Override
    public int hashCode(){
        return super.hashCode();
    }
}
