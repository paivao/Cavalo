package cavalo;

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
	
        /**
         * Contrutor padrão da fábrica, com os dados fornecidos pelo professor.
         */
	public CavaloFabricaConexoes()
	{
		this.setServer("186.202.152.241");
		this.setTcpport("3306");
		this.setBase("websimulado16");
		this.setUser("websimulado16");
		this.setPassword("a97c4cfb3ca0a2");
		
	}
        
        /**
         * Cria uma conexão com o banco de dados.
         * @return a conexão
         * @throws ClassNotFoundException
         * @throws SQLException 
         */
	public Connection conexao() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		return (Connection)
				DriverManager.getConnection("jdbc:mysql://" +getServer()
						+ ":" +getTcpport()+ "/" + getBase(), getUser(), getPassword());
		
			
	}
	
        /**
         * Pegar o nome da base de dados.
         * @return o nome da base
         */
	public String getBase()
	{
		return base;
	}
	
        /**
         * Selecionar a base de dados.
         * @param base a base
         */
	public void setBase(String base)
	{
		this.base=base;
	}
	
        /**
         * Pegar o usuário.
         * @return o usuário
         */
	public String getUser()
	{
		return user;
	}
	
        /**
         * Colocar o usuário.
         * @param user o usuário
         */
	public void setUser(String user)
	{
		this.user=user;
	}
	
        /**
         * Pegar a senha do banco de dados.
         * @return a senha
         */
        private String getPassword(){
            return this.password;
        }
        
        /**
         * Colocar a senha do usuário do banco de dados.
         * @param password a senha
         */
	public void setPassword(String password)
	{
		this.password=password;
	}
	
        /**
         * Pegar o servidor.
         * @return o servidor
         */
	public String getServer()
	{
		return server;
	}
	
        /**
         * Colocar o servidor.
         * @param server o servidor
         */
	public void setServer(String server)
	{
		this.server=server;
	}
	
        /**
         * Pegar a porta TCP do banco de dados.
         * @return a porta TCP
         */
	public String getTcpport()
	{
		return tcpport;
	}
	
        /**
         * Colocar a porta TCP do banco de dados.
         * @param tcpport a porta TCP
         */
	public void setTcpport(String tcpport)
	{
		this.tcpport=tcpport;
	}
}