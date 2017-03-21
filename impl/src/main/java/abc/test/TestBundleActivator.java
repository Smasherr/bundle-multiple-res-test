package abc.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBundleActivator implements BundleActivator {
	
    private static final Logger LOG = LoggerFactory.getLogger(TestBundleActivator.class);
	
	@Override
	public void start(BundleContext context) {
		Bundle b = context.getBundle();
		try {
			for (Enumeration<URL> urls = b.getResources("test.txt"); urls.hasMoreElements();) {
				try (BufferedReader br = new BufferedReader(new InputStreamReader(urls.nextElement().openConnection().getInputStream()))) {
					while(br.ready()){
						LOG.info(br.readLine());
					}
				}
			}
		}
		catch (IOException e)
		{
			LOG.error("An exception occured!", e);			
		}
	}
	
	@Override
	public void stop(BundleContext context) {
	}
}