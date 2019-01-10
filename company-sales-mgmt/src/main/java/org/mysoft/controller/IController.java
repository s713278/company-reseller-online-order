package org.mysoft.controller;

import org.springframework.http.ResponseEntity;

/**
 * Root controller specification for this project.
 * @author Swamy K
 *
 */
public interface IController<T,H> {

	ResponseEntity<H> handleRequest(T h);
	boolean preHandleRequest(T request,H response);
	boolean postHandleRequest(T request,H response);
}
