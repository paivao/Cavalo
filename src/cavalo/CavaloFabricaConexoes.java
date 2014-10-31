package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class CavaloFabricaConexoes
{
	private String base;
	private String user;
	private String password;
	private String server;
	private String tcpport;
	
	public CavaloFabricaConexoes()
	{
		this.setServer("localhost");
		this.setTcpport("3306");
		this.setBase("java");
		this.setUser("LuanCardoso");
		this.setPassword("");
		
	}
	
	public Connection conexao() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		return (Connection)
				DriverManager.getConnection("jdbc:mysql://" +getServer()
						+ ":" +getTcpport()+ "/" + getBase(), getUser(), getPassword());
		
			
	}
	
	public String getBase()
	{
		return base;
	}
	
	public void setBase(String base)
	{
		this.base=base;
	}
	
	public String getUser()
	{
		return user;
	}
	
	public void setUser(String user)
	{
		this.user=user;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password=password;
	}
	
	public String getServer()
	{
		return server;
	}
	
	public void setServer(String server)
	{
		this.server=server;
	}
	
	public String getTcpport()
	{
		return tcpport;
	}
	
	public void setTcpport(String tcpport)
	{
		this.tcpport=tcpport;
	}
}