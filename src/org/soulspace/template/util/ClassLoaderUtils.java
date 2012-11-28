package org.soulspace.template.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassLoaderUtils {
	
	/**
	 * Returns the list of classes in the given package.
	 * @param pkgName
	 * @return list of classes
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("rawtypes")
	public static List<Class> getClassesForPackage(String pkgName)
			throws ClassNotFoundException {
		// This will hold a list of directories matching the package name.
		// There may be more than one if a package is split over multiple jars/paths
		List<Class> classes = new ArrayList<Class>();
		ArrayList<File> directories = new ArrayList<File>();
		try {
			ClassLoader cld = Thread.currentThread().getContextClassLoader();
			if (cld == null) {
				throw new ClassNotFoundException("Can't get class loader.");
			}
			// Ask for all resources for the path
			Enumeration<URL> resources = cld.getResources(pkgName.replace('.', '/'));
			while (resources.hasMoreElements()) {
				URL res = resources.nextElement();
				if (res.getProtocol().equalsIgnoreCase("jar")) {
					JarURLConnection conn = (JarURLConnection) res.openConnection();
					JarFile jar = conn.getJarFile();
					for (JarEntry e : Collections.list(jar.entries())) {

						if (e.getName().startsWith(pkgName.replace('.', '/'))
								&& e.getName().endsWith(".class") && !e.getName().contains("$")) {
							String className = e.getName().replace("/", ".").substring(0,
									e.getName().length() - 6);
							System.out.println(className);
							classes.add(Class.forName(className));
						}
					}
				} else
					directories.add(new File(URLDecoder.decode(res.getPath(), "UTF-8")));
			}
		} catch (NullPointerException x) {
			throw new ClassNotFoundException(pkgName + " does not appear to be "
					+ "a valid package (Null pointer exception)");
		} catch (UnsupportedEncodingException encex) {
			throw new ClassNotFoundException(pkgName + " does not appear to be "
					+ "a valid package (Unsupported encoding)");
		} catch (IOException ioex) {
			throw new ClassNotFoundException("IOException was thrown when trying "
					+ "to get all resources for " + pkgName);
		}

		// For every directory identified capture all the .class files
		for (File directory : directories) {
			if (directory.exists()) {
				// Get the list of the files contained in the package
				String[] files = directory.list();
				for (String file : files) {
					// we are only interested in .class files
					if (file.endsWith(".class")) {
						// removes the .class extension
						classes.add(Class.forName(pkgName + '.'
								+ file.substring(0, file.length() - 6)));
					}
				}
			} else {
				throw new ClassNotFoundException(pkgName + " (" + directory.getPath()
						+ ") does not appear to be a valid package");
			}
		}
		return classes;
	}

	/**
	 * Returns a list of all classes implementing the given interface.
	 * @param pkgName The package to search for implementations
	 * @param theInterface The interface for which implementations should be listed
	 * @return List of implementations of the given interface
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Class> getImplementationsInPackage(String pkgName,
			Class theInterface) {
		List<Class> classList = new ArrayList<Class>();
		try {
			for (Class discovered : getClassesForPackage(pkgName)) {
				if(theInterface.isAssignableFrom(discovered)) {
					classList.add(discovered);
				}
			}
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
		}
		return classList;
	}

}
