
import estudantes.entidades.Anfibio;
import estudantes.entidades.Animal;
import estudantes.entidades.Ave;
import estudantes.entidades.AveVoadora;
import estudantes.entidades.MamiferoAquatico;
import estudantes.entidades.MamiferoTerrestre;
import estudantes.entidades.MamiferoVoador;
import estudantes.entidades.Peixe;
import estudantes.entidades.Reptil;
import estudantes.entidades.ReptilAquatico;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class AnimaisTest {

    public AnimaisTest() {
    }

    /* testar paciências dos Animais */
    @Test
    public void testPacienciaDosAnimais(){
        Animal a = new Animal(1, "A", "B", 1, 100, 20);
        assertEquals("Animal genérico", 25, a.PACIENCIA_MAXIMA);

        Ave b = new Ave(1, "A", "B", 1, 100, 20, "C");
        assertEquals("Ave", 30, b.PACIENCIA_MAXIMA);

        AveVoadora c = new AveVoadora(1, "A", "B", 1, 100, 20, "C");
        assertEquals("Ave voadora", 20, c.PACIENCIA_MAXIMA);

        Anfibio d = new Anfibio(1, "A", "B", 1, 100, 20);
        assertEquals("Anfibio", 45, d.PACIENCIA_MAXIMA);

        MamiferoAquatico e = new MamiferoAquatico(1, "A", "B", 1, 100, 20, true);
        assertEquals("Mamifero aquatico", 40, e.PACIENCIA_MAXIMA);

        MamiferoTerrestre f = new MamiferoTerrestre(1, "A", "B", 1, 100, 20, true);
        assertEquals("Mamifero terrestre", 35, f.PACIENCIA_MAXIMA);

        MamiferoVoador g = new MamiferoVoador(1, "A", "B", 1, 100, 20, true);
        assertEquals("Mamifero voador", 15, g.PACIENCIA_MAXIMA);

        Peixe h = new Peixe(1, "A", "B", 1, 100, 20, "C");
        assertEquals("Peixe", 60, h.PACIENCIA_MAXIMA);

        Reptil i = new Reptil(1, "A", "B", 1, 100, 20);
        assertEquals("Reptil", 10, i.PACIENCIA_MAXIMA);

        ReptilAquatico j = new ReptilAquatico(1, "A", "B", 1, 100, 20);
        assertEquals("Reptil", 15, j.PACIENCIA_MAXIMA);
    }

    /* testar implementação de equals*/
    @Test
    public void testImplementacaoDoEquals(){
        Animal a = new Animal(1, "A", "B", 1, 100, 20);
        Animal b = new Animal(1, "A", "B", 1, 100, 20);
        assertTrue("Animais iguais", a.equals(b));

        MamiferoTerrestre a1 = new MamiferoTerrestre(1, "A", "B", 1, 100, 20, true);
        MamiferoTerrestre b1 = new MamiferoTerrestre(1, "A", "B", 1, 100, 20, true);
        assertTrue("Mamíferos terrestres iguais", a1.equals(b1));

        Ave c = new Ave(1, "A", "B", 1, 100, 20, "C");
        Ave d = new Ave(2, "A", "B", 1, 100, 20, "C");
        assertFalse("Aves com diferentes ID", c.equals(d));

        Anfibio e = new Anfibio(1, "A", "B", 1, 100, 20);
        Reptil f = new Reptil(1, "A", "B", 1, 100, 20);
        assertFalse("Animais de classes diferentes", e.equals(f));

        AveVoadora g = new AveVoadora(1, "A", "B", 1, 100, 20, "C");
        assertFalse("Ave voadora e NULL", g.equals(null));

        MamiferoAquatico h = new MamiferoAquatico(1, "A", "B", 1, 100, 20, true);
        MamiferoAquatico i = new MamiferoAquatico(1, "A", "B", 1, 100, 20, false);
        assertFalse("Mamíferos aquáticos peludos e pelados", h.equals(i));

        MamiferoTerrestre j = new MamiferoTerrestre(1, "A", "B", 1, 100, 20, true);
        MamiferoTerrestre k = new MamiferoTerrestre(1, "A", "B", 2, 100, 20, true);
        assertFalse("Mamíferos terrestres com desejo de andares diferentes", j.equals(k));

        MamiferoVoador m = new MamiferoVoador(1, "A", "B", 1, 100, 20, true);
        MamiferoVoador n = new MamiferoVoador(1, "C", "B", 1, 100, 20, true);
        assertFalse("Mamíferos voadores diferentes NOMES e reflexivos", m.equals(n) || n.equals(m));

        Peixe o= new Peixe(1, "A", "B", 1, 100, 20, "C");
        Peixe p = new Peixe(1, "A", "B", 1, 100, 20, "D");
        assertFalse("Peixes de cores diferentes", o.equals(p));

        Reptil q = new Reptil(1, "A", "B", 1, 100, 20);
        Reptil r = new Reptil(1, "A", "C", 1, 100, 20);
        assertFalse("Répteis com diferentes ESPECIES", q.equals(r));

        ReptilAquatico s = new ReptilAquatico(1, "A", "B", 1, 100, 20);
        ReptilAquatico t = new ReptilAquatico(1, "A", "B", 1, 99, 19);
        assertFalse("Répteis aquáticos com diferentes PESOS e TEMP ideais", s.equals(t));
    }

    /* testar implementação de hashCode */
    @Test
    public void testImplementacaoDoHashCode(){
        Animal a = new Animal(1, "A", "B", 1, 100, 20);
        Animal b = new Animal(1, "A", "B", 1, 100, 20);
        assertEquals("Animal", a.hashCode(), b.hashCode());

        Ave c = new Ave(1, "A", "B", 1, 100, 20, "C");
        Ave d = new Ave(1, "A", "B", 1, 100, 20, "C");
        assertEquals("Ave", c.hashCode(), d.hashCode());

        AveVoadora e = new AveVoadora(1, "A", "B", 1, 100, 20, "C");
        AveVoadora f = new AveVoadora(1, "A", "B", 1, 100, 20, "C");
        assertEquals("Ave voadora", e.hashCode(), f.hashCode());

        MamiferoAquatico g = new MamiferoAquatico(1, "A", "B", 1, 100, 20, true);
        MamiferoAquatico h = new MamiferoAquatico(1, "A", "B", 1, 100, 20, true);
        assertEquals("Mamífero aquático", g.hashCode(), h.hashCode());

        Peixe i = new Peixe(1, "A", "B", 1, 100, 20, "C");
        Peixe j = new Peixe(1, "A", "B", 1, 100, 20, "C");
        assertEquals("Peixe", i.hashCode(), j.hashCode());
    }

    /* testar aumenta espera */
    @Test (expected = RuntimeException.class)
    public void testAumentoDeEsperaDeAnimal(){
        Animal a = new Animal(1, "A", "B", 1, 100, 20);
        assertEquals(a.getTempoDeEspera(), 0);
        a.aumentaEspera();
        assertEquals(a.getTempoDeEspera(), 1);
        for(int i = a.getTempoDeEspera(); i <= a.PACIENCIA_MAXIMA; i++, a.aumentaEspera()){
        }
    }

    /* testar aumenta espera de Ave */
    /* esses testes não fazem sentido, por causa de um erro de UML */
    @Ignore
    @Test (expected = RuntimeException.class)
    public void testAumentoDeEsperaDeAve(){
        Ave a = new Ave(1, "A", "B", 1, 100, 20,"C");
        assertEquals(a.getTempoDeEspera(), 0);
        a.aumentaEspera();
        assertEquals(a.getTempoDeEspera(), 1);
        for(int i = a.getTempoDeEspera(); i <= a.PACIENCIA_MAXIMA; i++, a.aumentaEspera()){
        }
    }

    /* testar aumenta espera de Peixe */
    /* esses testes não fazem sentido, por causa de um erro de UML */
    @Ignore
    @Test (expected = RuntimeException.class)
    public void testAumentoDeEsperaDePeixe(){
        Peixe a = new Peixe(1, "A", "B", 1, 100, 20,"C");
        assertEquals(a.getTempoDeEspera(), 0);
        a.aumentaEspera();
        assertEquals(a.getTempoDeEspera(), 1);
        for(int i = a.getTempoDeEspera(); i <= a.PACIENCIA_MAXIMA; i++, a.aumentaEspera()){
        }
    }

    /* testar animais que voam */
    @Test
    public void testAnimaisQueVoam(){
        AveVoadora a = new AveVoadora(1, "A", "B", 1, 100, 20, "C");
        assertEquals(a.voar(), "voando");

        MamiferoVoador b = new MamiferoVoador(1, "A", "B", 1, 100, 20, true);
        assertEquals(b.voar(), "voando");
    }

    /* testar animais de nadam */
    @Test
    public void testAnimaisQueNadam(){
        Anfibio a = new Anfibio(1, "A", "B", 1, 100, 20);
        assertEquals(a.nadar(), "nadando");

        MamiferoAquatico b = new MamiferoAquatico(1, "A", "B", 1, 100, 20, true);
        assertEquals(b.nadar(), "nadando");

        Peixe c = new Peixe(1, "A", "B", 1, 100, 20, "C");
        assertEquals(c.nadar(), "nadando");

        ReptilAquatico d = new ReptilAquatico(1, "A", "B", 1, 100, 20);
        assertEquals(d.nadar(), "nadando");
    }

    /* testar animais de andam */
    @Test
    public void testAnimaisQueAndam(){
        Ave a = new Ave(1, "A", "B", 1, 100, 20, "C");
        assertEquals(a.andar(), "andando");

        AveVoadora b = new AveVoadora(1, "A", "B", 1, 100, 20, "C");
        assertEquals(b.andar(), "andando");

        Anfibio c = new Anfibio(1, "A", "B", 1, 100, 20);
        assertEquals(c.andar(), "andando");

        MamiferoTerrestre d = new MamiferoTerrestre(1, "A", "B", 1, 100, 20, false);
        assertEquals(d.andar(), "andando");

        Reptil e = new Reptil(1, "A", "B", 1, 100, 20);
        assertEquals(e.andar(), "andando");

        ReptilAquatico f = new ReptilAquatico(1, "A", "B", 1, 100, 20);
        assertEquals(f.andar(), "andando");
    }
}