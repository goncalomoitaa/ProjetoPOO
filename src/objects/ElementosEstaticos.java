package objects;

public abstract class ElementosEstaticos extends ElementosDeJogo {

    public ElementosEstaticos(int x, int y) {
        super(x, y, TipoDeElemento.ESTATICO);
    }

    public boolean isSolid() {
        return true;
    }

}
