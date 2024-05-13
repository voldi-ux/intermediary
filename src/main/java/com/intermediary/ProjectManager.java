package com.intermediary;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.intermediary.blockchain.blockchainExtended;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ProjectManager {
	private static Project selectedProject;
	private static blockchainExtended<CommitDetails> blockchain = new blockchainExtended<CommitDetails>();

	public static Project getSelectedProject() {
		return selectedProject;
	}

	public static void setSelectedProject(Project selectedProject) {
		ProjectManager.selectedProject = selectedProject;
	}

	public static void verifyCommits() {
		/// use the validator to validate the commit
		if (selectedProject != null) {
			try {
				String validatorPath = "validators/" + selectedProject.getId() + "/" + "Validator.java";
				File file = new File(validatorPath);

				if (!file.isFile()) {
					Logger.addAndDisplay("add a validator to validate the commits");
					return;
				}

				blockchain.addStakes(selectedProject.getParticipants());

				Class<?> validator = getValidator(validatorPath);
				Object obj = validator.getDeclaredConstructors()[0].newInstance(); // call the default constructor
				Method validate = validator.getDeclaredMethod("validate", String.class);

				for (CommitDetails details : selectedProject.getCommits()) {
					if ((boolean) validate.invoke(obj, details.getDetails())) {
						System.out.println(details);
						blockchain.addBlock(details);
					} else {
						// if a block fails then report the error and stop adding subsequent commits to
						// the blockchain
						Alert a = new Alert(AlertType.INFORMATION);
						a.setContentText("could not validate commit with id: " + details.getId());
						a.show();
						return;
					}

				}

				Logger.addAndDisplay("All commits for project : " + selectedProject.getName()
						+ " was successfully verified and added to the blockchain");

			} catch (MalformedURLException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			} catch (InstantiationException e) {

				e.printStackTrace();
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			} catch (InvocationTargetException e) {

				e.printStackTrace();
			} catch (NoSuchMethodException e) {

				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static Class<?> getValidator(String validatorPath) throws Exception {

		// get a Java compiler
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		if (compiler == null) {
			throw new IllegalStateException("No Java compiler available. Ensure JDK is used to run this code.");
		}

		// Create a compilation task
		// a compilation unit corresponds to a single java source file that can be
		// compiled
		// so a compilation units corresponds to a set of java files that are too be be
		// compiled.
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

		Iterable<? extends JavaFileObject> compilationUnits = fileManager
				.getJavaFileObjectsFromStrings(List.of(validatorPath));

		// a compilation task is a process of converting java source files /
		// compilations unites into
		// java bytecode.
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null,
				compilationUnits);

		// starts the compilation process
		boolean success = task.call();
		if (!success) {
			throw new Exception("could not compile the validator class");
		}

		// Load the compiled class dynamically
		URLClassLoader classLoader = URLClassLoader
				.newInstance(new URL[] { new File("validators/" + selectedProject.getId()).toURI().toURL() });
		Class<?> compiledClass = Class.forName("Validator", true, classLoader);
		Object obj = compiledClass.getDeclaredConstructors()[0].newInstance();
//		Method sayHelloMethod = compiledClass.getDeclaredMethod("validate");

//		sayHelloMethod.invoke(obj, null);
		return compiledClass;
	};

}
