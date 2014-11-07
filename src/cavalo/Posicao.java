package cavalo;

public class Posicao {

    private int x;
    private int y;

    public Posicao(int x, int y) {
	this.x = x;
	this.y = y;

    }

    public int getX() {
	return x;
    }

    public void SetX(int x) {
	this.x = x;
    }

    public int getY() {
	return y;
    }

    public void SetY(int y) {
	this.y = y;
    }

    @Override
    public String toString() {
	return "(" + (this.x+1) + "," + (this.y+1) + ")";
    }
}
