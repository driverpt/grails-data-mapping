/* Copyright (C) 2010 SpringSource
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.datastore.mapping.core.impl;

import java.util.List;

import org.springframework.datastore.mapping.model.PersistentEntity;

/**
 * An operation that is pending execution
 * 
 * @author Graeme Rocher
 *
 * @param <E> The native entity type (examples could be Row, Document etc.)
 * @param <K> The native key
 */
public interface PendingOperation<E, K> extends Runnable {

	/**
	 * @return The {@link PersistentEntity} being inserted
	 */
	public abstract PersistentEntity getEntity();

	/**
	 * @return The native key to insert
	 */
	public abstract K getNativeKey();

	/**
	 * @return The native entry to persist
	 */
	public abstract E getNativeEntry();
	
	/**
	 * Operations to be executed directly prior to this operation
	 * @return The operations to execute prior 
	 */
	public List<PendingOperation<E,K>> getPreOperations();
	
	/**
	 * Adds an operation to executed prior to other operations
	 * @param preOperation The prior operation
	 */
	public void addPreOperation(PendingOperation<E, K> preOperation);
	
	/**
	 * Operations to be executed directly following this operation
	 * @return The operations to cascade to 
	 */
	public List<PendingOperation<E,K>> getCascadeOperations();
	
	/**
	 * Adds an operation that should be executed after this operation
	 * 
	 * @param pendingOperation The pending operation
	 */
	public void addCascadeOperation(PendingOperation<E, K> pendingOperation);


}