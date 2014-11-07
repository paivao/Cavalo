package cavalo;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PosicaoDAO {

    Posicao create(int x, int y) {
	return new Posicao(x, y);
    }

    void insert(Posicao position) throws SQLException, ClassNotFoundException {
	CavaloFabricaConexoes fabrica = new CavaloFabricaConexoes();
	Statement stmt = fabrica.conexao().createStatement();
	int rs = stmt.executeUpdate("INSERT INTO movimentos (x,y) values (" + position.getX() + "," + position.getY() + ")");

	if (rs == 0) {
	    System.out.println("insert informa: INSERCAO NAO REALIZADA");
	} else {
	    System.out.println("insert informa: INSERCAO REALIZADA COM SUCESSO");
	}
    }

    public void findALL() throws ClassNotFoundException, SQLException {
	List<Posicao> lista = new ArrayList<>();
	CavaloFabricaConexoes fabrica = new CavaloFabricaConexoes();
	Statement stmt = fabrica.conexao().createStatement();
	ResultSet rs = stmt.executeQuery("SELECT * FROM movimentos");

	while (rs.next()) {
	    lista.add(new Posicao(rs.getInt("x"), rs.getInt("y")));
	}

	for (int i = 0; i < lista.size(); i++) {
	    System.out.println("Movimento " + (i + 1) + " x=" + (lista.get(i).getX()) + "," + "y=" + (lista.get(i).getY()));
	}
    }

    public void InsereList(List<Posicao> lista) throws ClassNotFoundException, SQLException {
	CavaloFabricaConexoes fabrica = new CavaloFabricaConexoes();
	PreparedStatement st = fabrica.conexao().prepareStatement("INSERT INTO movimentos (x,y) VALUES (?,?)");

	for (Posicao p : lista) {
	    st.setInt(1, p.getX());
	    st.setInt(2, p.getY());
	    boolean deuCerto = (st.executeUpdate() == 1);
	}

	st.close();
    }

}
