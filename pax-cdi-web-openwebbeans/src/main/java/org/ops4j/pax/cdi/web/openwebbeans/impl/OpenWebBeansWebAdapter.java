/*
 * Copyright 2012 Harald Wellmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.cdi.web.openwebbeans.impl;

import javax.servlet.ServletContextListener;

import org.apache.webbeans.config.WebBeansFinder;
import org.ops4j.pax.cdi.spi.CdiContainerListener;
import org.ops4j.pax.cdi.web.CdiWebAppDependencyManager;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component(property = "type=web", service = CdiContainerListener.class)
public class OpenWebBeansWebAdapter extends CdiWebAppDependencyManager {

    @Activate
    public void activate(BundleContext context) {
        WebBeansFinder.setSingletonService(new BundleSingletonService());
    }

    @Deactivate
    public void deactivate(BundleContext context) {
        // TODO the following causes an exception:
        // org.apache.webbeans.exception.WebBeansConfigurationException: 
        // Already using another custom SingletonService!

        // WebBeansFinder.setSingletonService(null);
    }

    
    @Override
    public ServletContextListener getServletContextListener() {
        return new OpenWebBeansListener();
    }
}
