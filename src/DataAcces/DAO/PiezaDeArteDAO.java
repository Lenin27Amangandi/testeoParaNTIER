package DataAcces.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import DataAcces.SQLiteDataHelper;
import DataAcces.DTO.PiezaDeArteDTO;

public class PiezaDeArteDAO extends SQLiteDataHelper implements IDAO<PiezaDeArteDTO> {

    @Override
    public boolean create(PiezaDeArteDTO entity) throws Exception {
        String query = "INSERT INTO PiezaDeArte (BarCode, Nombre, Autor, Descripcion, PrecioReplica, idCategoria, idSeccion) VALUES(?,?,?,?,?,?,?)";
        try {
            Connection          conn = openConnection();
            PreparedStatement   pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getBarCode());
            pstmt.setString(2, entity.getNombre());
            pstmt.setString(3, entity.getAutor());
            pstmt.setString(4, entity.getDescripcion());
            pstmt.setDouble(5, entity.getPrecioReplica());
            pstmt.setInt(6, entity.getIdCategoria());
            pstmt.setInt(7, entity.getIdSeccion());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public boolean update(PiezaDeArteDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Categoria SET Nombre = ?, Descripcion = ? , PrecioReplica = ?, , FechaModifica = ? WHERE BarCode = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, entity.getDescripcion());
            pstmt.setDouble(3, entity.getPrecioReplica());
            pstmt.setString(4, dtf.format(now).toString());
            pstmt.setString(5, entity.getBarCode());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
        throw e;
        }
    }

    @Override
    public List<PiezaDeArteDTO> readAll() throws Exception {

        List <PiezaDeArteDTO> list = new ArrayList<>();
        String query = "SELECT idPieza "
                    + " ,BarCode          "
                    + " ,Nombre           "
                    + " ,Autor            "
                    + " ,Descripcion      "
                    + " ,PrecioReplica    "
                    + " ,idCategoria      "
                    + " ,idSeccion        "
                    + " ,Estado           "
                    + " ,FechaCrea        "
                    + " ,FechaModifica    "
                    + " From PiezaDeArte  "
                    + " WHERE Estado='A' ";

        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                PiezaDeArteDTO pieza = new PiezaDeArteDTO(rs.getInt(1)
                                                , rs.getString(2)
                                                , rs.getString(3)
                                                , rs.getString(4)
                                                , rs.getString(5)
                                                , rs.getDouble(6)
                                                , rs.getInt(7)
                                                , rs.getInt(8)
                                                , rs.getString(9)
                                                , rs.getString(10)
                                                , rs.getString(11));
                list.add(pieza);
            }
            
        } catch (Exception e) {
        throw e;
        }
        return list;
    }

    @Override
    public boolean delete(String barcode) throws Exception {
        String query = "UPDATE PiezaDeArte SET Estado = ? WHERE BarCode = ? ";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "X");
            pstmt.setString(2, barcode);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        }
    }



    @Override
    public Integer getMaxRow() throws Exception {
        String query = " SELECT COUNT(*)"
                + " FROM    PiezaDeArte"
                + " WHERE   Estado ='A'";
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        throw e;
        }
        return 0;
    }



    @Override
    public PiezaDeArteDTO readBy(String barcode) throws Exception {
    PiezaDeArteDTO piezaArtDTO = new PiezaDeArteDTO();
    String query = "SELECT idPieza "
                + " ,BarCode          "
                + " ,Nombre           "
                + " ,Autor            "
                + " ,Descripcion      "
                + " ,PrecioReplica    "
                + " ,idCategoria      "
                + " ,idSeccion        "
                + " ,Estado           "
                + " ,FechaCrea        "
                + " ,FechaModifica    "
                + " From PiezaDeArte  "
                + " WHERE Estado='A' "
                + " AND   BarCode = " + barcode;

                try {
                    Connection conn = openConnection();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        piezaArtDTO = new PiezaDeArteDTO(rs.getInt(1)
                                            , rs.getString(2)
                                            , rs.getString(3)
                                            , rs.getString(4)
                                            , rs.getString(5)
                                            , rs.getDouble(6)
                                            , rs.getInt(7)
                                            , rs.getInt(8)
                                            , rs.getString(9)
                                            , rs.getString(10)
                                            , rs.getString(11));
                    }
                } catch (Exception e) {
                    throw e; // TODO: handle exception
                }
                return piezaArtDTO;
        }
    
}
