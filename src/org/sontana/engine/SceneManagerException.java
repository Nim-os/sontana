package org.sontana.engine;

/**
 * The <code>SceneManagerException</code> class represents exceptions that appear when mishandling <code>AbstractScene</code> objects.
 * @author Christophe Simon
 *
 */
public class SceneManagerException extends Exception // TODO Might not even need this but might be useful when validating scenes?
{
	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = -8523474840417826892L;

	public SceneManagerException(String str)
	{
		super(str);
	}

}
