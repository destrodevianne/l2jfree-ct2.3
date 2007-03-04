package net.sf.l2j.tools.hibernate;

import java.sql.SQLException;
import java.util.List;

import net.sf.l2j.tools.db.DerbyHelper;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.dialect.DerbyDialect;

/**
 * 
 * Class for unit testing hibernate DAO. 
 * This class provides automatic startup and shutdown of derby in setup and teardown.
 * It use dynamic generation of configuration and use dbunit to set up database.
 */
public abstract class ADAOTestCase extends AHibernateTestCase
{

	/** Constructor */
	public ADAOTestCase(String _name)
	{
		super(_name);
	}

	/**
	 * {@link Override} TestCase#setUp()
	 */
	public void setUp() throws Exception
	{
		// Database initialisation if not existant
		DerbyHelper.startup();

		// Init des properties hibernate
		System.getProperties().put("hibernate.connection.url", DerbyHelper.PROTOCOL);
		System.getProperties().put("hibernate.connection.driver_class", DerbyHelper.DRIVER);
		System.getProperties().put("hibernate.connection.username", DerbyHelper.USER);
		System.getProperties().put("hibernate.connection.password", DerbyHelper.PASSWORD);

		System.getProperties().put("hibernate.dialect", DerbyDialect.class.getName());
		System.getProperties().put("hibernate.show_sql", "true");
		System.getProperties().put("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");
		System.getProperties().put("hibernate.cache.provider_class", "org.hibernate.cache.EhCacheProvider");

		// Appel du chargement de la factory
		super.setUp();

		// Initialisation DBUnit
		setUpDBUnit();
	}

	/**
	 * @throws DatabaseUnitException
	 * @throws SQLException
	 */
	public void setUpDBUnit() throws Exception
	{
		// Connection initialisation
		IDatabaseConnection connection = null;
		connection = new DatabaseConnection(DerbyHelper.getConnection(), DerbyHelper.USER);

		// initialize your dataset here

		List<IDataSet> dataSetList = getDataSet();
		try
		{
			for (int indice = 0; indice < dataSetList.size(); indice++)
			{
				IDataSet dataSet = dataSetList.get(indice);
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			connection.close();
		}
	}

	protected void tearDown() throws Exception
	{
		// Check close connection
		super.tearDown();

		// Shutdown database
		DerbyHelper.shutdown();
	}

	/**
	 * Returns the test dataset.
	 */
	// protected abstract IDataSet getDataSet() throws Exception;
	protected abstract List<IDataSet> getDataSet() throws Exception;

}
