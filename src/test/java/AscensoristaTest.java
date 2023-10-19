
import estudantes.entidades.Anfibio;
import estudantes.entidades.Ave;
import estudantes.entidades.AveVoadora;
import estudantes.entidades.MamiferoAquatico;
import estudantes.entidades.MamiferoTerrestre;
import estudantes.entidades.Peixe;
import estudantes.entidades.Reptil;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import professor.entidades.Arca;

public class AscensoristaTest {

    Arca arca;

    public AscensoristaTest() {
    }

    @Before
    public void setUp(){
        arca = new Arca();
    }

    /* teste de arca sem animais */
    @Test(timeout = 500)
    public void testSemAnimais(){
        for(int i = 0; i < 1000; i++){
            arca.ascensorista.agir(arca.elevador, arca.andares[arca.elevador.getAndar()]);
            arca.elevador.movimentar();
        }
    }

    /* testar transporte de mamíferos terrestres polares e desérticos */
    @Test
    public void testMamiferosTerrestresPolaresEDeserticos() {
        Random gerador = new Random(1);
        for(int i = 0; i < 1000; i++){
            arca.andares[(i+1)%5].colocarNaFila(new MamiferoTerrestre(i, "A", "B", gerador.nextInt(5), 100, i%2==0?-10:50, true));
            arca.ascensorista.agir(arca.elevador, arca.andares[arca.elevador.getAndar()]);
            arca.elevador.movimentar();
        }
        assertFalse("Sem mamíferos transportados", arca.andares[0].animaisQueDesceram.isEmpty()
                && arca.andares[1].animaisQueDesceram.isEmpty()
                && arca.andares[2].animaisQueDesceram.isEmpty()
                && arca.andares[3].animaisQueDesceram.isEmpty()
                && arca.andares[4].animaisQueDesceram.isEmpty());
    }

    /* teste transporte de répteis super pesados */
    @Test
    public void testRepteisSuperPesados(){
        Random gerador = new Random(1);
        for(int i = 0; i < 1000; i++){
            arca.andares[(i+1)%5].colocarNaFila(new Reptil(i, "A", "B", gerador.nextInt(5), 1000, 20));
            arca.ascensorista.agir(arca.elevador, arca.andares[arca.elevador.getAndar()]);
            arca.elevador.movimentar();
        }
        assertFalse("Sem mamíferos aquáticos transportados", arca.andares[0].animaisQueDesceram.isEmpty()
                && arca.andares[1].animaisQueDesceram.isEmpty()
                && arca.andares[2].animaisQueDesceram.isEmpty()
                && arca.andares[3].animaisQueDesceram.isEmpty()
                && arca.andares[4].animaisQueDesceram.isEmpty());
    }

    /* testar transporte de aves voadoras e peixes */
    @Test
    public void testAveVoadorasEPeixes() {
        Random gerador = new Random(1);
        for(int i = 0; i < 1000; i++){
            arca.andares[(i+1)%5].colocarNaFila(new AveVoadora(i, "A", "B", gerador.nextInt(5), 100, 20,"C"));
            arca.andares[(i+1)%5].colocarNaFila(new Peixe(i, "A", "B", gerador.nextInt(5), 100, 20, "C"));
            arca.ascensorista.agir(arca.elevador, arca.andares[arca.elevador.getAndar()]);
            arca.elevador.movimentar();
        }
        assertFalse("Sem bichos transportados", arca.andares[0].animaisQueDesceram.isEmpty()
                && arca.andares[1].animaisQueDesceram.isEmpty()
                && arca.andares[2].animaisQueDesceram.isEmpty()
                && arca.andares[3].animaisQueDesceram.isEmpty()
                && arca.andares[4].animaisQueDesceram.isEmpty());
    }

    /* testar filas enormes de anfíbios leves */
    @Test
    public void testFilasEnormesDeAnfibios() {
        Random gerador = new Random(1);
        for(int i = 0; i < 1000; i++){
            for(int j = 0; j < 500; j++){
                arca.andares[(i+1)%5].colocarNaFila(new Anfibio(i, "A", "B", gerador.nextInt(5), 10, 20));
            }
            arca.ascensorista.agir(arca.elevador, arca.andares[arca.elevador.getAndar()]);
            arca.elevador.movimentar();
        }
        assertFalse("Sem anfíbios transportados", arca.andares[0].animaisQueDesceram.isEmpty()
                && arca.andares[1].animaisQueDesceram.isEmpty()
                && arca.andares[2].animaisQueDesceram.isEmpty()
                && arca.andares[3].animaisQueDesceram.isEmpty()
                && arca.andares[4].animaisQueDesceram.isEmpty());
    }

