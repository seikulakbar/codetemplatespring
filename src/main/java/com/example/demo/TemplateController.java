package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TemplateController {

	@Autowired
	private PrepareArchetypeGenerateCommand archetypeCmd;

	@PostMapping("/templates")
	public ResponseEntity<Template> createTemplate(@RequestBody Template template) {
		String repoUrl = template.getProjectUrl() + ".git";
		String cloneDirectoryPath = "C:\\Projects\\Github_Project";
		try {
			System.out.println("Cloning " + repoUrl + " into " + repoUrl);
			Git.cloneRepository().setURI(repoUrl).setDirectory(Paths.get(cloneDirectoryPath).toFile()).call();
			System.out.println("Completed Cloning");
		} catch (GitAPIException e) {
			e.printStackTrace();
		}

		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command("cmd.exe", "/c", "mvn archetype:create-from-project");
			builder.directory(new File("C:\\Projects\\Github_Project"));
			Process process = builder.start();

			Thread.sleep(5000);
			builder.command("cmd.exe", "/c", "mvn clean install");
			builder.directory(new File("C:\\Projects\\Github_Project\\target\\generated-sources\\archetype"));
			process = builder.start();

			String command = archetypeCmd.prepareArchetypeGenerateCmd(template.getGroupId(), template.getArtifactId(),
					template.getPkg(), template.getVersion());
			builder.command("cmd.exe", "/c", command);
			builder.directory(new File("C:\\Projects\\SpringBoot_Code_Template"));
			process = builder.start();
			
			System.out.println("Generated SpringBoot_Code_Template");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Template>(template, HttpStatus.OK);
	}

}