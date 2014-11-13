package cavalo;

public class Posicao {

    private int x;
    private int y;

    /**
     * Construtor da posição.
     * @param x eixo X da posição
     * @param y eixo Y da posição
     */
    public Posicao(int x, int y) {
	this.x = x;
	this.y = y;

    }

    /**
     * Pegar o valor do eixo X.
     * @return o valor de X
     */
    public int getX() {
	return x;
    }

    /**
     * Coloca o valor do eixo X da posição.
     * @param x o valor do eixo
     */
    public void SetX(int x) {
	this.x = x;
    }

    /**
     * Pega o valor do eixo Y.
     * @return o valor de Y
     */
    public int getY() {
	return y;
    }

    /**
     * Coloca o valor do eixo Y da posição.
     * @param y o valor do eixo
     */
    public void SetY(int y) {
	this.y = y;
    }

    @Override
    public String toString() {
	return "(" + (this.x+1) + "," + (this.y+1) + ")";
    }

}
