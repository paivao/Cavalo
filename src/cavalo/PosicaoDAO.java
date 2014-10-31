package JDBC;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class PosicaoDAO
{

	Posicao create(int x, int y)
	{
		return new Posicao(x,y);
	}

	void insert(Posicao position) throws SQLException, ClassNotFoundException
	{
		FabricaConexoes fabrica=new FabricaConexoes();
		Statement stmt=fabrica.conexao().createStatement();
		int rs=stmt.executeUpdate("INSERT INTO movimentos VALUES (" + position.getX() + ","+ position.getY()+")");
		
		if(rs==0)
			System.out.println("insert informa: INSERCAO NAO REALIZADA");
		else
			System.out.println("insert informa: INSERCAO REALIZADA COM SUCESSO");
	}
	
	void delete(Posicao position) throws ClassNotFoundException, SQLException
	{
		FabricaConexoes fabrica=new FabricaConexoes();
		Statement stmt=fabrica.conexao().createStatement();
		int rs=stmt.executeUpdate("DELETE FROM movimentos WHERE x="+ position.getX()+ "and y=" + position.getY());
		
		if(rs==0)
			System.out.println("delete informa: MOVIMENTO NAO DELETADO");
		else
			System.out.println("delete informa: MOVIMENTO DELETADO COM SUCESSO");
	}
	public void findALL() throws ClassNotFoundException, SQLException
	{
		ArrayList<Posicao> lista=new ArrayList<Posicao>();
		FabricaConexoes fabrica=new FabricaConexoes();
		Statement stmt=fabrica.conexao().createStatement();
		ResultSet rs=stmt.executeQuery("SELECT * FROM movimentos");
		
		while(rs.next())
		{
			lista.add(new Posicao(rs.getInt("x"),rs.getInt("y")));
		}
		
		
		for(int i=0;i<lista.size();i++)
			System.out.println("Movimento x="+lista.get(i).getX()+","+"y="+lista.get(i).getY());
	}
	
}