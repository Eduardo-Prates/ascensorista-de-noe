package estudantes.entidades;

public class AveVoadora extends Ave{

    public final int PACIENCIA_MAXIMA = 20;

    /**
     * Construtor da ave voadora.
     *
     * @param id
     * @param nome
     * @param especie
     * @param andarDesejado
     * @param peso
     * @param temperaturaIdeal
     * @param corDasPenas
     */
    public AveVoadora(int id, String nome, String especie, int andarDesejado, int peso, int temperaturaIdeal, String corDasPenas) {
        super(id, nome, especie, andarDesejado, peso, temperaturaIdeal, corDasPenas);
    }

    /**
     * @return Uma String com a indicação da ação de voar da ave voadora.
     */
    public String voar() {
        return "voando";
    }

    /**
     * Retorna uma representação em String da ave voadora.
     * A representação é composta por todos os atributos do ave voadora.
     * @return representação em String do ave voadora
     */
    @Override
    public String toString(){
        return "Ave voadora" + super.toString();
    }

    /**
     * Retorna se a ave voadora é igual a outra.
     * @param o a outra ave voadora a ser comparada.
     * @return true se as aves voadoras são iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o){
       return super.equals(o);
    }
}
