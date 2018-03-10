// Copyright 2013 Thiago H. de Paula Figueiredo
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// Copyright 2013 Thiago H. de Paula Figueiredo
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package br.com.arsmachina.eloquentia.tapestry.pages.page;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.annotations.ContentType;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.HttpError;
import org.apache.tapestry5.util.TextStreamResponse;

import br.com.arsmachina.eloquentia.controller.PageController;
import br.com.arsmachina.eloquentia.controller.TagController;
import br.com.arsmachina.eloquentia.entity.Page;
import br.com.arsmachina.eloquentia.entity.Tag;

/**
 * Serves the CSS for a given page.
 * 
 * @author Thiago H. de Paula Figueiredo (http://machina.com.br/thiago)
 */
@ContentType("text/css")
public class Css {
	
	@Inject
	private PageController pageController;
	
	@Inject
	private Messages messages;
	
	private Page page;
	
	Object onActivate(EventContext context) {
		Object returnValue = null;
		final String pageName = context.get(String.class, 0);
		if (context.getCount() == 1) {
			page = pageController.findByUri(pageName);
		}
		if (page == null) {
			 returnValue = new HttpError(404, messages.format("eloquentia.page-tag-not-found", pageName));
		}
		else {
			String css = page.getCss() != null ? page.getCss() : "";
			return new TextStreamResponse("text/css", css);
		}
		return returnValue;
	}

}
