package com.dreadedsoftware.example.eclipse.plugin.scala.handlers

import org.eclipse.core.commands.AbstractHandler
import org.eclipse.core.commands.ExecutionEvent
import org.eclipse.core.commands.ExecutionException
import org.eclipse.ui.IWorkbenchWindow
import org.eclipse.ui.handlers.HandlerUtil
import org.eclipse.jface.dialogs.MessageDialog

class SampleHandler extends AbstractHandler {

  def execute(event: ExecutionEvent): AnyRef = {
    val window = HandlerUtil.getActiveWorkbenchWindowChecked(event)
    MessageDialog.openInformation(window.getShell, "Scala", "Hello, Eclipse Scala world")
    null
  }

/*
Original Java:
package com.dreadedsoftware.example.eclipse.plugin.scala.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;

|**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 *|
public class SampleHandler extends AbstractHandler {
	|**
	 * The constructor.
	 *|
	public SampleHandler() {
	}

	|**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 *|
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"Scala",
				"Hello, Eclipse world");
		return null;
	}
}

*/
}