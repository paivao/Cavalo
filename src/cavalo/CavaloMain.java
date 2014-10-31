package JDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CavaloMain
{
	public static void main(String args[]) throws ClassNotFoundException, SQLException
	{
		
		PosicaoDAO pos=new PosicaoDAO();
		
		/*List<Posicao> lista=new ArrayList<Posicao>();
		
		Posicao p1 =  new Posicao(1,1);
		Posicao p2 =  new Posicao(1,3);
		Posicao p3 =  new Posicao(1,2);
		
		lista.add(p1);
		lista.add(p2);
		lista.add(p3);
		
		pos.InsereList(lista);*/
		
		pos.findALL();
			
	}
}