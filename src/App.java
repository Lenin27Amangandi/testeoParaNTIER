import DataAcces.SQLiteDataHelper;
import DataAcces.DAO.AdministradorDAO;
import DataAcces.DAO.AdministradorTipoDAO;
import DataAcces.DAO.CategoriaDAO;
import DataAcces.DAO.PiezaDeArteDAO;
import DataAcces.DAO.SeccionDAO;
import DataAcces.DTO.AdministradorDTO;
import DataAcces.DTO.AdministradorTipoDTO;
import DataAcces.DTO.CategoriaDTO;
import DataAcces.DTO.PiezaDeArteDTO;
import DataAcces.DTO.SeccionDTO;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            // Testeo de conexión a la base de datos SQLite para verificar que la conexión es exitosa
            SQLiteDataHelper.testConnection();

            // Testeo de Dao Categoria
            System.out.println();
            System.out.println("- ".repeat(25));
            CategoriaDAO dao = new CategoriaDAO();
            for (CategoriaDTO cate : dao.readAll()) {
                System.out.println(cate.toString());
            }

            // Testeo para el getMaxRow Categoria
            System.out.println("- ".repeat(25));
            Integer maxRow1 = dao.getMaxRow();
            System.out.println("Número máximo de filas: " + maxRow1);

            // Testeo de Dao AdministradorTipo
            System.out.println("- ".repeat(25));
            AdministradorTipoDAO dao2 = new AdministradorTipoDAO();
            for (AdministradorTipoDTO adminTip : dao2.readAll()) {
                System.out.println(adminTip.toString());
            }

            //Testeo para el getMaxRow AdministradorTipo
            System.out.println("- ".repeat(25));
            Integer maxRow2 = dao2.getMaxRow();
            System.out.println("Número máximo de filas: " + maxRow2);

            //Testeo de Dao Seccion
            System.out.println();
            System.out.println("- ".repeat(25));
            SeccionDAO dao3= new SeccionDAO();
            for (SeccionDTO seccion : dao3.readAll()) {
                System.out.println(seccion.toString());
            }
            // Testeo para el getMaxRow AdministradorTipo
            System.out.println("- ".repeat(25));
            Integer maxRow3 = dao3.getMaxRow();
            System.out.println("Número máximo de filas: " + maxRow3);

            // Testeo de Dao PiezaDeArte
            System.out.println();
            System.out.println("- ".repeat(25));
            PiezaDeArteDAO dao4 = new PiezaDeArteDAO();
            for (PiezaDeArteDTO pieza : dao4.readAll()) {
                System.out.println(pieza.toString());
            }
            // Testeo para el getMaxRow AdministradorTipo
            System.out.println("- ".repeat(25));
            Integer maxRow4 = dao4.getMaxRow();
            System.out.println("Número máximo de filas: " + maxRow4);
        

            // Testeo de Dao Administrador 
            System.out.println();
            System.out.println("- ".repeat(25));
            AdministradorDAO dao5 = new AdministradorDAO();
            for (AdministradorDTO admin : dao5.readAll()) {
                System.out.println(admin.toString());
            }

            // Testeo para el getMaxRow AdministradorTipo
            System.out.println("- ".repeat(25));
            Integer maxRow5 = dao5.getMaxRow();
            System.out.println("Número máximo de filas: " + maxRow5);

        } catch (Exception e) {
            throw e;
        }
    }
}
