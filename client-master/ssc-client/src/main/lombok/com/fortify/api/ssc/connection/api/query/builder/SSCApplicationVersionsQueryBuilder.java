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
package com.fortify.api.ssc.connection.api.query.builder;

import com.fortify.api.ssc.annotation.SSCRequiredActionsPermitted;
import com.fortify.api.ssc.connection.SSCAuthenticatingRestConnection;
import com.fortify.api.ssc.connection.api.query.SSCEntityQuery;
import com.fortify.api.util.rest.json.preprocessor.JSONMapEnrichWithDeepLink;

/**
 * This builder class can be used to build {@link SSCEntityQuery} instances
 * for querying application versions.
 * 
 * @author Ruud Senden
 *
 */
public final class SSCApplicationVersionsQueryBuilder extends AbstractSSCEntityQueryBuilder<SSCApplicationVersionsQueryBuilder> {
	@SSCRequiredActionsPermitted({"GET=/api/v\\d+/projectVersions"})
	public SSCApplicationVersionsQueryBuilder(SSCAuthenticatingRestConnection conn) {
		super(conn, true);
		appendPath("/api/v1/projectVersions");
		preProcessor(new JSONMapEnrichWithDeepLink(conn.getBaseUrl()+"html/ssc/index.jsp#!/version/${id}/fix"));
	}

	public final SSCApplicationVersionsQueryBuilder paramFields(String... fields) {
		return super.paramFields(fields);
	}

	public final SSCApplicationVersionsQueryBuilder orderBy(String orderBy) {
		return super.paramOrderBy(orderBy);
	}

	public final SSCApplicationVersionsQueryBuilder paramQAnd(String field, String value) {
		return super.paramQAnd(field, value);
	}

	public SSCApplicationVersionsQueryBuilder id(String id) {
		return super.paramQAnd("id", id);
	}

	public SSCApplicationVersionsQueryBuilder applicationName(String applicationName) {
		return super.paramQAnd("project.name", applicationName);
	}

	public SSCApplicationVersionsQueryBuilder versionName(String versionName) {
		return super.paramQAnd("name", versionName);
	}
	
	public SSCApplicationVersionsQueryBuilder nameOrId(String applicationVersionNameOrId, String separator) {
		String[] appVersionElements = applicationVersionNameOrId.split(separator);
		if ( appVersionElements.length == 1 ) {
			return id(appVersionElements[0]);
		} else if ( appVersionElements.length == 2 ) {
			return applicationName(appVersionElements[0]).versionName(appVersionElements[1]);
		} else {
			throw new IllegalArgumentException("Applications or versions containing a '+separator+' can only be specified by id");
		}
	}
	
	public SSCApplicationVersionsQueryBuilder nameOrId(String applicationVersionNameOrId) {
		return nameOrId(applicationVersionNameOrId, ":");
	}
}