/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cavalo;

/**
 *
 * @author rafaelpaiva
 */
public class Utils {
    
    private static final int modulo = 8;
    
    static int toIndice(Ponto p) {
	return modulo*p.getY() + p.getX();
    }
    
    static Ponto toPonto(int indice) {
	return new Ponto(indice%modulo, indice/modulo);
    }
}