    /* testar transporte apenas de aves polares */
    @Test
    public void testAvesPolares() {
        Random gerador = new Random(1);
        for(int i = 0; i < 1000; i++){
            arca.andares[(i+1)%5].colocarNaFila(new Ave(i, "A", "B", gerador.nextInt(5), 100, -10, "C"));
            arca.ascensorista.agir(arca.elevador, arca.andares[arca.elevador.getAndar()]);
            arca.elevador.movimentar();
        }
        assertFalse("Sem aves polares transportadas", arca.andares[0].animaisQueDesceram.isEmpty()
                && arca.andares[1].animaisQueDesceram.isEmpty()
                && arca.andares[2].animaisQueDesceram.isEmpty()
                && arca.andares[3].animaisQueDesceram.isEmpty()
                && arca.andares[4].animaisQueDesceram.isEmpty());
    }

    /* testar transporte apenas de mamíferos polares aquaticos */
    @Test
    public void testMamiferosPolaresAquaticos() {
        Random gerador = new Random(1);
        for(int i = 0; i < 1000; i++){
            arca.andares[(i+1)%5].colocarNaFila(new MamiferoAquatico(i, "A", "B", gerador.nextInt(5), 100, -10, true));
            arca.ascensorista.agir(arca.elevador, arca.andares[arca.elevador.getAndar()]);
            arca.elevador.movimentar();
        }
        assertFalse("Sem mamíferos aquáticos polares transportados", arca.andares[0].animaisQueDesceram.isEmpty()
                && arca.andares[1].animaisQueDesceram.isEmpty()
                && arca.andares[2].animaisQueDesceram.isEmpty()
                && arca.andares[3].animaisQueDesceram.isEmpty()
                && arca.andares[4].animaisQueDesceram.isEmpty());
    }

    /* testar transporte apenas de répteis desérticos */
    @Test
    public void testRepteisDeserticos() {
        Random gerador = new Random(1);
        for(int i = 0; i < 1000; i++){
            arca.andares[(i+1)%5].colocarNaFila(new Reptil(i, "A", "B", gerador.nextInt(5), 100, 50));
            arca.ascensorista.agir(arca.elevador, arca.andares[arca.elevador.getAndar()]);
            arca.elevador.movimentar();
        }
        assertFalse("Sem répteis desérticos transportados", arca.andares[0].animaisQueDesceram.isEmpty()
                && arca.andares[1].animaisQueDesceram.isEmpty()
                && arca.andares[2].animaisQueDesceram.isEmpty()
                && arca.andares[3].animaisQueDesceram.isEmpty()
                && arca.andares[4].animaisQueDesceram.isEmpty());
    }

    /* testar só peixes no térreo querendo ir pro último andar */
    @Test
    public void testPeixesNoTerreoQuerendoSubir() {
        for(int i = 0; i < 1000; i++){
            arca.andares[0].colocarNaFila(new Peixe(i, "A", "B", Arca.QUANTIDADE_DE_ANDARES_NA_ARCA-1, 100, 20, "D"));
            arca.ascensorista.agir(arca.elevador, arca.andares[arca.elevador.getAndar()]);
            arca.elevador.movimentar();
        }
        assertFalse("Peixes não subiram", arca.andares[Arca.QUANTIDADE_DE_ANDARES_NA_ARCA-1].animaisQueDesceram.isEmpty());
    }

    /* testar só aves no último andar querendo ir pro térreo */
    @Test
    public void testAvesNoUltimoAndarQuerendoDescer() {
        for(int i = 0; i < 1000; i++){
            arca.andares[Arca.QUANTIDADE_DE_ANDARES_NA_ARCA-1].colocarNaFila(new Ave(i, "A", "B", 0, 100, 20, "D"));
            arca.ascensorista.agir(arca.elevador, arca.andares[arca.elevador.getAndar()]);
            arca.elevador.movimentar();
        }
        assertFalse("Aves não desceram", arca.andares[0].animaisQueDesceram.isEmpty());
    }

    /* testar só anfíbios no terceiro andar e querendo descer ali mesmo */
    @Test
    public void testAnfibiosNoTerceiroAndarQueQueremFicar(){
        for(int i = 0; i < 1000; i++){
            arca.andares[2].colocarNaFila(new Anfibio(i, "A", "B", 2, 100, 20));
            arca.ascensorista.agir(arca.elevador, arca.andares[arca.elevador.getAndar()]);
            arca.elevador.movimentar();
        }
        assertFalse("Anfibios não desceram", arca.andares[2].animaisQueDesceram.isEmpty());
    }

}