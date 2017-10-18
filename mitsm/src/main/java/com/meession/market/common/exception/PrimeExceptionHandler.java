package com.meession.market.common.exception;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.persistence.OptimisticLockException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * this exception class is used for handling exceptions of Primefaces related.
 * @author sanguine
 *
 */

public class PrimeExceptionHandler extends
		org.primefaces.application.exceptionhandler.PrimeExceptionHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(PrimeExceptionHandler.class);

	public PrimeExceptionHandler(ExceptionHandler wrapped) {
		super(wrapped);
	}

	/**
	 * retrieving exceptions from Primeface's exception queue and extracting the 
	 * information from the exception stack
	 */
	@Override
	public void handle() throws FacesException {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getResponseComplete()) {
			return;
		}

		Iterable<ExceptionQueuedEvent> exceptionQueuedEvents = getUnhandledExceptionQueuedEvents();
		if (exceptionQueuedEvents != null&& exceptionQueuedEvents.iterator() != null) {
			Iterator<ExceptionQueuedEvent> unhandledExceptionQueuedEvents = getUnhandledExceptionQueuedEvents().iterator();

			if (unhandledExceptionQueuedEvents.hasNext()) {
				try {
					Throwable throwable = unhandledExceptionQueuedEvents.next().getContext().getException();
					unhandledExceptionQueuedEvents.remove();

					Throwable rootCause = getRootCause(throwable);
					
					final NavigationHandler nav = context.getApplication().getNavigationHandler();

					if(rootCause instanceof ViewExpiredException){
						nav.handleNavigation(context, null, "/error-pages/timeout.xhtml");
						return;
					}else if (rootCause instanceof BusinessException) {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,rootCause.getMessage(), "");
						FacesContext.getCurrentInstance().addMessage(null,message);
					} else if (rootCause instanceof OptimisticLockException) {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"The date was updated or deleted by another transaction, please refresh the page","");
						FacesContext.getCurrentInstance().addMessage(null,message);
					} else {
						FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,rootCause.getMessage(), "");
						FacesContext.getCurrentInstance().addMessage(null,message);
						/* ExceptionInfo info = createExceptionInfo(rootCause);*/

						// always log the exception
						logger.error(rootCause.getMessage(), rootCause);

						/* KEEP THIS FOR REMOVE
						 * if (context.getPartialViewContext().isAjaxRequest())
						// {
						// handleAjaxException(context, rootCause, info);
						// } else {
						// handleRedirect(context, rootCause, info);
						// }
						 * */
					}
				} catch (Exception ex) {
					logger.error("Could not handle exception!", ex);
				}
				FacesContext.getCurrentInstance().validationFailed();
			}

			while (unhandledExceptionQueuedEvents.hasNext()) {
				// Any remaining unhandled exceptions are not interesting. First
				// fix the first.
				unhandledExceptionQueuedEvents.next();
				unhandledExceptionQueuedEvents.remove();
			}
		}
	}
}
