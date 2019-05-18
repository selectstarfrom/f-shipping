package networks.executors;

/**
 * 
 * @author Syed Firoze K
 *
 */
public interface IFileExecutor {

	/**
	 * Calling this method will initiate the processing of given Input CSV files.
	 * 
	 * @param pFileName
	 *            Full qualified filename of the input csv.
	 * @param pFileNo
	 * @return
	 * @throws Exception
	 */
	public boolean execute(String pFilename) throws Exception;

}
