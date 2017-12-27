/*******************************************************************************
 * (c) Copyright 2017 EntIT Software LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a 
 * copy of this software and associated documentation files (the 
 * "Software"), to deal in the Software without restriction, including without 
 * limitation the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit persons to 
 * whom the Software is furnished to do so, subject to the following 
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be included 
 * in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY 
 * KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE 
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN 
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
 * IN THE SOFTWARE.
 ******************************************************************************/
package com.fortify.client.fod.api;

import java.util.Collection;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import com.fortify.client.fod.connection.FoDAuthenticatingRestConnection;
import com.fortify.util.rest.json.JSONMap;

/**
 * This class is used to access FoD bug tracker related functionality.
 * 
 * @author Ruud Senden
 *
 */
public class FoDBugTrackerAPI extends AbstractFoDAPI {
	public FoDBugTrackerAPI(FoDAuthenticatingRestConnection conn) {
		super(conn);
	}
	
	public void addBugLinkToVulnerabilities(String releaseId, String bugLink, Collection<String> vulnIds) {
		String path = String.format("/api/v3/releases/%s/vulnerabilities/bug-link", releaseId);
		JSONMap data = new JSONMap();
		data.put("bugLink", bugLink);
		data.put("vulnerabilityIds", vulnIds);
		conn().executeRequest(HttpMethod.POST, conn().getBaseResource().path(path), Entity.entity(data,MediaType.APPLICATION_JSON), JSONMap.class);
	}
}