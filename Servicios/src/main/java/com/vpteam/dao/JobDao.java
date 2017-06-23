//@author QUINCHO

package com.vpteam.dao;

import static com.vpteam.dao.Conexion.logger;
import com.vpteam.entities.Job;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobDao 
{
    public int insertar(Job objJobs)
    {  
        int id = 0;
        
        try 
        {
            logger.info(Conexion.obtenerInstancia().obtenerConexion());
            Statement statement = Conexion.obtenerInstancia().obtenerConexion().createStatement();
            PreparedStatement estado = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(
                    "INSERT INTO jobs (images, description, jobs_contractor_id) VALUES (?, ?, ?);"
                            , statement.RETURN_GENERATED_KEYS);
            estado.setString(1, null);
            estado.setString(2, objJobs.getDescription());
            estado.setInt(3, objJobs.getJobsContractorId());
            estado.execute();
            ResultSet rs = estado.getGeneratedKeys();             
                         
            while(rs.next())             	
                id = rs.getInt(1);
        }
        catch(SQLException exception)
        {
            logger.error(exception);
        }
        
        return id;
    }
    
    public List<Job> seleccionar()
    {
        ResultSet rs;
        List<Job> lista = new ArrayList<>();
        
        try 
        {
             PreparedStatement estado = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(
                    "SELECT * FROM jobs");
            //estado = Conexion.obtenerInstancia().obtenerConexion().createStatement();
            rs = estado.executeQuery();
            
            while(rs.next())
                lista.add(new Job(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
        }
        catch(SQLException exception)
        {
            logger.error(exception);
            System.out.println("Error: " + exception.getErrorCode() + exception.getMessage());
        }
        
        return lista;
    }
    
    public void eliminar(int id)
    {
        try
        {
            PreparedStatement estado = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(
                    "DELETE * FROM jobs WHERE id = ?");
            estado.setInt(1, id);
            estado.executeUpdate();
        }
        catch(SQLException exception)
        {
            logger.error(exception);
            System.out.println("Error: " + exception.getErrorCode() + exception.getMessage());
        }
    }
    
    public void actualizar(int id, Job job)
    { 
        String query = "UPDATE job SET images = ?, description = ?" +
                       "WHERE id = ?";
        
        try 
        {
            PreparedStatement estado = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
            estado.setString(1, job.getImages());
            estado.setString(2, job.getDescription());
            estado.setInt(id, id);
            estado.executeUpdate();
        }
        catch(SQLException exception)
        {
            logger.error(exception);
            logger.info("Error: " + exception.getErrorCode() + exception.getMessage());
        }
    }
    
    
    public int numeroDePaginas()
    {
    	ResultSet rs;
    	String query = "SELECT COUNT(*)   FROM jobs";
    	int cantidadDePaginas = 0;
    	try 
    	{
    		PreparedStatement preparedStatement = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
    		rs = preparedStatement.executeQuery();
    	 	while(rs.next())
        	{
    	 		cantidadDePaginas = rs.getInt(1);
        	}
		} catch (SQLException sql) 
    	{
			logger.error(sql);
		}
    	return cantidadDePaginas;
    }
    
    /////////////////////////////////////   (2-1 * 10)           10
//    public List<Job> pagSeleccionar(int indice, int cantidad)
//    {
//        ResultSet rs;
//        List<Job> lista = new ArrayList<>();
//        String query = "SELECT * FROM personas LIMIT ?, ?";
//        
//        try 
//        {
//            PreparedStatement estado = Conexion.obtenerInstancia().obtenerConexion().prepareStatement(query);
//            estado.setInt(1, indice);
//            estado.setInt(2, cantidad);
//            rs = estado.executeQuery();
//            
//            while(rs.next())
//                lista.add(new Job( rs.getString(2), rs.getString(3), rs.getInt(4),
//                        rs.getString(5) ) );
//        }
//        catch(SQLException exception)
//        {
//            logger.error(exception);
//            logger.info("Error: " + exception.getErrorCode() + exception.getMessage());
//        }
//        
//        return lista;
//    }
}
